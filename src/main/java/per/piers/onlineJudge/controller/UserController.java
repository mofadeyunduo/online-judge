package per.piers.onlineJudge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import per.piers.onlineJudge.Exception.CRUDException;
import per.piers.onlineJudge.Exception.ExistenceException;
import per.piers.onlineJudge.Exception.ExpiryException;
import per.piers.onlineJudge.model.Sex;
import per.piers.onlineJudge.model.User;
import per.piers.onlineJudge.util.TokenUtil;
import per.piers.onlineJudge.util.DataAccessObject;
import per.piers.onlineJudge.util.MailUtil;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

//TODO: RESTful
@Controller
public class UserController {

    private DataAccessObject dao;

    @Autowired
    public UserController(DataAccessObject dao) {
        this.dao = dao;
    }

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(path = "/user/login/test", method = RequestMethod.POST)
    @ResponseBody
    public String login(String email, String password, Model model) {
        User user = new User();
        user.setEmail(email);
        user = dao.selectUser(user);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "FOUND";
        } else {
            throw new BadCredentialsException("email or password is incorrect");
        }
    }

    @RequestMapping(path = "/user/register", method = RequestMethod.GET)
    public String register() throws MessagingException {
        return "user/registerEmail";
    }

    @RequestMapping(path = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(String email, HttpServletRequest request) throws MessagingException {
        String key = TokenUtil.addURLToken(System.currentTimeMillis(), email);
        String url = request.getScheme() + "://" + request.getServerName() +
                (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + request.getContextPath() + "/user/register/" + key;
        User user = new User();
        user.setEmail(email);
        user = dao.selectUser(user);
        if (user != null) throw new ExistenceException("email");
        MailUtil.sendEmail(email, "Piers 在线评测——注册", url);
        return "SUCCESS";
    }

    @RequestMapping(path = "/user/register/{token}", method = RequestMethod.GET)
    public String register(@PathVariable("token") String token, Model model) {
        String email = TokenUtil.getEmailFromToken(token);
        if (email != null) {
            model.addAttribute("email", email);
            return "user/register";
        } else throw new ExpiryException("expired page: register password");
    }

    @RequestMapping(path = "/user/register/{token}", method = RequestMethod.POST)
    @ResponseBody
    public String register(@PathVariable("token") String token, String email, String password, String name, String sex, Boolean enabled, String role) {
        if (Objects.equals(TokenUtil.getEmailFromToken(token), email)) {
            User user = new User(null, email, password, name.isEmpty() ? null : name, sex.equals("undefined") ? null : Sex.valueOf(sex.toUpperCase()), enabled, role);
            User existedUser = new User();
            existedUser.setEmail(email);
            if (dao.selectUser(existedUser) != null) throw new ExistenceException("email");
            int id = dao.insertUser(user);
            if (id == 0) throw new CRUDException("insert failed: " + user);
            else return "SUCCESS";
        } else {
            throw new ExpiryException("expired page: register password");
        }
    }

    @RequestMapping(path = "/user/information", method = RequestMethod.GET)
    public String information(Model model) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = new User();
        user.setEmail(email);
        user = dao.selectUser(user);
        model.addAttribute("user", user);
        return "user/information";
    }

    @RequestMapping(path = "/user/information", method = RequestMethod.POST)
    @ResponseBody
    public String information(int id, String email, String password, String name, String sex, Boolean enabled, String role) {
        User user = new User(id, email, password, name.isEmpty() ? null : name, sex.equals("undefined") ? null : Sex.valueOf(sex.toUpperCase()), enabled, role);
        int line = dao.updateUser(user);
        if (line == 0) throw new CRUDException("update failed: " + user);
        else if (line > 1) throw new CRUDException("update size > 1" + user);
        else return "SUCCESS";
    }

    @RequestMapping(path = "/user/password", method = RequestMethod.GET)
    public String password() {
        return "user/passwordResetEmail";
    }


    @RequestMapping(path = "/user/password", method = RequestMethod.POST)
    @ResponseBody
    public String password(String email, HttpServletRequest request) throws MessagingException {
        String key = TokenUtil.addURLToken(System.currentTimeMillis(), email);
        String url = request.getScheme() + "://" + request.getServerName() +
                (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + request.getContextPath() + "/user/password/" + key;
        User user = new User();
        user.setEmail(email);
        user = dao.selectUser(user);
        if (user == null) throw new BadCredentialsException("email doesn't exist");
        MailUtil.sendEmail(email, "Piers 在线评测——忘记密码", url);
        return "SUCCESS";
    }

    @RequestMapping(path = "/user/password/{token}", method = RequestMethod.GET)
    public String passwordToken(@PathVariable("token") String token) {
        String email = TokenUtil.getEmailFromToken(token);
        if (email != null) return "user/passwordReset";
        else throw new ExpiryException("expired page: forget password");
    }

    @RequestMapping(path = "/user/password/{token}", method = RequestMethod.POST)
    @ResponseBody
    public String passwordToken(@PathVariable("token") String token, String password) {
        String email = TokenUtil.getEmailFromToken(token);
        if (email != null) {
            User user = new User();
            User existedUser = new User();
            existedUser.setEmail(email);
            user = dao.selectUser(existedUser);
            user.setId(dao.selectUser(existedUser).getId());
            user.setEmail(email);
            user.setPassword(password);
            int line = dao.updateUser(user);
            if (line == 0) throw new CRUDException("update failed: " + user);
            else if (line > 1) throw new CRUDException("update size > 1" + user);
            else return "SUCCESS";
        } else {
            throw new ExpiryException("expired page: forget password");
        }
    }

}
