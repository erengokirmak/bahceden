package com.swifties.bahceden.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.swifties.bahceden.activities.ProducerOrderDetailsActivity;
import com.swifties.bahceden.R;
import com.swifties.bahceden.databinding.LayoutCustomerOrderBinding;
import com.swifties.bahceden.databinding.LayoutProducerOrdersItemBinding;
import com.swifties.bahceden.models.Order;

import java.util.List;

public class ProducerOrderAdapter extends RecyclerView.Adapter<ProducerOrderAdapter.ViewHolder> {
    List<Order> orders;
    Context context;
    LayoutInflater inflater;

    public ProducerOrderAdapter(List<Order> orders, Context context, LayoutInflater inflater) {
        this.orders = orders;
        this.context = context;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public ProducerOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutProducerOrdersItemBinding binding = LayoutProducerOrdersItemBinding.inflate(inflater, parent, false);
        return new ProducerOrderAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducerOrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        Picasso.get().load(order.getProduct().getImageURL())
                .into(holder.binding.productImage);
        holder.binding.productName.setText(order.getProduct().getName());
        holder.binding.orderNumber.setText(String.format(context.getResources().getText(R.string.orderWithId).toString(), order.getId()));
        holder.binding.orderDate.setText(order.getDateOfPurchase());
        holder.binding.totalAmount.setText(String.format(context.getResources().getText(R.string.totalCost).toString(), order.getTotalPrice()));
        int color = context.getColor(R.color.black);
        switch (order.getStatus())
        {
            case PENDING:
                color = context.getColor(R.color.orange);
                break;
            case ONGOING:
                color = context.getColor(R.color.eggplant_pink);
                break;
            case CANCELLED:
                color = context.getColor(R.color.plus_green);
                break;
            case DELIVERED:
                color = context.getColor(R.color.minus_red);
                break;
        }
        holder.binding.orderStatus.setText(String.format(context.getResources().getText(R.string.status).toString(), order.getStatus()));
        holder.binding.orderStatus.setTextColor(color);





        holder.binding.changeStatusButton.setOnClickListener(v -> {
            holder.changeStatusButtonsHolder.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.pop);
            holder.changeStatusButtonsHolder.startAnimation(animation);
        });
        holder.changeStatusButtonsHolder.setOnClickListener(v -> holder.changeStatusButtonsHolder.setVisibility(View.GONE));

        holder.binding.detailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProducerOrderDetailsActivity.class);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(orders == null) return 0;
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        View changeStatusButtonsHolder;

        LayoutProducerOrdersItemBinding binding;
        public ViewHolder(LayoutProducerOrdersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
