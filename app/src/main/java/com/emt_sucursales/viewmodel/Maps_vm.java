package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.emt_sucursales.brcoredata.model.Sucursales;
import com.emt_sucursales.brcoredata.rest.ProjectRepository;

import java.util.List;

public class Maps_vm extends ViewModel {

    public Maps_vm() {
    }


    public MutableLiveData<List<Sucursales>> getSucursales(){
        MutableLiveData<List<Sucursales>> sucursales;
        sucursales = ProjectRepository.getInstance().getSucursales();
        return sucursales;
    }

}
