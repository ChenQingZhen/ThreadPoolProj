package com.google.threadpoolproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.threadpoolproj.executor.JobExecutor;
import com.google.threadpoolproj.executor.PrintTask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialWidgets();
        initialData();
    }
    private void initialWidgets(){
        TextView tvProcessor= (TextView) findViewById(R.id.tv_processor);
        tvProcessor.setText("availableProcessors:"+Runtime.getRuntime().availableProcessors()+"\n");
    }

    private void initialData() {
        getData();
    }

    private void getData(){
        Thread t=null;
        for (int i=0;i<10;i++){
            t=new PrintTask();
            JobExecutor.getInstance().execute(t);
        }
        JobExecutor.getInstance().shutdown();
    }
}
