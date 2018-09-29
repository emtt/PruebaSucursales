package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.emt_sucursales.App;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sortingrv.c20.com.coreapp.model.Sucursales;
import sortingrv.c20.com.coreapp.rest.ProjectRepository;

public class Maps_vm extends ViewModel {

    public Maps_vm() {
    }


    public MutableLiveData<List<Sucursales>> getSucursales(){
        MutableLiveData<List<Sucursales>> sucursales;
        sucursales = ProjectRepository.getInstance(App.getAppContext()).getSucursales();
        return sucursales;
    }

    //RX
    public Observable<List<Sucursales>> getSurcursales(){
        return ProjectRepository.getInstance(App.getAppContext()).getSucursales_RX()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
