package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping({"/", "/hello"})
    public String greeting(Map<String, Object> map) {
        map.put("title", "realTitle");
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * FROM users");
        map.put("userName", list.get(0).get("UserName"));
        map.put("id", list.get(0).get("ID"));
        map.put("email", list.get(0).get("Email"));

        // return "MainPage";
        return "TestThymeleaf";
    }

}
