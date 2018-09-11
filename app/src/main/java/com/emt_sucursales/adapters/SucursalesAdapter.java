package com.emt_sucursales.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import com.emt_sucursales.R;
import com.emt_sucursales.databinding.ItemSucursalBinding;
import com.emt_sucursales.interfaces.SucursalesAdapterListener;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import sortingrv.c20.com.coreapp.model.Sucursales;

public class SucursalesAdapter extends RecyclerView.Adapter<SucursalesAdapter.ItemHolder> implements Filterable {
    private List<Sucursales> sucursales;
    private List<Sucursales> sucursalesFiltered;
    private SucursalesAdapterListener sucursalesAdapterListener;

    public SucursalesAdapter(List<Sucursales> mSucursales, SucursalesAdapterListener mSucursalesAdapterListener) {
        this.sucursales = mSucursales;
        this.sucursalesFiltered = mSucursales;
        this.sucursalesAdapterListener = mSucursalesAdapterListener;
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
        binding.setSucursal(sucursalesFiltered.get(position));

        holder.binding.contactCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("TAG", sucursalesFiltered.get(position).getNOMBRE());
                //mContactoItemListener.Onclick(sucursales.get(position));
                sucursalesAdapterListener.onSucursalSelect(sucursalesFiltered.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sucursalesFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    sucursalesFiltered = sucursales;
                } else {
                    List<Sucursales> filteredList = new ArrayList<>();
                    for (Sucursales row : sucursales) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNOMBRE().toLowerCase().contains(charString.toLowerCase()) || row.getDOMICILIO().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    sucursalesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = sucursalesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                sucursalesFiltered = (ArrayList<Sucursales>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public static class ItemHolder extends RecyclerView.ViewHolder {
        ItemSucursalBinding binding;

        public ItemHolder(ItemSucursalBinding mBinding) {
            super(mBinding.contactCard);
            this.binding = mBinding;
        }
    }
}
