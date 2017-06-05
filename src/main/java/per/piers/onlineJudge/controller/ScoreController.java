package per.piers.onlineJudge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import per.piers.onlineJudge.model.TestInfo;
import per.piers.onlineJudge.model.User;
import per.piers.onlineJudge.util.DataAccessObject;

@Controller
@RequestMapping(path = "/score")
public class ScoreController {

    private DataAccessObject dao;

    @Autowired
    public ScoreController(DataAccessObject dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String user(Model model) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = new User();
        user.setEmail(email);
        user = dao.selectUser(user);
        TestInfo[] testInfos = dao.selectAllScore(user.getId());
        model.addAttribute("allScores",dao.selectAllScore(user.getId()));
        model.addAttribute("sumScoreAndRank",dao.selectSumScoreAndRank(user.getId()));
        return "score/user";
    }

}
