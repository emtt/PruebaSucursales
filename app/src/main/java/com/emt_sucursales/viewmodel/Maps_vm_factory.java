package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class Maps_vm_factory  extends ViewModelProvider.NewInstanceFactory {
    public Maps_vm_factory() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new Maps_vm();
    }
}
