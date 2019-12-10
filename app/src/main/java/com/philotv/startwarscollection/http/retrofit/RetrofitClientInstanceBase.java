package com.philotv.startwarscollection.http.retrofit;

import com.philotv.startwarscollection.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstanceBase {
    private static Retrofit swapiRetrofitClient;
    private static Retrofit superHeroRetrofitClient;

    public static Retrofit getSwapiRetrofitInstance() {
        if (swapiRetrofitClient == null) {
            swapiRetrofitClient = new retrofit2.Retrofit.Builder()
                    .baseUrl(BuildConfig.APP_BASE_URL_SWAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return swapiRetrofitClient;
    }
    public static Retrofit getsuperHeroRetrofitInstance() {
        if (superHeroRetrofitClient == null) {
            superHeroRetrofitClient = new retrofit2.Retrofit.Builder()
                    .baseUrl(BuildConfig.APP_BASE_URL_SUPER_HERO)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return superHeroRetrofitClient;
    }
}
