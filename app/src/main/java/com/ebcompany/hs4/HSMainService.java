package com.ebcompany.hs4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class HSMainService extends Service {

    //region constants
    public static final int MY_NOTIFICATION_ID = 1;
    //public static final int SERVERPORT = 8080;
    //endregion
    //************************
    //region instance variables
    private String TAG;
    private Uri soundURI = Uri
            .parse("android.resource://com.ebcompany.hs4/"
                    + R.raw.alarm_rooster);
    private long[] mVibratePattern = { 0, 200, 200, 300 };
    //endregion
    //************************
    //region constructors
    public HSMainService() {
        //TAG = getApplicationContext().getString(R.string.log_message_tag);
    }
    //endregion
    //************************
    //region override methods
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // This is the old onStart method that will be called on the pre-2.0
    // platform.  On 2.0 or later we override onStartCommand() so this
    // method will not be called.
    @Override
    public void onStart(Intent intent, int startId) {
        //handleCommand(intent);
        Log.i(TAG, "onStart");
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //handleCommand(intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Log.i(TAG, "onStartCommand");
        //Intent in = new Intent(getApplicationContext(), TestActivity.class);
        //in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(in);
        //Toast.makeText(getApplicationContext(), "onStartCommand", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Context cntx = getApplicationContext();
        TAG = cntx.getString(R.string.log_message_tag);
        Log.i(TAG, "Service creating");
        final Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext())
                .setContentInfo("HS4")
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(cntx.getString(R.string.notification_content_title))
                .setContentText(cntx.getString(R.string.notification_content_text))
                .setTicker(cntx.getString(R.string.notification_tiker_text))
                .setSound(soundURI)
                .setOngoing(true)
                .setLargeIcon((((BitmapDrawable) this.getResources().getDrawable(R.drawable.fire_eye_alien)).getBitmap()));
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder.setWhen(System.currentTimeMillis());
        mNotificationManager.notify(MY_NOTIFICATION_ID,
                notificationBuilder.build());
//        try {
//            server = HttpServer.create(new InetSocketAddress(SERVERPORT), 0);
//            server.createContext("IS2", new IS2Handler());
//            server.createContext("DSP", new DSPHandler());
//            server.setExecutor(Executors.newFixedThreadPool(0x5) ); // creates a default executor
//            server.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service destroying");
        //server.stop(0x1);
    }
    //endregion
    //************************
}
