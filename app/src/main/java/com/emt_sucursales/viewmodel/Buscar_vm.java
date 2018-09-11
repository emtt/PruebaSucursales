package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.emt_sucursales.App;

import java.util.List;

import sortingrv.c20.com.coreapp.model.Sucursales;
import sortingrv.c20.com.coreapp.rest.ProjectRepository;

public class Buscar_vm extends ViewModel {

    public Buscar_vm() {
    }

    public MutableLiveData<List<Sucursales>> getSucursalesByDistance(Double currLat, Double currLng){
        MutableLiveData<List<Sucursales>> sucursales;
        sucursales = ProjectRepository.getInstance(App.getAppContext()).getSucursalesByDistance(currLat, currLng);
        return sucursales;
    }

}
