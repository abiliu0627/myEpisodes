package com.example.demo.service;

import com.example.demo.Repository.MovieRepository;
import com.example.demo.entity.Movie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MovieService {
    @Resource
    private
    MovieRepository MovieRepository;

    public Movie findById(Integer id) {
        return MovieRepository.findById(id).orElse(null);
    }
    public void add(Movie Movie) {
        MovieRepository.save(Movie);
    }
    public void edit(Movie Movie) {
        MovieRepository.save(Movie);
    }

    public void delete(Integer id) {
        Movie Movie  = MovieRepository.findById(id).orElse(null);
        if (Movie == null) {
            return;
        }
        MovieRepository.delete(Movie);
    }
    public List<Movie> findByOrderById() {
        return MovieRepository.findByOrderById();
    }

    public List<Movie> findTop10ByOrderByIdDesc() { return MovieRepository.findTop10ByOrderByIdDesc();}

    public List<Movie> searchByTitle(String value) { return MovieRepository.searchByTitle(value); }
}
