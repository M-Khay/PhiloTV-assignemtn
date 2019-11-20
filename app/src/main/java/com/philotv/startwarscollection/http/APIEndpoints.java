package com.philotv.startwarscollection.http;

import com.philotv.startwarscollection.data.SWCharacter;
import com.philotv.startwarscollection.data.SWCharacterDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIEndpoints {

         @GET("/people?")
         Call<SWCharacter> getSWCharacter(@Query("search") String searchByName);


         @GET("/people?")
         Call<SWCharacterDetail> getSelectedSWCharacterDetail(@Query("search") String searchByName);

}
