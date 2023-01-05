package com.cyquen.employee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面模板控制器
 */
@Controller
public class PageController {

    /**
     * 登录页
     *
     * @return 登录页面
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 主页
     *
     * @return 主页
     */
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * 员工日常分析页
     *
     * @return 员工日常分析页面
     */
    @GetMapping("/daily")
    public ModelAndView dailyAnalysis() {
        return new ModelAndView("dailyAnalysis");
    }

    @GetMapping("/employee")
    public ModelAndView employeeAnalysis() {
        return new ModelAndView("employeeAnalysis");
    }
}
