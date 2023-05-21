package com.swifties.bahceden.data;

import com.swifties.bahceden.activities.IntroActivity;
import com.swifties.bahceden.data.apis.CustomerApi;
import com.swifties.bahceden.models.Customer;
import com.swifties.bahceden.models.Producer;
import com.swifties.bahceden.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthUser {
    private static AuthUser instance = null;
    private User user;
    private AuthUser() {}

    public static AuthUser getInstance() {
        if (instance == null) {
            instance = new AuthUser();
        }
        return instance;
    }

    public void createUser(String email, int userType) {
        if (userType == IntroActivity.CUSTOMER_TYPE)
        {
            RetrofitService.getApi(CustomerApi.class).getCustomerByEmail(email).enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    user = response.body();
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                }
            });
        }
        else if (userType == IntroActivity.PRODUCER_TYPE)
        {
            RetrofitService.getApi(CustomerApi.class).getCustomerByEmail(email).enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                    user = response.body();
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                }
            });
        }
        else
        {
            throw new RuntimeException("userType must be either customer or producer");
        }
    }

    public void deleteUser() {
        user = null;
    }

    public Customer getCustomerInstance() {
        if (user instanceof Customer) {
            return (Customer) user;
        } else {
            return null;
        }
    }

    public static Customer getCustomer()
    {
        return getInstance().getCustomerInstance();
    }

    public Producer getProducerInstance() {
        if (user instanceof Producer) {
            return (Producer) user;
        } else {
            return null;
        }
    }

    public static Producer getProducer()
    {
        return getInstance().getProducerInstance();
    }
}