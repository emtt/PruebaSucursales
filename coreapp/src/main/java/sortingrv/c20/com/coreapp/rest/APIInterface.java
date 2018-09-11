package sortingrv.c20.com.coreapp.rest;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import sortingrv.c20.com.coreapp.Constants;
import sortingrv.c20.com.coreapp.model.Login;
import sortingrv.c20.com.coreapp.model.Sucursales;

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
