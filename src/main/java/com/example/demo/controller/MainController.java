package com.example.demo.controller;

import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.example.demo.service.ImgFileService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController extends ImgFileService {
    @Resource
    private MovieService movieService;
    final String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static";

    @GetMapping({"/movies", "/"})
    public String main(Model model) {
        List<Movie> movies = movieService.findByOrderById();
        model.addAttribute("movies", movies);
        return "MainPage";
    }

    @GetMapping("/addmovie")
    public String toAddPage() {
        return "AddMovie";
    }

    @PostMapping("/addmovie")
    public String add_Movie(Movie movie, HttpServletRequest request) throws IOException {

        if (request instanceof MultipartHttpServletRequest) {
            // 转型为MultipartHttpRequest
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartHttpServletRequest.getFile("file");
            processFile(movie, file);
        }
        movieService.add(movie);
        System.out.println("movie added");
        return "redirect:/movies";
    }

    @GetMapping("/movie/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "EditMovie";
    }

//    TODO: handleSearch
    @RequestMapping(value="/search_title",method=RequestMethod.GET)
    public String searchResults(@RequestParam String search, Model model) {
        System.out.println("title: " + search);
        List<Movie> movies = movieService.searchByTitle(search);
        model.addAttribute("movies", movies);
        return "MainPage";
    }

    @PutMapping("/movie/{id}")
    public String update_movie(@PathVariable("id") Integer id, Movie movie, HttpServletRequest request) throws IOException {
        boolean hasFile = false;
        String prevAvatar = movieService.findById(id).getAvatar();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartHttpServletRequest.getFile("file");
            hasFile = processFile(movie, file);
            System.out.println(new Date() + "   ====>   " + "Update Photo Success");

        }
        if(!hasFile) {
            System.out.println("Avatar not found, use prev avatar");
            movie.setAvatar(prevAvatar);
        }
        movieService.edit(movie);
//        System.out.println(new Date() + "   ====>   " + "student info has been updated");
//        System.out.println("- - - - - - - - - - - - - - - - - - - - ");
        return "redirect:/movie/profile/{id}";



    }

    @GetMapping("/movies/{id}")
    public String deleteMovies(@PathVariable("id") Integer id) {
        System.out.println((new Date()
                + "   ====>   "
                + "Found avator path "
                + path + movieService.findById(id).getAvatar()
        ));

        FileSystemUtils.deleteRecursively(new File(path + movieService.findById(id).getAvatar()));
        movieService.delete(id);
//        System.out.println(new Date() + "   ====>   "
//                + "Delete student(id=" + id + ")"
//                + "by using GetMapping");
//        System.out.println("- - - - - - - - - - - - - - - - - - - - ");
        return "redirect:/movies";
    }
}
