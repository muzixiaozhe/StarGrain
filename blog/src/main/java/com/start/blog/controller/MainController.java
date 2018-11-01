package com.start.blog.controller;

import com.start.blog.domain.Authority;
import com.start.blog.domain.User;
import com.start.blog.service.AuthorityService;
import com.start.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 主页控制器
 */
@Controller
public class MainController implements Serializable{

    private static final long serialVersionUID = 2L;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String index() {
        return "redirect:/blogs";
    }

    /**
     * 获取登录界面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登录失败");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册用户
     *
     * @return
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(serialVersionUID));
        user.setEncodePassword(user.getPassword());
        user.setAuthorities(authorities);

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }
}



















