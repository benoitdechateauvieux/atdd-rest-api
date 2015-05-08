package org.exoplatform.bch.activity.cucumber;

import org.exoplatform.bch.activity.client.retrofit.ApiRequestInterceptor;
import org.exoplatform.bch.activity.client.retrofit.HttpRestClientException;
import org.exoplatform.bch.activity.client.retrofit.RestClient;
import retrofit.RestAdapter;

/**
 * Created by bdechateauvieux on 5/8/15.
 */
public class BaseStepDefinitions {
    private static final String USERNAME = null;
    private static final String PASSWORD = null;

    private final RestClient activityClient;

    public BaseStepDefinitions() {
        ApiRequestInterceptor requestInterceptor = new ApiRequestInterceptor(USERNAME, PASSWORD);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://localhost:8080")
                .setRequestInterceptor(requestInterceptor)
                .setErrorHandler(cause -> new HttpRestClientException(cause.getResponse().getStatus()))
                .build();
        activityClient = restAdapter.create(RestClient.class);
    }

    protected RestClient getActivityClient() {
        return activityClient;
    }
}
