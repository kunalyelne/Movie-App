package com.kyodude.movieapp.repository;

import com.kyodude.movieapp.model.dataModel.NowPlayingResult;
import com.kyodude.movieapp.model.dataModel.TrendingResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.rxjava3.core.Observable;

public interface Repository {
    LiveData<List<NowPlayingResult>> getNowPlayingResult();
    LiveData<List<TrendingResult>> getTrendingResult();
}
