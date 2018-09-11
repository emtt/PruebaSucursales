package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.ViewModel;

import sortingrv.c20.com.coreapp.model.Sucursales;


public class DetailActivity_vm  extends ViewModel {
    public Sucursales sucursal;

    public DetailActivity_vm(Sucursales mSucursal) {
        sucursal = mSucursal;
    }

}
