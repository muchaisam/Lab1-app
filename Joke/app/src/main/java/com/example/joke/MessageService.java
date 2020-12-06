package com.example.joke;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.TaskStackBuilder;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * * helper methods.
 */
public class MessageService extends IntentService {
    private Handler handler;
    public static final int NOTIFICATION_ID = 1;
    //Declare a constant KEY to pass a message from the Main Activity to the service
    public static final String EXTRA_MESSAGE = "MESSAGE";
    public MessageService() {
        super("MessageService");
        //the constructor is required, don't delete it
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        //Don't delete this method,
        // this method will contains the code we want to run when the service receives an
//        intent
        synchronized (this) {
//            // synchronized() method is Java code which allows us to lock a particular
//            block of code from access by other threads
            try {
                //wait for 10 seconds t
                wait(10000);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
//            //try..catch is Java syntax which allows us to perform code actions on the try
//            block , and catch error exceptions in the the catch block , hence making us able to trace
//            the line of code which has errors during debugging
        }
        //get the text from the intent
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        //call showText method
        showText(text);
    }
    private void showText(final String text) {
        Log.v("DelayedMessageService", "What is the secret of comedy?:" + text);
        // the above line of code logs a piece of text so that we can see it in the logcat
        //post the Toast code to the main thread using the handler post method

        //Intent
        Intent intent = new Intent(this, MainActivity.class);
        //TaskBuilder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        //Pending Intent
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //Notification
        Notification notification = new Notification.Builder(this)
        //this displays a small notification icon-in this case the mipmap called
        //ic_joke_round
                .setSmallIcon(R.mipmap.ic_joke_round)
                //set the title as your application name
                .setContentTitle(getString(R.string.app_name))
                //set the content text
                .setContentText(text)
                //make the notification disappear when clicked
                .setAutoCancel(true)
                //give it a maximum priority to allow peeking
                .setPriority(Notification.PRIORITY_MAX)
                //set it to vibrate to get a large heads-up notification
                .setDefaults(Notification.DEFAULT_VIBRATE)
                //open main activity on clicking the notification
                .setContentIntent(pendingIntent)
                .build();
        //display the notification using the Android notification service
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        //Issue the notification
        assert notificationManager != null;
        notificationManager.notify(NOTIFICATION_ID, notification);

    }
}

