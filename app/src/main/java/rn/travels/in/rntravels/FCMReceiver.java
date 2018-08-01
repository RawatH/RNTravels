package rn.travels.in.rntravels;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.MessageEvent;
import rn.travels.in.rntravels.models.NotificationVO;
import rn.travels.in.rntravels.ui.activity.RootActivity;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;


public class FCMReceiver extends FirebaseMessagingService {

    private static final String TAG = FCMReceiver.class.getSimpleName();
    private static final String CHANNEL_ID = "UPDATE";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "Token: " + s);
        //TODO : SEND TOKEN TO APP SERVER
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(Util.isNotificationEnabled(getApplicationContext())) {
            NotificationVO notificationVO = RNDatabase.getInstance(getBaseContext()).getNotificationDao().getNotification();
            RNDatabase.getInstance(getBaseContext()).getNotificationDao().updateCount(notificationVO.getCount() + 1);
            EventBus.getDefault().post(new MessageEvent(Appconst.MessageEvent.NOTIFICATION_RECEIVED));
            createNotification(remoteMessage);
        }
    }

    private void createNotification(RemoteMessage remoteMessage) {
        createNotificationChannel();

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, RootActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("msg"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setNumber(1)
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancelAll();

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, mBuilder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Up";//getString(R.string.channel_name);
            String description = "desc";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
