package com.udacity.czziemba.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MovieListing {
    private String posterPath;
    private Boolean adult;
    private String overview;
    private String releaseDate;
    private List<Integer> genreIds = new ArrayList<>();
    private Integer id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backdropPath;
    private Double popularity;
    private Integer voteCount;
    private Boolean video;
    private Double voteAverage;
}
