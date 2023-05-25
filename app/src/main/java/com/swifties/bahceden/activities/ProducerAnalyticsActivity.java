package com.swifties.bahceden.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swifties.bahceden.R;
import com.swifties.bahceden.adapters.AnalyticsProductAdapter;
import com.swifties.bahceden.databinding.ActivityProducerAnalyticsBinding;

public class ProducerAnalyticsActivity extends AppCompatActivity {

    RecyclerView analyticsProductRV;
    RecyclerView.Adapter<AnalyticsProductAdapter.ViewHolder> analyticsProductAdapter;
    RecyclerView.LayoutManager analyticsProductLM;

    Spinner spinner1;
    Spinner spinner2;

    TextView totalText;
    TextView averageText;
    ActivityProducerAnalyticsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProducerAnalyticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.producerAnalyticsBackButton.setOnClickListener(view -> ProducerAnalyticsActivity.super.onBackPressed());

        totalText = binding.producerAnalyticsTotalText;
        averageText = binding.producerAnalyticsAverageText;

        analyticsProductRV = binding.producerAnalyticsRV;
        analyticsProductRV.setHasFixedSize(true);
        analyticsProductLM = new LinearLayoutManager(this);
        analyticsProductRV.setLayoutManager(analyticsProductLM);
        analyticsProductAdapter = new AnalyticsProductAdapter();
        analyticsProductRV.setAdapter(analyticsProductAdapter);

        spinner1 = binding.producerAnalyticsSpinner1;
        spinner2 = binding.producerAnalyticsSpinner2;

        ArrayAdapter<CharSequence> spinner1Adapter = ArrayAdapter.createFromResource(this, R.array.analytics_spinner_1, android.R.layout.simple_spinner_dropdown_item);
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinner1Adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                switch (selectedItem) {
                    case "This Month":
                        break;
                    case "Last Month":
                        break;
                    case "This Year":
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                switch (selectedItem) {
                    case "Earnings":
                        totalText.setText("Total : 1500$");
                        averageText.setText("Average : 46$");
                        break;
                    case "Quantity":
                        totalText.setText("Total : 58");
                        averageText.setText("Average : 16");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2 = findViewById(R.id.producerAnalyticsSpinner2);

        ArrayAdapter<CharSequence> spinner2Adapter = ArrayAdapter.createFromResource(this, R.array.analytics_spinner_2, android.R.layout.simple_spinner_dropdown_item);
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinner2Adapter);
    }
}