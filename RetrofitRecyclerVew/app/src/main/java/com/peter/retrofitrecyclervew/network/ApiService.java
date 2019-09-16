package com.peter.retrofitrecyclervew.network;

import com.peter.retrofitrecyclervew.model.EmployeeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("retrofit-demo.php")
    Call<EmployeeList> getEmployeeData(@Query("company_no") int companyNo);
}
