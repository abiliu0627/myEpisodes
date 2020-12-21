package com.example.demo.Repository;

import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findTop10ByOrderByIdDesc();
    List<Movie> findByOrderById();
}
