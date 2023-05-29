package com.swifties.bahceden.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swifties.bahceden.adapters.ProducerOrderAdapter;
import com.swifties.bahceden.data.RetrofitService;
import com.swifties.bahceden.data.apis.CustomerApi;
import com.swifties.bahceden.data.apis.OrderApi;
import com.swifties.bahceden.databinding.FragmentProducerOrdersBinding;
import com.swifties.bahceden.models.Customer;
import com.swifties.bahceden.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProducerOrdersFragment extends Fragment {


    private FragmentProducerOrdersBinding binding;

    private RecyclerView ordersRV;
    private RecyclerView.Adapter<ProducerOrderAdapter.ViewHolder> ordersAdapter;
    private List<Order> orders;

    private int pending = 0;

    private int ongoing = 0;

    private int delivered = 0;

    private int cancelled = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProducerOrdersBinding.inflate(inflater, container, false);


        RetrofitService.getApi(OrderApi.class).getAllOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orders = response.body();
                ordersRV = binding.producerOrdersRV;
                ordersAdapter = new ProducerOrderAdapter(orders, getContext(), inflater);
                ordersRV.setLayoutManager(new LinearLayoutManager(getActivity()));
                ordersRV.setAdapter(ordersAdapter);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getContext(), "There was a problem retrieving your orders", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        // TODO: This is here for testing purposes,
        //  this should be changed to the producer's orders
        RetrofitService.getApi(CustomerApi.class).getCustomerById(1).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                assert response.body() != null;
                orders = response.body().getOrders();
                ordersAdapter.notifyDataSetChanged();
                for (Order order : orders) {
                    if (order.getStatus() == Order.OrderStatus.PENDING) pending++;
                    else if (order.getStatus() == Order.OrderStatus.ONGOING) ongoing++;
                    else if (order.getStatus() == Order.OrderStatus.DELIVERED) delivered++;
                    else if (order.getStatus() == Order.OrderStatus.CANCELLED) cancelled++;
                }
                binding.producerOrdersPendingCount.setText(String.format("%d", pending));
                binding.producerOrdersOngoingCount.setText(String.format("%d", ongoing));
                binding.producerOrdersDeliveredCount.setText(String.format("%d", delivered));
                binding.producerOrdersCancelledCount.setText(String.format("%d", cancelled));

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.e("errorPurposes", t.getMessage());
            }
        });
    }
}