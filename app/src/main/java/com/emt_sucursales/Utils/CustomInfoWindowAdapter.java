package com.emt_sucursales.Utils;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.emt_sucursales.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Activity context;

    public CustomInfoWindowAdapter(Activity context){
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.customwindow, null);

        TextView txtSucursal = (TextView) view.findViewById(R.id.txtSucursal);
        TextView txtDireccion = (TextView) view.findViewById(R.id.txtDireccion);

        txtSucursal.setText(marker.getTitle());
        txtDireccion.setText(marker.getSnippet());

        return view;
    }
}
