package com.emt_sucursales.brcoredata.rest;

import com.emt_sucursales.brcoredata.Constants;
import com.emt_sucursales.brcoredata.model.Login;
import com.emt_sucursales.brcoredata.model.Sucursales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    /**
     * @param login
     * @return Login
     */
    @POST(Constants.LOGIN)
    Call<Login> login(@Body Login login);

    @GET(Constants.SUCURSALES)
    Call<List<Sucursales>> getSucursales();

}
