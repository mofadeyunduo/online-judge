package per.piers.onlineJudge.service;

import per.piers.onlineJudge.model.InputOutput;
import per.piers.onlineJudge.model.TestInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CppTest extends LanguageTest {

    public CppTest(int uid, int qid, String code, long submitTime) {
        super(uid, qid, code, submitTime);
    }

    @Override
    protected List<String> getCompileCommands() {
        ArrayList<String> compileCommands = new ArrayList<>();
        compileCommands.add("docker");
        compileCommands.add("run");
        compileCommands.add("--rm");
        compileCommands.add("-u");
        compileCommands.add("root");
        compileCommands.add("-v");
        compileCommands.add(String.format("%s:%s", codeDir, codeDir));
        compileCommands.add("gcc:7");
        compileCommands.add("/bin/sh");
        compileCommands.add("-c");
        compileCommands.add(String.format("cd %s&&g++ Main.cpp", codeDir));
        return compileCommands;
    }

    @Override
    protected List<String> getExecuteCommands() {
        ArrayList<String> executeCommands = new ArrayList<>();
        executeCommands.add("docker");
        executeCommands.add("run");
        executeCommands.add("--rm");
        executeCommands.add("-i");
        executeCommands.add("-u");
        executeCommands.add("root");
        executeCommands.add("-v");
        executeCommands.add(String.format("%s:%s", codeDir, codeDir));
        executeCommands.add("gcc:7");
        executeCommands.add("/bin/sh");
        executeCommands.add("-c");
        executeCommands.add(String.format("cd %s&&timeout 3s ./a.out", codeDir));
        return executeCommands;
    }

    @Override
    protected String getCodeFileName() {
        return "Main.cpp";
    }

//    public static void main(String[] args) throws IOException {
//        CppTest cppTest = new CppTest(1, 2, "#include<iostream>\n using namespace std;\n" +
//                "int main(){\nint n; cin>>n; cout<<n<<endl;\n}", System.currentTimeMillis());
//        System.out.println(cppTest.compile());
//        System.out.println();
//        InputOutput inputOutput1 = new InputOutput();
//        inputOutput1.setInput("123");
//        inputOutput1.setOutput("123");
//        InputOutput inputOutput2 = new InputOutput();
//        inputOutput2.setInput("333");
//        inputOutput2.setOutput("223");
//        ArrayList<InputOutput> inputOutputs = new ArrayList<>();
//        inputOutputs.add(inputOutput1);
//        inputOutputs.add(inputOutput2);
//        TestInfo testInfo = cppTest.execute(inputOutputs);
//        System.out.println(testInfo);
//    }

}
