package com.android.example.superheroindex;

import com.android.example.superheroindex.model.MyPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("{super}")
    Call<MyPojo> getHeroes(@Path("super") String superhero);
}