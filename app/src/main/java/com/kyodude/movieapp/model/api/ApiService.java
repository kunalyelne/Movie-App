package com.kyodude.movieapp.model.api;

import com.kyodude.movieapp.model.dataModel.MovieDetailResponse;
import com.kyodude.movieapp.model.dataModel.NowPlayingResponse;
import com.kyodude.movieapp.model.dataModel.TrendingResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("movie/now_playing")
    Observable<NowPlayingResponse> getNowPlayingMovies();

    @GET("trending/movie/day")
    Observable<TrendingResponse> getTrendingMovies();

    @GET("movie/{movie_id}")
    Observable<MovieDetailResponse> getMovieDetails(@Path("movie_id") int id);
}
