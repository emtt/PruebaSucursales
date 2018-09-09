package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.emt_sucursales.interfaces.OnLoginCallback;

public class Login_vm_factory extends ViewModelProvider.NewInstanceFactory {
    OnLoginCallback onLoginCallback;
    public Login_vm_factory(OnLoginCallback mOnLoginCallback) {
        onLoginCallback = mOnLoginCallback;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new Login_vm(onLoginCallback);
    }
}
