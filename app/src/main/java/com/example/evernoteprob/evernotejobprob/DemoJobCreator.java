package com.example.evernoteprob.evernotejobprob;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

class DemoJobCreator implements JobCreator {

    @Override
    public Job create(String tag) {
        switch (tag) {
            case DemoSyncJob.TAG:
                return new DemoSyncJob();
            default:
                return null;
        }
    }
}
