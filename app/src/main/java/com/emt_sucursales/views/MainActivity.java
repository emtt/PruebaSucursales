package com.emt_sucursales.views;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.emt_sucursales.R;
import com.emt_sucursales.databinding.ActivityMainBinding;
import com.emt_sucursales.interfaces.OnLoginCallback;
import com.emt_sucursales.viewmodel.Login_vm;
import com.emt_sucursales.viewmodel.Login_vm_factory;

import sortingrv.c20.com.coreapp.model.Login;

public class MainActivity extends AppCompatActivity implements OnLoginCallback, LifecycleOwner {
    String TAG = MainActivity.class.getSimpleName();
    private Login_vm viewModel;
    private LifecycleRegistry mLifecycleRegistry;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Login_vm_factory factory = new Login_vm_factory(this);
        viewModel = ViewModelProviders.of(this, factory).get(Login_vm.class);
        activityMainBinding.setViewModel(viewModel);
    }


    @Override
    public void onValidate(Boolean valid) {
        changeEditables(false);
        if (valid) {

            viewModel.getLogin().observe(this, loginObserver);

        } else {
            Toast.makeText(this, "Por favor, ingresa los datos correctos", Toast.LENGTH_LONG).show();
            viewModel.stopAnim();
            changeEditables(true);
        }

    }

    Observer<Login> loginObserver = new Observer<Login>() {
        @Override
        public void onChanged(@Nullable Login login) {
            Log.d(TAG, "login" + login.toString());
            if (login != null) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(i);
                MainActivity.this.finish();
            }
        }
    };

    private void changeEditables(Boolean status) {
        activityMainBinding.txtPassword.setEnabled(status);
        activityMainBinding.txtUser.setEnabled(status);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }

}
