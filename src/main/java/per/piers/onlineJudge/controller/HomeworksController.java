//package per.piers.onlineJudge.controller;
//
//import java.io.UnsupportedEncodingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import per.mofadeyunduo.teachingTestManager.model.QuestionRank;
//import per.mofadeyunduo.teachingTestManager.service.DataAccess;
//
//@Controller
//public class HomeworksController {
//
//	@Autowired
//	private DataAccess dataAccess;
//
//	private UpdateScoreTask task = new UpdateScoreTask();
//
//	@RequestMapping(path = "/homework", method = RequestMethod.GET)
//	public String homework() {
//		task.updatePracticeScoreAndRank(dataAccess);
//		return "homework";
//	}
//
//	@RequestMapping(path = "/homework/scores", method = RequestMethod.GET)
//	public String scores(Model model) {
//		model.addAttribute("courses", dataAccess.selectCourses());
//		return "scores";
//	}
//
//	@RequestMapping(path = "/homework/scores", method = RequestMethod.POST)
//	public String scores(@RequestParam("name") String name, Model model)
//			throws UnsupportedEncodingException {
//		model.addAttribute("scores", dataAccess.selectScoresByName(name));
//		return "scores";
//	}
//
//	@RequestMapping(path = "/homework/score/{sid}/{name}", method = RequestMethod.GET)
//	public String score(@PathVariable("sid") int sid,
//			@PathVariable("name") String name, Model model) {
//		model.addAttribute("score", dataAccess.selectScores(sid, name));
//		return "score";
//	}
//
//	@RequestMapping(path = "/homework/updateScore/{sid}/{name}", method = RequestMethod.POST)
//	public String updateScore(@PathVariable("sid") int sid,
//			@PathVariable("name") String name,
//			@RequestParam("usualScore") double usualScore,
//			@RequestParam("finalScore") double finalScore, Model model) {
//		dataAccess.updateScore(usualScore, finalScore, sid, name);
//		return "homework";
//	}
//
//	@RequestMapping(path = "/homework/cluster", method = RequestMethod.GET)
//	public String clustering(Model model) {
//		model.addAttribute("questions", dataAccess.selectQuestions());
//		return "cluster";
//	}
//
//	@RequestMapping(path = "/homework/cluster", method = RequestMethod.POST)
//	public String clustering(@RequestParam("qidDotName") String qidDotName,
//			Model model) throws Exception {
//		model.addAttribute("cluster", task.clusterCode(
//				Integer.parseInt(qidDotName.split("\\.")[0]), dataAccess));
//		return "cluster";
//	}
//
//	@RequestMapping(path = "/homework/intervals", method = RequestMethod.GET)
//	public String intervals(Model model) {
//		model.addAttribute("questions", dataAccess.selectQuestions());
//		return "intervals";
//	}
//
//	@RequestMapping(path = "/homework/intervals", method = RequestMethod.POST)
//	public String intervals(@RequestParam("qidDotName") String qidDotName,
//			Model model) {
//		QuestionRank[] ranks = dataAccess
//				.selectRanksByQid(Integer.parseInt(qidDotName.split("\\.")[0]));
//		int[] intervals = new int[5];
//		for (QuestionRank rank : ranks) {
//			if (rank.getCorrectRate() >= 0.9)
//				intervals[4]++;
//			else if (rank.getCorrectRate() > 0.8)
//				intervals[3]++;
//			else if (rank.getCorrectRate() > 0.7)
//				intervals[2]++;
//			else if (rank.getCorrectRate() > 0.6)
//				intervals[1]++;
//			else if (rank.getCorrectRate() < 0.6)
//				intervals[0]++;
//		}
//		model.addAttribute("intervals", intervals);
//		return "intervals";
//	}
//
//}
