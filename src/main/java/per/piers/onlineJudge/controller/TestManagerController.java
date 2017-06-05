package per.piers.onlineJudge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import per.piers.onlineJudge.Exception.CRUDException;
import per.piers.onlineJudge.Exception.ExistenceException;
import per.piers.onlineJudge.model.Category;
import per.piers.onlineJudge.model.InputOutput;
import per.piers.onlineJudge.model.Question;
import per.piers.onlineJudge.model.TestData;
import per.piers.onlineJudge.util.DataAccessObject;

import java.util.ArrayList;
import java.util.Arrays;

@RequestMapping(path = "/testManager")
@Controller
public class TestManagerController {

    DataAccessObject dao;

    @Autowired
    public TestManagerController(DataAccessObject dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categories(Model model) {
        model.addAttribute("categories", dao.selectCategory(null));
        return "testManager/categories";
    }

    @RequestMapping(path = {"/category"})
    public String category() {
        ;
        return "testManager/category";
    }

    @RequestMapping(path = {"/category/{id}"})
    public String category(@PathVariable("id") Integer id, Model model) {
        Category category = new Category();
        category.setId(id);
        Category[] categories = dao.selectCategory(category);
        if (categories.length != 1) throw new CRUDException("categories.length != 1");
        model.addAttribute("category", categories[0]);
        return "testManager/category";
    }

    @RequestMapping(path = "/category/insert", method = RequestMethod.POST)
    @ResponseBody
    public String updateCourse(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        Category[] categories = dao.selectCategory(category);
        if (categories.length > 0) throw new ExistenceException("category" + categories[0]);
        int line = dao.insertCategory(category);
        if (line != 1) throw new CRUDException("insert: category != 1");
        return "SUCCESS";
    }

    @RequestMapping(path = "/category/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCourse(@PathVariable("id") Integer id, String name, String description) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        int line = dao.updateCategory(category);
        if (line != 1) throw new CRUDException("delete: category != 1");
        return "SUCCESS";
    }

    @RequestMapping(path = "/category/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String Course(@PathVariable("id") Integer id) {
        Category category = new Category();
        category.setId(id);
        int line = dao.deleteCategory(category);
        if (line != 1) throw new CRUDException("delete: category != 1");
        return "SUCCESS";
    }

    @RequestMapping(path = "/questions", method = RequestMethod.GET)
    public String questions(Model model) {
        model.addAttribute("questions", dao.selectQuestion(null));
        return "testManager/questions";
    }

    @RequestMapping(path = "/question", method = RequestMethod.GET)
    public String question(Model model) {
        ArrayList<Category> categories = new ArrayList<>();
        Category nullCategory = new Category();
        nullCategory.setId(0);
        nullCategory.setName("(暂时不选择题库)");
        categories.add(nullCategory);
        categories.addAll(Arrays.asList(dao.selectCategory(null)));
        model.addAttribute("categories", categories);
        return "testManager/question";
    }

    @RequestMapping(path = "/question/{id}", method = RequestMethod.GET)
    public String question(@PathVariable("id") Integer id, Model model) {
        Question question = new Question();
        question.setId(id);
        Question[] questions = dao.selectQuestion(question);
        if (questions.length != 1) throw new CRUDException("questions.length != 1");

        ArrayList<Category> categories = new ArrayList<>();
        if (questions[0].getCategory() != null) {
            categories.add(questions[0].getCategory());
        }
        Category nullCategory = new Category();
        nullCategory.setId(0);
        nullCategory.setName("(暂时不选择题库)");
        categories.add(nullCategory);
        for (Category category : dao.selectCategory(null)) {
            if (!(questions[0].getCategory() != null && category.getId().equals(questions[0].getCategory().getId())))
                categories.add(category);
        }

        model.addAttribute("question", questions[0]);
        model.addAttribute("categories", categories);
        return "testManager/question";
    }

    @RequestMapping(path = "/question/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertQuestion(Integer qid, String name, String description, Integer cid) {
        Question existedQuestion = new Question();
        existedQuestion.setQid(qid);
        Question[] existedQuestions = dao.selectQuestion(existedQuestion);
        if (existedQuestions.length > 0) throw new ExistenceException("question" + existedQuestions[0]);
        Question question = new Question();
        question.setQid(qid);
        question.setName(name);
        question.setDescription(description);
        Category category = new Category();
        if (cid != 0) category.setId(cid);
        question.setCategory(category);
        int line = dao.insertQuestion(question);
        if (line != 1) throw new CRUDException("insert: question != 1");
        return "SUCCESS";
    }

    @RequestMapping(path = "/question/{id}/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateQuestion(@PathVariable("id") Integer id, Integer qid, String name, String description, Integer cid) {
        Question question = new Question();
        question.setId(id);
        question.setQid(qid);
        question.setName(name);
        question.setDescription(description);
        Category category = new Category();
        if (cid != 0) category.setId(cid);
        question.setCategory(category);
        int line = dao.updateQuestion(question);
        if (line != 1) throw new CRUDException("update: question != 1");
        return "SUCCESS";
    }

    @RequestMapping(path = "/question/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteQuestion(@PathVariable("id") int id) {
        Question question = new Question();
        question.setId(id);
        int line = dao.deleteQuestion(question);
        if (line != 1) throw new CRUDException("delete: question != 1");
        return "SUCCESS";
    }

    @RequestMapping(path = "/testData/{id}", method = RequestMethod.GET)
    public String importTestData(@PathVariable("id") int id, Model model) {
        TestData selectTestData = new TestData();
        selectTestData.setQid(id);
        TestData[] testDatas = dao.selectTestData(selectTestData);
        if (testDatas.length == 1) {
            StringBuilder builder = new StringBuilder();
            for (InputOutput inputOutput : testDatas[0].getInputOutputs()) {
                builder.append(String.format("%s$%s\n", inputOutput.getInput(), inputOutput.getOutput()));
            }
            model.addAttribute("exitedTestDatas", builder.toString());
        } else if(testDatas.length == 0){
            model.addAttribute("exitedTestDatas", "（无）");
        } else {
            throw new CRUDException("testDatas.length != 1");
        }
        return "testManager/testData";
    }

    @RequestMapping(path = "/testData/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String importTestData(@PathVariable("id") Integer id, String inputOutputs) {
        ArrayList<InputOutput> inputOutputsList = new ArrayList<>();
        if (inputOutputs.isEmpty()) {
            dao.deleteTestData(id);
        } else {
            for (String inputOutput : inputOutputs.split("\n")) {
                String[] splits = inputOutput.split("\\$");
                InputOutput inputOutputLine = new InputOutput();
                inputOutputLine.setInput(splits[0]);
                inputOutputLine.setOutput(splits[1]);
                inputOutputLine.setQid(id);
                inputOutputsList.add(inputOutputLine);
            }
            dao.importTestData(inputOutputsList, id);
        }
        return "SUCCESS";
    }

}
