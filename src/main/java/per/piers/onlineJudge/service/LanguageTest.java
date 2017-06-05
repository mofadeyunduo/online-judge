package per.piers.onlineJudge.service;

import org.springframework.stereotype.Service;
import per.piers.onlineJudge.Exception.ExistenceException;
import per.piers.onlineJudge.controller.TestController;
import per.piers.onlineJudge.model.InputOutput;
import per.piers.onlineJudge.model.TestInfo;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

@Service
public abstract class LanguageTest {

    private int uid;
    private int qid;
    private long submitTime;
    protected String code;
    protected String codeDir;
    protected String codeFile;
    private boolean isCompiled = false;
    private List<String> compileCommands = new ArrayList<>();
    private List<String> executeCommands = new ArrayList<>();

    protected LanguageTest(int uid, int qid, String code, long submitTime) {
        this.uid = uid;
        this.qid = qid;
        this.code = code;
        this.submitTime = submitTime;
        Properties properties = new Properties();
        try {
            try (InputStream inputStream = TestController.class.getClassLoader().getResourceAsStream("config/codeProcessor/codeProcessor.properties")) {
                properties.load(inputStream);
                String tmpDir = properties.getProperty("path");
                this.codeDir = String.format("%s/%s/%s/%s/", tmpDir, uid, qid, submitTime);
                this.codeFile = String.format("%s/%s", codeDir, getCodeFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.compileCommands = getCompileCommands();
        this.executeCommands = getExecuteCommands();
    }

    protected abstract List<String> getCompileCommands();

    protected abstract List<String> getExecuteCommands();

    protected abstract String getCodeFileName();

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
        //TODO: Docker 权限控制
        ProcessBuilder processBuilder = new ProcessBuilder(compileCommands);
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

    protected String test(String input) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(executeCommands);
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

}
