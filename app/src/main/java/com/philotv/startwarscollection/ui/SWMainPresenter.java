package com.philotv.startwarscollection.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.philotv.startwarscollection.adapter.SWClientAdapter;
import com.philotv.startwarscollection.data.SWCharacterDetail;
import com.philotv.startwarscollection.data.SWCharacterImage;
import com.philotv.startwarscollection.data.SWCharacterList;
import com.philotv.startwarscollection.http.retrofit.APIEndpoints;
import com.philotv.startwarscollection.http.retrofit.RetrofitClientInstanceBase;
import com.philotv.startwarscollection.mvp.BaseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SWMainPresenter implements SWMainContract.Presenter {

    private static final String TAG = SWMainPresenter.class.getSimpleName();
    BaseView<SWMainContract.Presenter> view;
    Retrofit retrofitSwapi;
    Retrofit retrofitSuperHero;
    APIEndpoints swapiAPIEndPoint;
    APIEndpoints superHeroAPIEndPoint;


    public SWMainPresenter(@NonNull BaseView<SWMainContract.Presenter> view) {
        this.view = view;
        view.setPresenter(this);
        start();
    }
    public void start() {
        retrofitSwapi = RetrofitClientInstanceBase.getSwapiRetrofitInstance();
        retrofitSuperHero = RetrofitClientInstanceBase.getsuperHeroRetrofitInstance();
        swapiAPIEndPoint = retrofitSwapi.create(APIEndpoints.class);
        superHeroAPIEndPoint = retrofitSuperHero.create(APIEndpoints.class);
    }

    @Override
    public void getSWCharacterList(@Nullable String characterNamePrefix) {

        if (view instanceof SWMainActivity) {
            Call<SWCharacterList> call = swapiAPIEndPoint.getSWCharacter(characterNamePrefix);
            Log.d(TAG, call.toString());
            call.enqueue(new Callback<SWCharacterList>() {
                @Override
                public void onResponse(Call<SWCharacterList> call, Response<SWCharacterList> response) {
                    Log.d(TAG, "Success");
                    ((SWMainActivity) view).showSWCharacterList(response.body().results);
                }

                @Override
                public void onFailure(Call<SWCharacterList> call, Throwable t) {
                    Log.d(TAG, "Failure :" + t.getMessage());
                }
            });
        }

    }

    @Override
    public void getSelectedSWCharacterDetails(int planetId) {
        if (view instanceof SWCharacterDetailFragment) {
            // planetId = 2 temporary testing purpose.
            planetId = 2;
            Call<SWCharacterDetail> call = swapiAPIEndPoint.getSelectedSWCharacterDetail(planetId);
            call.enqueue(new Callback<SWCharacterDetail>() {
                @Override
                public void onResponse(Call<SWCharacterDetail> call, Response<SWCharacterDetail> response) {
                    Log.d(TAG, "Success");
                    ((SWCharacterDetailFragment) view).showSWCharacterDetail(response.body());
                }

                @Override
                public void onFailure(Call<SWCharacterDetail> call, Throwable t) {
                    Log.d(TAG, "Failure :" + t.getMessage());

                }
            });

        }
    }

    @Override
    public void getCharacterImage(String characterName, final SWClientAdapter adapter) {
        characterName.replace(" ","%20");
        Call<SWCharacterImage> call = superHeroAPIEndPoint.fetchCharacterImageUrl(characterName);
        call.enqueue(new Callback<SWCharacterImage>() {
            @Override
            public void onResponse(Call<SWCharacterImage> call, Response<SWCharacterImage> response) {
                if (response.body() != null && response.body().getResults()!=null)
                    adapter.temp(response.body().getResult(0));
            }

            @Override
            public void onFailure(Call<SWCharacterImage> call, Throwable t) {

            }
        });
    }

}
