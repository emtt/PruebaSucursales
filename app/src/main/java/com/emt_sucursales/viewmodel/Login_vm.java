package com.emt_sucursales.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.emt_sucursales.R;
import com.emt_sucursales.brcoredata.model.Login;
import com.emt_sucursales.brcoredata.rest.ProjectRepository;
import com.emt_sucursales.interfaces.OnLoginCallback;

public class Login_vm extends ViewModel {
    String TAG = Login_vm.class.getSimpleName();
    private String user;
    private String password;
    private OnLoginCallback onLoginCallback;
    private ActionProcessButton btnLogin;

    public Login_vm(OnLoginCallback mOnLoginCallback) {
        onLoginCallback = mOnLoginCallback;
    }

    public void onLogin(final View view) {
        btnLogin = (ActionProcessButton) view.findViewById(R.id.btnLogin);
        btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
        btnLogin.setProgress(1);

        /**
         * AGREGUÉ UN DELAY PARA MOSTRAR EL EFECTO EN EL BOTÓN
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkDataValidity();
            }
        }, 500);
    }

    public LiveData<Login> getLogin() {
        MutableLiveData<Login> mLogin;
        Login l = new Login();
        l.setUser(user);
        l.setPassword(password);
        mLogin = ProjectRepository.getInstance().login(l);
        return mLogin;
    }

    private void checkDataValidity() {
        if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
            onLoginCallback.onValidate(true);
        } else {
            onLoginCallback.onValidate(false);
        }
    }

    public void stopAnim() {
        btnLogin.setProgress(0);
    }

    public TextWatcher getUsuarioTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                user = editable.toString();
            }
        };
    }

    public TextWatcher getPasswordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                password = editable.toString();
            }
        };
    }

}
