package per.piers.onlineJudge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import per.piers.onlineJudge.Exception.CRUDException;
import per.piers.onlineJudge.model.AllScore;
import per.piers.onlineJudge.model.Category;
import per.piers.onlineJudge.model.User;
import per.piers.onlineJudge.util.DataAccessObject;

@RequestMapping(path = "/testManager")
@Controller
public class ScoreManagerController {

    DataAccessObject dao;

    @Autowired
    public ScoreManagerController(DataAccessObject dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/scores", method = RequestMethod.GET)
    public String scores(Model model) {
        model.addAttribute("categories", dao.selectCategory(null));
        return "scoreManager/scores";
    }

    @RequestMapping(path = "/scores", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String scores(Integer id) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User selectUser = new User();
        selectUser.setEmail(email);
        User admin = dao.selectUser(selectUser);
        AllScore[] allScores =  dao.selectPracticeAndUsualScoreFromAdmin(admin.getId(), id);
        StringBuilder builder = new StringBuilder();
        for (AllScore allScore: allScores) {
            builder.append(String.format("%s;%s;%s;%s;\n", allScore.getUser().getName(), allScore.getUser().getEmail(), allScore.getUsualScore(), allScore.getPracticeScore()));
        }
        return builder.toString();
    }

    @RequestMapping(path = "/score/{id}", method = RequestMethod.GET)
    public String score(@PathVariable("id") Integer id, Model model) {
        AllScore selectAllScore = new AllScore();
        selectAllScore.setId(id);
        AllScore[] allScores = dao.selectUsualScore(selectAllScore);
        if (allScores.length != 1) throw new CRUDException("select: allScores.length != 1");
        model.addAttribute("score", allScores[0]);
        return "scoreManager/score";
    }

    @RequestMapping(path = "/score/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String score(@PathVariable("id") Integer id, Double usualScore) {
        AllScore updateAllScore = new AllScore();
        updateAllScore.setId(id);
        updateAllScore.setUsualScore(usualScore);
        int line = dao.updateUsualScore(updateAllScore);
        if (line != 1) throw new CRUDException("update: allScores.length != 1");
        return "SUCCESS";
    }

}
