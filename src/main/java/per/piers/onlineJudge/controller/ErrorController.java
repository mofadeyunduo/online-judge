package per.piers.onlineJudge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping(path = "/error/401")
    public String error401() {
        return "error/401";
    }

    @RequestMapping(path = "/error/403")
    public String error403() {
        return "error/403";
    }

    @RequestMapping(path = "/error/404")
    public String error404() {
        return "error/404";
    }

    @RequestMapping(path = "/error/409")
    public String error409() {
        return "error/409";
    }

    @RequestMapping(path = "/error/500")
    public String error500() {
        return "error/500";
    }

}
