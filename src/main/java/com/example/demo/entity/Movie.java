package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Date;
//hhhhhhhhhhhhhhhh
@Entity
@Table(name = "movie")
public class Movie implements Avatar{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column
    private String title;

    @Column
    private Date date;

    @Column
    private String description;

    @Column
    private String region;

    @Column
    private String genre;

    @Column
    private String videotype;

    @Column //省略默认列名就是属性名
    private int rating;

    @Column //省略默认列名就是属性名
    private String comment;

    @Column
    private String avatar;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getVideotype() {
        return videotype;
    }

    public void setVideotype(String videotype) {
        this.videotype = videotype;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;

    }
}
