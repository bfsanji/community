package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+" "+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            //h获取响应输出流
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //GET请求
    // /students?current=1&limit=20
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@RequestParam(name="current",required = false,defaultValue = "1") int current,
                             @RequestParam(name="limit",required = false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }

    //将请求参数编排到请求路径中，如根据id获取某学生信息
    // /student/123
    @RequestMapping(path="/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //Post请求
    @RequestMapping(path="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应html数据
    @RequestMapping(path="/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","张三");
        mv.addObject("age",30);
        mv.setViewName("/demo/view");
        return mv;
    }
    //另一种返回页面的方式
    @RequestMapping(path="/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北大");
        model.addAttribute("age",80);
        return "/demo/view";
    }

    //响应JSON数据(异步请求)
    // Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path="/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",230);
        map.put("salary",8000);
        return map;
    }

    @RequestMapping(path="/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",230);
        map.put("salary",8000);
        maps.add(map);
        map = new HashMap<>();
        map.put("name","张si");
        map.put("age",23);
        map.put("salary",2000);
        maps.add(map);
        return maps;
    }

    //cookie示例

    @RequestMapping(path="/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        //设置cookie生效范围
        cookie.setPath("/community/alpha");
        //设置cookie生存时间
        cookie.setMaxAge(60*10);
        //发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path="/cookie/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }

    //session示例
    @RequestMapping(path="/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","test");
        return "set session";
    }

    @RequestMapping(path="/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

    //ajax示例
    @RequestMapping(path = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0,"操作成功");
    }

}
