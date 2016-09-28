package com.udacity.czziemba.popularmovies.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MovieListingResponse {
    private Integer page;
    private List<MovieListing> results;
}
