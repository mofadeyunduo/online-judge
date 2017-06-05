package per.piers.onlineJudge.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import per.piers.onlineJudge.Exception.CRUDException;
import per.piers.onlineJudge.Exception.ExistenceException;
import per.piers.onlineJudge.Exception.ExpiryException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public String badCredentialsExceptionHandler(Exception e, Model model) {
        model.addAttribute("exception", getExceptionMessage(e));
        return "error/401";
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {ExpiryException.class, IllegalArgumentException.class})
    public String illegalStateExceptionHandler(Exception e, Model model) {
        model.addAttribute("exception", getExceptionMessage(e));
        return "error/403";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(ExistenceException.class)
    public String existenceExceptionHandler(Exception e, Model model) {
        model.addAttribute("exception", getExceptionMessage(e));
        return "error/409";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {CRUDException.class, IOException.class, IllegalStateException.class, MessagingException.class, PersistenceException.class})
    public String CRUDExceptionHandler(Exception e, Model model) {
        model.addAttribute("exception", getExceptionMessage(e));
        return "error/500";
    }

    public String getExceptionMessage(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }

}
