package com.swifties.bahceden;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerViewProducerActivity extends AppCompatActivity {
    private RecyclerView producerRV;
    private RecyclerView.Adapter ProducerAdapter;
    RecyclerView.LayoutManager producerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_producer);

        producerRV = (RecyclerView) findViewById(R.id.costumerViewProducerProductsRV);
        producerRV.setHasFixedSize(true);
        producerLayoutManager = new GridLayoutManager(this, 2);

        producerRV.setLayoutManager(producerLayoutManager);
        ProducerAdapter = new ProducerAdapter();
        producerRV.setAdapter(ProducerAdapter);
    }
}
