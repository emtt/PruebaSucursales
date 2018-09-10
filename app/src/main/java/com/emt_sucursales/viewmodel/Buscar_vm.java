package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.emt_sucursales.brcoredata.model.Sucursales;
import com.emt_sucursales.brcoredata.rest.ProjectRepository;

import java.util.List;

public class Buscar_vm extends ViewModel {

    public Buscar_vm() {
    }

    public MutableLiveData<List<Sucursales>> getSucursalesByDistance(Double currLat, Double currLng){
        MutableLiveData<List<Sucursales>> sucursales;
        sucursales = ProjectRepository.getInstance().getSucursalesByDistance(currLat, currLng);
        return sucursales;
    }

}
