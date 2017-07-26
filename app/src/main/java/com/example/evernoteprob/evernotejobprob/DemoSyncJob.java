package com.example.evernoteprob.evernotejobprob;


import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

class DemoSyncJob extends Job {
    static final String TAG = "job_demo_tag";
    static final Object LOCK = new Object();

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Log.i(TAG, "Entering onRunJob");
        synchronized (LOCK) {
            try {
                Log.i(TAG, "Waiting (releasing intrinsic lock)");
                LOCK.wait();
                Log.i(TAG, "Notified (acquired intrinsic lock)");
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted....", e);
            }
        }
        return Result.SUCCESS;
    }

    static void scheduleJob() {
        new JobRequest.Builder(DemoSyncJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setPersisted(true)
                .build()
                .schedule();
    }
}