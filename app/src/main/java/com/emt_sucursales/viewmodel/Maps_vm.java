package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.emt_sucursales.App;

import java.util.List;

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

}
