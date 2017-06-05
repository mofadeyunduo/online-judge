package per.piers.onlineJudge.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.piers.onlineJudge.model.TestInfo;
import per.piers.onlineJudge.util.DataAccessObject;
import per.piers.onlineJudge.util.FindPlagiarismAlgorithm;

@RequestMapping(path = "/testManager")
@Controller
public class QuestionAnalysisController {

    private DataAccessObject dao;

    @Autowired
    public QuestionAnalysisController(DataAccessObject dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/questionsAnalysis" ,method = RequestMethod.GET)
    public String index() {
        return "questionAnalysis/index";
    }

    @RequestMapping(path = "/interval", method = RequestMethod.GET)
    public String interval(Model model) {
        model.addAttribute("questions", dao.selectQuestion(null));
        return "questionAnalysis/interval";
    }

    @RequestMapping(path = "/interval", method = RequestMethod.POST)
    @ResponseBody
    public String intervals(Integer id) {
        TestInfo selectTestInfo = new TestInfo();
        selectTestInfo.setQid(id);
        TestInfo[] testInfos = dao.selectTestInfo(selectTestInfo);
        int[] intervals = new int[5];
        for (TestInfo testInfo : testInfos) {
            if (testInfo.getCorrectRate() >= 0.9)
                intervals[4]++;
            else if (testInfo.getCorrectRate() >= 0.8)
                intervals[3]++;
            else if (testInfo.getCorrectRate() >= 0.7)
                intervals[2]++;
            else if (testInfo.getCorrectRate() >= 0.6)
                intervals[1]++;
            else if (testInfo.getCorrectRate() < 0.6)
                intervals[0]++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i: intervals) {
            builder.append(i + " ");
        }
        return builder.toString();
    }

	@RequestMapping(path = "/cluster", method = RequestMethod.GET)
	public String cluster(Model model) {
        model.addAttribute("questions", dao.selectQuestion(null));
		return "questionAnalysis/cluster";
	}

	@RequestMapping(path = "/cluster", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
	public String cluster(Integer id) throws Exception {
        FindPlagiarismAlgorithm algorithm = new FindPlagiarismAlgorithm();
        TestInfo selectTestInfo = new TestInfo();
        selectTestInfo.setQid(id);
        return algorithm.cluster(id, dao.selectTestInfo(selectTestInfo));
	}

}