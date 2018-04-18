package rn.travels.in.rntravels.app;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import rn.travels.in.rntravels.network.ApiClient;
import rn.travels.in.rntravels.network.ApiService;

/**
 * Created by demo on 16/02/18.
 */

public class RNApp extends Application {

    private ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        apiService  = ApiClient.getClient().create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
