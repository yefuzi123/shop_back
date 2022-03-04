package com.asjy.userController;

import com.asjy.po.User;
import com.asjy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:zhangjianhe
 * @Data:2022/3/3
 * @Version:1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return allUser;
    }

    @RequestMapping("/userList")
    @ResponseBody
    public String toUserList(Model model) {
        model.addAttribute("name", "tom");
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "userList";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public    List<String> addUser(@Validated User user, BindingResult br) {
        List<ObjectError> allErrors = br.getAllErrors();
        List<String>list=new ArrayList<>();
        for (ObjectError allError : allErrors) {
            String defaultMessage = allError.getDefaultMessage();
            String objectName = allError.getObjectName();
            list.add(defaultMessage+":"+objectName);
            System.out.println(defaultMessage+":"+objectName);
        }
        return  list;
    }
}
