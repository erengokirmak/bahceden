package com.swifties.bahceden.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swifties.bahceden.R;

public class YourAddressesAdapter extends RecyclerView.Adapter<YourAddressesAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_customer_address_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YourAddressesAdapter.ViewHolder holder, int position) {
        if (position == 0)
        {
            holder.selected.setImageResource(R.drawable.ic_tick_selected);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView selected;

        public ViewHolder(View view) {
            super(view);

            selected = view.findViewById(R.id.customerAddressItemSelected);

            view.setOnClickListener(v -> Toast.makeText(view.getContext(), "BaS:" + getAdapterPosition(), Toast.LENGTH_SHORT).show());
        }
    }
}
