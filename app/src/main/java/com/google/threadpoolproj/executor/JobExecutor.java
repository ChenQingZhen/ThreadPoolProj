package com.google.threadpoolproj.executor;

import android.os.Process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqingzhen on 2016/6/21.
 */
public class JobExecutor extends ThreadPoolExecutor{
    private static int NUMBER_OF_CORES=Runtime.getRuntime().availableProcessors()-1;
    private static int KEEP_ALIVE_TIME=1;
    private static TimeUnit KEEP_ALIVE_TIME_UNIT;

    private static JobExecutor mInstance;

    static {
        KEEP_ALIVE_TIME_UNIT=TimeUnit.SECONDS;
    }

    public static JobExecutor getInstance(){
        if(mInstance==null){
            mInstance=new JobExecutor();
        }
        return mInstance;
    }



    private JobExecutor(){
        this(NUMBER_OF_CORES,NUMBER_OF_CORES,KEEP_ALIVE_TIME,KEEP_ALIVE_TIME_UNIT,new LinkedBlockingQueue<Runnable>(),new JobThreadFactory());

    }



    public JobExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }


    private static class JobThreadFactory implements ThreadFactory{
        private static final String THREAD_NAME = "android_";
        private int counter = 0;
        @Override
        public Thread newThread(Runnable runnable) {
            Thread t=new Thread(runnable,THREAD_NAME+counter++);
            t.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
            return t;
        }
    }

}
