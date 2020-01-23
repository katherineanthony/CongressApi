package com.example.tacoapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CongressService {
    String BASE_URL = "https://www.govtrack.us/api/v2/";

    @GET("/v2/role?current=true/role_type={role}&limit=1")
    Call<Congress> getRandomRole(@Query("role_type") String role);
}
