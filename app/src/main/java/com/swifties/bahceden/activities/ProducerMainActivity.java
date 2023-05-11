package com.swifties.bahceden.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.swifties.bahceden.R;
import com.swifties.bahceden.fragments.ProducerAddProductFragment;
import com.swifties.bahceden.fragments.ProducerDataFragment;
import com.swifties.bahceden.fragments.ProducerHomeFragment;
import com.swifties.bahceden.fragments.ProducerOrdersFragment;
import com.swifties.bahceden.fragments.ProducerProfileFragment;

public class ProducerMainActivity extends AppCompatActivity {

    BottomNavigationView producerBottomNav;

    ProducerProfileFragment producerProfileFragment;
    ProducerOrdersFragment producerOrdersFragment;
    ProducerHomeFragment producerHomeFragment;
    ProducerAddProductFragment producerAddProductFragment;
    ProducerDataFragment producerDataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_main);

        producerBottomNav = findViewById(R.id.producerBottomNavBar);

        producerProfileFragment = new ProducerProfileFragment();
        producerHomeFragment = new ProducerHomeFragment();
        producerDataFragment = new ProducerDataFragment();
        producerOrdersFragment = new ProducerOrdersFragment();
        producerAddProductFragment = new ProducerAddProductFragment();

        //        Set HomeFragment as main fragment
        replaceFragment(producerHomeFragment);

//        Set listener for navBar
        producerBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.producerHome:
                        replaceFragment(producerHomeFragment);
                        return true;

                    case R.id.producerAdd:
                        replaceFragment(producerAddProductFragment);
                        return true;

                    case R.id.producerData:
                        replaceFragment(producerDataFragment);
                        return true;

                    case R.id.producerOrder:
                        replaceFragment(producerOrdersFragment);
                        return true;

                    case R.id.producerProfile:
                        replaceFragment(producerProfileFragment);
                        return true;
                }

                return false;
            }
        });
    }

    //    Helper method to replace fragments
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.producerMainFragmentContainer, fragment).commit();
    }
}