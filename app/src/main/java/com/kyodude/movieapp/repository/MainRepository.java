package com.kyodude.movieapp.repository;

import android.util.Log;

import com.kyodude.movieapp.model.api.ApiService;
import com.kyodude.movieapp.model.dataModel.NowPlayingResponse;
import com.kyodude.movieapp.model.dataModel.NowPlayingResult;
import com.kyodude.movieapp.model.dataModel.TrendingResponse;
import com.kyodude.movieapp.model.dataModel.TrendingResult;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainRepository implements Repository{
    private static final String TAG = "MainRepository";
    private final ApiService api;
    private final MutableLiveData<List<TrendingResult>> trendingList = new MutableLiveData<>();
    private final MutableLiveData<List<NowPlayingResult>> nowPlayingList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    @Inject
    public MainRepository(ApiService api) {
        this.api = api;
    }

    public LiveData<List<NowPlayingResult>> getNowPlayingResult() {
        api.getNowPlayingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getNowPlayingMoviesObserverRx());
        return nowPlayingList;
    }

    public LiveData<List<TrendingResult>> getTrendingResult() {
        api.getTrendingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getTrendingMoviesObserverRx());
        return trendingList;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public Observer<NowPlayingResponse> getNowPlayingMoviesObserverRx() {
        return new Observer<NowPlayingResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                isLoading.setValue(true);
            }

            @Override
            public void onNext(@NonNull NowPlayingResponse apiResponse) {
                nowPlayingList.postValue(apiResponse.getResults());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                nowPlayingList.postValue(null);
                Log.d(TAG, "onFailure: failed to fetch now playing movies from server");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                isLoading.setValue(false);
            }
        };
    }

    public Observer<TrendingResponse> getTrendingMoviesObserverRx() {
        return new Observer<TrendingResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                isLoading.setValue(true);
            }

            @Override
            public void onNext(@NonNull TrendingResponse apiResponse) {
                trendingList.postValue(apiResponse.getTrendingResults());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                trendingList.postValue(null);
                Log.d(TAG, "onFailure: failed to fetch trending movies from server");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                isLoading.setValue(false);
            }
        };
    }
}
