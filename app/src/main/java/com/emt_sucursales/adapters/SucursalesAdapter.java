package com.emt_sucursales.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emt_sucursales.R;
import com.emt_sucursales.brcoredata.model.Sucursales;
import com.emt_sucursales.databinding.ItemSucursalBinding;

import java.util.List;

public class SucursalesAdapter extends RecyclerView.Adapter<SucursalesAdapter.ItemHolder> {
    private List<Sucursales> sucursales;

    public SucursalesAdapter(List<Sucursales> mSucursales) {
        this.sucursales = mSucursales;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSucursalBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_sucursal, parent, false);

        return new SucursalesAdapter.ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        ItemSucursalBinding binding = holder.binding;
        binding.setSucursal(sucursales.get(position));

        holder.binding.contactCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", sucursales.get(position).getNOMBRE());
                //mContactoItemListener.Onclick(sucursales.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return sucursales.size();
    }


    public static class ItemHolder extends RecyclerView.ViewHolder {
        ItemSucursalBinding binding;

        public ItemHolder(ItemSucursalBinding mBinding) {
            super(mBinding.contactCard);
            this.binding = mBinding;
        }
    }
}
