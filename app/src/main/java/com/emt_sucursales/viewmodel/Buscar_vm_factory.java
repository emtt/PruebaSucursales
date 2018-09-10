package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class Buscar_vm_factory extends ViewModelProvider.NewInstanceFactory {

    public Buscar_vm_factory() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new Buscar_vm();
    }
}
