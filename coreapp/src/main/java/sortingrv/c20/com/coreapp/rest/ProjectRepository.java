package sortingrv.c20.com.coreapp.rest;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sortingrv.c20.com.coreapp.Constants;
import sortingrv.c20.com.coreapp.model.Login;
import sortingrv.c20.com.coreapp.model.Sucursales;

public class ProjectRepository {
    private static String TAG = ProjectRepository.class.getSimpleName();
    private static ProjectRepository projectRepository;
    private APIInterface apiInterface;
    private Context context;

    public synchronized static ProjectRepository getInstance(Context mContext) {

        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository(mContext);
            }
        }
        return projectRepository;
    }

    private ProjectRepository(Context mContext) {
        this.context = mContext;
        /**
         Comment by Efrain Morales
         We can create an interceptor for check the traffic using HttpLoggingInterceptor
         or ChuckInterceptor which isn't added to this project. So I leave this comment just for info.
         HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
         logging.setLevel(HttpLoggingInterceptor.Level.BODY);
         or
         httpClient.addInterceptor(new ChuckInterceptor(App.getAppContext()));  // <-- this is the important line!
         **/
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new ChuckInterceptor(context));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    public MutableLiveData<List<Sucursales>> getSucursales() {
        final MutableLiveData<List<Sucursales>> data = new MutableLiveData<>();
        apiInterface.getSucursales().enqueue(new Callback<List<Sucursales>>() {
            @Override
            public void onResponse(Call<List<Sucursales>> call, Response<List<Sucursales>> response) {
                Log.d(TAG, " Total :" + response.body().size());
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                } else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Sucursales>> call, Throwable t) {
                data.postValue(null);
            }
        });

        return data;
    }

    /**
     * Prueba con RX
     */

    public Observable<List<Sucursales>> getSucursales_RX(){
        return apiInterface.getSucursalesRX();
    }




    /*
    public Disposable getSucursalesRX() {
        return apiInterface.getSucursalesRX()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);

    }

    public List<Sucursales> handleResponse(List<Sucursales> sucursalesList) {
        return sucursalesList;
    }

    private void handleError(Throwable error) {
        //Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
*/

    public MutableLiveData<List<Sucursales>> getSucursalesByDistance(final Double currLat, final Double currLng) {
        final MutableLiveData<List<Sucursales>> data = new MutableLiveData<>();
        apiInterface.getSucursales().enqueue(new Callback<List<Sucursales>>() {
            @Override
            public void onResponse(Call<List<Sucursales>> call, Response<List<Sucursales>> response) {
                if (response.isSuccessful()) {

                    for (Sucursales s : response.body()) {
                        Double distancia = meterDistanceBetweenPoints(Float.valueOf("" + currLat), Float.valueOf("" + currLng),
                                Float.valueOf(s.getLatitud()), Float.valueOf(s.getLongitud()));

                        s.setDistancia(distancia);
                    }
                    Collections.sort(response.body(), new Comparator<Sucursales>() {
                        @Override
                        public int compare(Sucursales lhs, Sucursales rhs) {
                            return lhs.getDistancia().compareTo(rhs.getDistancia());
                        }
                    });

                    data.postValue(response.body());

                } else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Sucursales>> call, Throwable t) {
                data.postValue(null);
            }
        });

        return data;
    }

    private double meterDistanceBetweenPoints(float lat_a, float lng_a, float lat_b, float lng_b) {
        float pk = (float) (180.f / Math.PI);

        float a1 = lat_a / pk;
        float a2 = lng_a / pk;
        float b1 = lat_b / pk;
        float b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
    }
}