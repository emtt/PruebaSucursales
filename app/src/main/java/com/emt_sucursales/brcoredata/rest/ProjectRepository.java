package com.emt_sucursales.brcoredata.rest;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.emt_sucursales.App;
import com.emt_sucursales.brcoredata.Constants;
import com.emt_sucursales.brcoredata.model.Login;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {
    private static String TAG = ProjectRepository.class.getSimpleName();
    private static ProjectRepository projectRepository;
    private APIInterface apiInterface;

    public synchronized static ProjectRepository getInstance() {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository();
            }
        }
        return projectRepository;
    }

    private ProjectRepository() {

        /*
        Comment by Efrain Morales
        We can create an interceptor for check the traffic using HttpLoggingInterceptor
        or ChuckInterceptor which isn't added to this project. So I leave this comment just for info.

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        or

        httpClient.addInterceptor(new ChuckInterceptor(App.getAppContext()));  // <-- this is the important line!
        */
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new ChuckInterceptor(App.getAppContext()));  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        apiInterface = retrofit.create(APIInterface.class);

    }


    public MutableLiveData<Login> login(Login mLogin) {
        final MutableLiveData<Login> data = new MutableLiveData<>();
        apiInterface.login(mLogin).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Log.d(TAG, response.body().toString());
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                } else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                data.postValue(null);
            }
        });

        return data;

    }
}
