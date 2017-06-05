package per.piers.onlineJudge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import per.piers.onlineJudge.Exception.CRUDException;
import per.piers.onlineJudge.model.*;
import per.piers.onlineJudge.service.CppTest;
import per.piers.onlineJudge.util.DataAccessObject;
import per.piers.onlineJudge.service.JavaTest;
import per.piers.onlineJudge.service.LanguageTest;

import java.io.IOException;
import java.sql.Timestamp;

@Controller
@RequestMapping(path = "/test")
public class TestController {

    DataAccessObject dao;

    @Autowired
    public TestController(DataAccessObject dao) {
        this.dao = dao;
    }

    @RequestMapping(path = {"", "/categories"}, method = RequestMethod.GET)
    public String categories(Model model) {
        model.addAttribute("categories", dao.selectCategory(null));
        return "/test/categories";
    }

    @RequestMapping(path = "/category/{id}", method = RequestMethod.GET)
    public String questions(@PathVariable("id") Integer id, Model model) {
        Question question = new Question();
        Category category = new Category();
        category.setId(id);
        question.setCategory(category);
        model.addAttribute("questions", dao.selectQuestion(question));
        return "/test/questions";
    }

    @RequestMapping(path = "/question/{id}", method = RequestMethod.GET)
    public String question(@PathVariable("id") Integer id, Model model) {
        Question question = new Question();
        question.setId(id);
        Question[] questions = dao.selectQuestion(question);
        if (questions.length != 1) throw new CRUDException("questions.length != 1");
        model.addAttribute("question", questions);
        return "/test/question";
    }

    @RequestMapping(path = "/question/answer", method = RequestMethod.POST)
    public String questionAnswer(int qid, String code, String languageName, Model model) throws IOException {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User selectUser = new User();
        selectUser.setEmail(email);
        User user = dao.selectUser(selectUser);
        System.out.println(user.getEmail());
        long createTime = System.currentTimeMillis();
        LanguageTest languageTest = null;
        switch (languageName) {
            case "java":
                languageTest = new JavaTest(user.getId(), qid, code, createTime);
                break;
            case "cpp":
                languageTest = new CppTest(user.getId(), qid, code, createTime);
                break;
        }
        if(languageTest == null) throw new IllegalArgumentException("languageName = " + languageName);
        String result = languageTest.compile();
        if (result != null) {
            model.addAttribute("code", code);
            model.addAttribute("compileError", result);
            return "test/compileError";
        }
        TestData testData = new TestData();
        testData.setQid(qid);
        TestData[] testDatas = dao.selectTestData(testData);
        if (testDatas.length == 0) {
            TestInfo testInfo = new TestInfo(user.getId(), qid, new Timestamp(createTime), code, (double) 0);
            model.addAttribute("testInfo", testInfo);
            return "test/testResults";
        } else if (testDatas.length != 1) {
            throw new CRUDException("testDatas.length != 1" + testDatas);
        } else {
            TestInfo testInfo = languageTest.execute(testDatas[0].getInputOutputs());
            int line = dao.insertTestInfo(testInfo);
            if (line == 0) throw new CRUDException("insert failed: " + testInfo);
            else if (line > 1) throw new CRUDException("insert size > 1" + testInfo);
            model.addAttribute("testInfo", testInfo);
            return "test/testResults";
        }
    }

}
