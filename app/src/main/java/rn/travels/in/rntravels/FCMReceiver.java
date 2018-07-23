package rn.travels.in.rntravels;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FCMReceiver extends FirebaseMessagingService {

    private static final String TAG = FCMReceiver.class.getSimpleName() ;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "Token: " + s);

        //TODO : SEND TOKEN TO APP SERVER
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //TODO : MESSAGE RECEIVED NOW SHOW THE NOTIFICATION
    }


}
