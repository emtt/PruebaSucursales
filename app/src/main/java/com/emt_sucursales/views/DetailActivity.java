package com.emt_sucursales.views;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emt_sucursales.R;
import com.emt_sucursales.brcoredata.model.Sucursales;
import com.emt_sucursales.databinding.ActivityDetailBinding;
import com.emt_sucursales.viewmodel.DetailActivity_vm;
import com.emt_sucursales.viewmodel.DetailActivity_vm_factory;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity implements LifecycleOwner {
    private ActivityDetailBinding activityDetailBinding;
    private LifecycleRegistry mLifecycleRegistry;
    private DetailActivity_vm viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("sucursal");
        Sucursales sucursal = gson.fromJson(strObj, Sucursales.class);

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        DetailActivity_vm_factory factory = new DetailActivity_vm_factory(sucursal);
        viewModel = ViewModelProviders.of(this, factory).get(DetailActivity_vm.class);
        activityDetailBinding.setViewModel(viewModel);
    }
}
