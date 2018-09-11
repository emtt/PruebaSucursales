package com.emt_sucursales.views;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.emt_sucursales.R;
import com.emt_sucursales.adapters.SucursalesAdapter;
import com.emt_sucursales.databinding.ActivityBuscarBinding;
import com.emt_sucursales.interfaces.SucursalesAdapterListener;
import com.emt_sucursales.viewmodel.Buscar_vm;
import com.emt_sucursales.viewmodel.Buscar_vm_factory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import sortingrv.c20.com.coreapp.model.Sucursales;

public class BuscarActivity extends AppCompatActivity implements LifecycleOwner, SucursalesAdapterListener {

    private ActivityBuscarBinding activityBuscarBinding;
    private Buscar_vm viewModel;
    private LifecycleRegistry mLifecycleRegistry;
    private ArrayList<Sucursales> sucursales = new ArrayList<>();
    private SucursalesAdapter adapter;
    private Double currLat;
    private Double currLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currLat = Double.valueOf(getIntent().getStringExtra("currLat"));
        currLng = Double.valueOf(getIntent().getStringExtra("currLng"));

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        activityBuscarBinding = DataBindingUtil.setContentView(this, R.layout.activity_buscar);
        Buscar_vm_factory factory = new Buscar_vm_factory();
        viewModel = ViewModelProviders.of(this, factory).get(Buscar_vm.class);
        activityBuscarBinding.setViewModel(viewModel);

        adapter = new SucursalesAdapter(sucursales, this);

        setUpRecycler();

        getData();

        hideKeyboard();

        activityBuscarBinding.txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getData() {
        viewModel.getSucursalesByDistance(currLat, currLng).observe(this, sucursalesObserver);
    }

    private void setUpRecycler() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        activityBuscarBinding.rvSucursales.setLayoutManager(layoutManager);
        activityBuscarBinding.rvSucursales.setAdapter(adapter);
    }


    Observer<List<Sucursales>> sucursalesObserver = new Observer<List<Sucursales>>() {
        @Override
        public void onChanged(@Nullable List<Sucursales> mSucursales) {
            if (mSucursales != null) {
                sucursales.addAll(mSucursales);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onSucursalSelect(Sucursales sucursales) {
        //Log.d("TAG", "Sucursal: " + sucursales.getNOMBRE());

        Gson gson = new Gson();
        Intent intent = new Intent(BuscarActivity.this, DetailActivity.class);
        intent.putExtra("sucursal", gson.toJson(sucursales));
        BuscarActivity.this.startActivity(intent);

    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
