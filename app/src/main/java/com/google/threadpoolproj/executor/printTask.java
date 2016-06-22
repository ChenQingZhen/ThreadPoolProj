package com.google.threadpoolproj.executor;

import android.util.Log;

/**
 * Created by chenqingzhen on 2016/6/21.
 */
public class PrintTask extends Thread{
    private static int i;


    @Override
    public void run() {
        i++;
        Log.d("ttt","第"+i+"个线程 "+Thread.currentThread().getName());
    }
}
