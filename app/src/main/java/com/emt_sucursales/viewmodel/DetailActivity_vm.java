package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.emt_sucursales.brcoredata.model.Sucursales;

public class DetailActivity_vm  extends ViewModel {
    public Sucursales sucursal;

    public DetailActivity_vm(Sucursales mSucursal) {
        sucursal = mSucursal;
    }

}
