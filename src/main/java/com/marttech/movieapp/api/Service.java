package com.marttech.movieapp.api;

import com.marttech.movieapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by izow on 11/27/17.
 */

public interface Service {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

}
