package per.piers.onlineJudge.util;

import org.springframework.stereotype.Service;
import per.piers.onlineJudge.Exception.ExistenceException;
import per.piers.onlineJudge.model.InputOutput;
import per.piers.onlineJudge.model.TestInfo;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class CodeProcessor {

    private int uid;
    private int qid;
    private long submitTime;
    private String code;
    private String tmpDir;
    private String codeDir;
    private String codeFile;
    private boolean isCompiled = false;

    public CodeProcessor(int uid, int qid, String code, long submitTime, String tmpDir) {
        this.uid = uid;
        this.qid = qid;
        this.submitTime = submitTime;
        this.code = code;
        this.tmpDir = tmpDir;
        this.codeDir = String.format("%s/%s/%s/%s/", tmpDir, uid, qid, submitTime);
        this.codeFile = String.format("%s/%s", codeDir, "Main.java");
    }

    public String compile() throws IOException {
        File codeFile = new File(this.codeFile);
        if (!codeFile.exists()) {
            codeFile.getParentFile().mkdirs();
            codeFile.createNewFile();
        } else {
            throw new ExistenceException("temp code file");
        }
        try (FileWriter writer = new FileWriter(codeFile)) {
            writer.write(code);
            writer.flush();
        }
        ProcessBuilder processBuilder = new ProcessBuilder("javac", "-J-Dfile.encoding=utf-8", "-J-Duser.country=US", "Main.java");
        processBuilder.directory(new File(codeDir));
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder output = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                output.append(line + "\n");
            isCompiled = true;
            return output.toString().isEmpty() ? null : output.toString();
        }
    }

    public TestInfo execute(ArrayList<InputOutput> inputOutputs) throws IOException {
        if (!isCompiled) throw new IllegalStateException("not compiled");

        int correct = 0;
        ArrayList<InputOutput> results = new ArrayList<>();
        // test all test cases
        for (InputOutput inputOutput : inputOutputs) {
            String output = test(inputOutput.getInput());
            InputOutput actualInputOutput = new InputOutput();
            actualInputOutput.setInput(inputOutput.getInput());
            actualInputOutput.setOutput(output);
            if (output.equals(inputOutput.getOutput())) {
                correct++;
                actualInputOutput.setCorrect(true);
            } else {
                actualInputOutput.setCorrect(false);
            }
            results.add(actualInputOutput);
        }
        TestInfo testInfo = new TestInfo(uid, qid, new Timestamp(submitTime), code, (double) correct / (double) inputOutputs.size());
        testInfo.setInputOutputs(results);
        return testInfo;
    }

    private String test(String input) throws IOException {
        String policyPath = codeDir + "/run.policy";
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-Xmx256m", "-Dfile.encoding=UTF-8", "-Djava.security.policy=" + policyPath, "-Duser.country=US", "Main");
        processBuilder.directory(new File(codeDir));
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        try (OutputStream outputStream = process.getOutputStream()) {
            outputStream.write(input.getBytes("UTF-8"));
            outputStream.flush();
        }
        StringBuilder results = new StringBuilder();
        try (Scanner in = new Scanner(process.getInputStream())) {
            while (in.hasNextLine())
                results.append(in.nextLine());
        }
        return results.toString();
    }

//    public static void main(String[] args) throws IOException {
//        CodeProcessor codeProcessor = new CodeProcessor(1,2,"import java.util.*;public class Main{\n" +
//                "    public static void main(String args[]){\n" +
//                "        Scanner in = new Scanner(System.in);int a = in.nextInt();System.out.println(a);\n" +
//                "    }\n" +
//                "}",System.currentTimeMillis(),"D:/");
//        System.out.println(codeProcessor.codeProcessor());
//        InputOutput inputOutput1 = new InputOutput();
//        inputOutput1.setInput("123");
//        inputOutput1.setOutput("123");
//        InputOutput inputOutput2 = new InputOutput();
//        inputOutput2.setInput("333");
//        inputOutput2.setOutput("223");
//        ArrayList<InputOutput> inputOutputs = new ArrayList<>();
//        inputOutputs.add(inputOutput1);
//        inputOutputs.add(inputOutput2);
//        TestInfo testInfo = codeProcessor.execute(inputOutputs);
//        System.out.println(testInfo);
//    }

}
