package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;

@Controller
public class ProfileController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/profile/{id}")
    public String movie_profile(@PathVariable("id") Integer id, Model model){
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "MovieProfile";
    }

}
