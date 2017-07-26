package com.example.evernoteprob.evernotejobprob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.evernote.android.job.JobManager;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.notifyJobButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOGTAG, "Cancelling all jobs");
                JobManager.create(v.getContext()).cancelAll();
                Log.i(LOGTAG, "Done cancelling all jobs");

                Log.i(LOGTAG, "Attempting to acquire intrinsic lock");
                synchronized (DemoSyncJob.LOCK) {
                    Log.i(LOGTAG, "Acquired intrinsic lock, notifying");
                    DemoSyncJob.LOCK.notify();
                }
            }
        });

        findViewById(R.id.scheduleJobButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOGTAG, "Scheduling job...");
                DemoSyncJob.scheduleJob();
            }
        });
    }
}
