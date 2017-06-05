package per.piers.onlineJudge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import per.piers.onlineJudge.model.User;
import per.piers.onlineJudge.util.DataAccessObject;
import per.piers.onlineJudge.util.ExcelUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

@Controller
@RequestMapping("/testManager")
public class UserImportController {

    private DataAccessObject dao;

    @Autowired
    public UserImportController(DataAccessObject dao) {
        this.dao = dao;
    }

    @RequestMapping("/import/user")
    public String importUser() {
        return "import/user";
    }

    @RequestMapping(path = "/import/user", method = RequestMethod.POST)
    public String importResult(@RequestPart("usersFile") MultipartFile usersFile, HttpServletRequest request, Model model) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/") + "/tmp/" + usersFile.getOriginalFilename();
        File file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();
        usersFile.transferTo(file);
        ExcelUtil excelUtil = new ExcelUtil();
        HashSet<String> emails = excelUtil.readColumns(file, "用户邮箱");
        try {
            if (emails == null) {
                model.addAttribute("failure", "读取列用户邮箱出错，可能是没有列用户邮箱");
            } else {
                User selectUser = new User();
                selectUser.setEmail(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
                Integer uidAdmin = dao.selectUser(selectUser).getId();
                HashMap<String, String> status = dao.importUser(emails, uidAdmin);
                StringBuilder builder = new StringBuilder();
                for (String key : status.keySet()) {
                    builder.append(String.format("%s,%s\n", key, status.get(key)));
                }
                model.addAttribute("success", builder.toString());
            }
        } catch (Exception e) {
            model.addAttribute("failure", e.getMessage());
        } finally {
            return "import/result";
        }
    }
}