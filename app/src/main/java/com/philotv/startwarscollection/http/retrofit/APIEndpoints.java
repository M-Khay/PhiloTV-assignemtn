package com.philotv.startwarscollection.http.retrofit;

import com.philotv.startwarscollection.data.SWCharacter;
import com.philotv.startwarscollection.data.SWCharacterDetail;
import com.philotv.startwarscollection.data.SWCharacterImage;
import com.philotv.startwarscollection.data.SWCharacterList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIEndpoints {

         @GET("/api/people/?")
         Call<SWCharacterList> getSWCharacter(@Query("search") String searchByName);


         @GET("/api/planets/?")
         Call<SWCharacterDetail> getSelectedSWCharacterDetail(@Query("") int searchByName);

         @GET("/api.php/10218899145883795/search/{name}")
         Call<SWCharacterImage> fetchCharacterImageUrl(@Path("name") String searchByName);
}
