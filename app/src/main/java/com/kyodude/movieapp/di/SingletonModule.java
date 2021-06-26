package com.kyodude.movieapp.di;

import com.kyodude.movieapp.model.api.ApiConfig;
import com.kyodude.movieapp.model.api.ApiService;
import com.kyodude.movieapp.repository.MainRepository;
import com.kyodude.movieapp.repository.Repository;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class SingletonModule {

    @Singleton
    @Provides
    ApiService provideApiService() {
        OkHttpClient.Builder oktHttpClient = new OkHttpClient.Builder();
        oktHttpClient.addInterceptor(chain -> {
            Request.Builder request = chain.request().newBuilder();
            HttpUrl originalHttpUrl = chain.request().url();
            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", ApiConfig.API_KEY)
                    .build();
            request.url(url);
            return chain.proceed(request.build());
        });

        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(oktHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    @Singleton
    @Provides
    Repository provideMainRepository(ApiService api) {
        return new MainRepository(api);
    }
}
