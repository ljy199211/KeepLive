package com.example.ljy.keeplive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ljy.keeplive.asynctask.MyTask;

/**
 * 进程保活
 * oom_adj
 * 0---13---15
 * 降低oom_adj来实现
 * 1.通过开启一个不可见的activity
 * 2.通过前台服务
 * cat/proc/pid/oom_adj
 * 进程没有挂掉 保活
 * 挂掉了  拉活
 * java层双进程守护--binder机制
 * NDK层
 * app 进程LocalService     bindService        拉活进程   RemoteService（两个服务都起来）
 * ServiceConnection
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,ForegroundService.class));
        //执行的时候，自动调用
        new MyTask().execute();
    }
}
