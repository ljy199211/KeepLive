package com.example.ljy.keeplive;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

public class ForegroundService extends Service {
    public static final int SERVICE_ID = 1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private class LocalBind extends Binder{

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT < 18){ //4.3以前
            startForeground(SERVICE_ID,new Notification());
        }else if (Build.VERSION.SDK_INT<26){
            startForeground(SERVICE_ID,new Notification());
            startService(new Intent(this,InnerService.class));
        }else {//Android8.0,需要chanel
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null){
                NotificationChannel channel = new NotificationChannel("channel","name",NotificationManager.IMPORTANCE_NONE);
                manager.createNotificationChannel(channel);
                //将service设置成前台服务，android8.0和9.0不一样，9.0以上加一个前台服务权限
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channel");
                startForeground(SERVICE_ID,builder.build());
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 隐藏通知栏消息
     */
    public static class InnerService extends Service{

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID,new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
