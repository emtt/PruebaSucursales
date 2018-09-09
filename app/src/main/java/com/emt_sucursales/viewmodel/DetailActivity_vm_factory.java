package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.emt_sucursales.brcoredata.model.Sucursales;

public class DetailActivity_vm_factory extends ViewModelProvider.NewInstanceFactory {
    Sucursales sucursales;
    public DetailActivity_vm_factory(Sucursales mSucursales) {
        sucursales = mSucursales;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DetailActivity_vm(sucursales);
    }
}
