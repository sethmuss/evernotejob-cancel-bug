#API 23: Cancel periodic job failure if job is running
When a periodic job is running, JobManager::cancelAll() does not prevent it from running in the future. 

##Steps to reproduce
(Reproduced on physical device running API 23, and Nexus 5X emulator running API 23)

1. Launch MainActivity
2. Press "Schedule Job"
3. Wait (10-15 min) for Log statement: 'I/job_demo_tag: Waiting (releasing intrinsic lock)'
4. Press "Cancel Jobs, Then notify() so job can finish"
5. Notice job is rescheduled.
6. Wait (10-15 min), notice job runs again.

## Annotated log sample:

07-26 09:26:35.274 3893-3953/com.example.evernoteprob.evernotejobprob I/OpenGLRenderer: Initialized EGL, version 1.4
07-26 09:26:35.315 3893-3893/com.example.evernoteprob.evernotejobprob W/art: Before Android 4.1, method int android.support.v7.widget.ListViewCompat.lookForSelectablePosition(int, boolean) would have incorrectly overridden the package-private method in android.widget.ListView

STARTING UP::::

07-26 09:26:43.362 3893-3893/com.example.evernoteprob.evernotejobprob I/MainActivity: Scheduling job...
07-26 09:26:43.442 3893-3893/com.example.evernoteprob.evernotejobprob I/art: Rejecting re-init on previously-failed class java.lang.Class<com.evernote.android.job.gcm.PlatformGcmService>
07-26 09:26:43.442 3893-3893/com.example.evernoteprob.evernotejobprob I/art: Rejecting re-init on previously-failed class java.lang.Class<com.evernote.android.job.gcm.PlatformGcmService>
07-26 09:26:43.464 3893-3893/com.example.evernoteprob.evernotejobprob D/JobProxy21: Schedule periodic (flex support) jobInfo success, request{id=1, tag=job_demo_tag}, start 00:10:00, end 00:15:00, flex 00:05:00
07-26 09:26:45.237 3893-3954/com.example.evernoteprob.evernotejobprob D/JobRescheduleService: Reschedule 0 jobs of 1 jobs

Waiting for job to run.....

07-26 09:41:42.881 3893-19351/com.example.evernoteprob.evernotejobprob D/PlatformJobService: Run job, request{id=1, tag=job_demo_tag}, waited 00:14:59, interval 00:15:00, flex 00:05:00
07-26 09:41:42.886 3893-19351/com.example.evernoteprob.evernotejobprob I/JobExecutor: Executing request{id=1, tag=job_demo_tag}, context PlatformJobService
07-26 09:41:42.891 3893-19352/com.example.evernoteprob.evernotejobprob I/job_demo_tag: Entering onRunJob
07-26 09:41:42.891 3893-19352/com.example.evernoteprob.evernotejobprob I/job_demo_tag: Waiting (releasing intrinsic lock)

Job is paused, waiting for notify().

07-26 09:42:08.868 3893-3893/com.example.evernoteprob.evernotejobprob I/MainActivity: Cancelling all jobs
07-26 09:42:08.875 3893-3893/com.example.evernoteprob.evernotejobprob I/JobManager: Found pending job request{id=1, tag=job_demo_tag}, canceling
07-26 09:42:08.915 3893-3893/com.example.evernoteprob.evernotejobprob I/JobManager: Cancel running job{id=1, finished=false, result=FAILURE, canceled=false, periodic=true, class=DemoSyncJob, tag=job_demo_tag}
07-26 09:42:08.915 3893-3893/com.example.evernoteprob.evernotejobprob I/MainActivity: Done cancelling all jobs
07-26 09:42:08.915 3893-3893/com.example.evernoteprob.evernotejobprob I/MainActivity: Attempting to acquire intrinsic lock
07-26 09:42:08.916 3893-3893/com.example.evernoteprob.evernotejobprob I/MainActivity: Acquired intrinsic lock, notifying
07-26 09:42:08.916 3893-19352/com.example.evernoteprob.evernotejobprob I/job_demo_tag: Notified (acquired intrinsic lock)
07-26 09:42:08.916 3893-19352/com.example.evernoteprob.evernotejobprob I/JobExecutor: Finished job{id=1, finished=true, result=SUCCESS, canceled=true, periodic=true, class=DemoSyncJob, tag=job_demo_tag}
07-26 09:42:08.916 3893-3893/com.example.evernoteprob.evernotejobprob D/PlatformJobService: Called onStopJob for job{id=1, finished=true, result=SUCCESS, canceled=true, periodic=true, class=DemoSyncJob, tag=job_demo_tag}
07-26 09:42:08.918 3893-19351/com.example.evernoteprob.evernotejobprob D/PlatformJobService: Finished job, request{id=1, tag=job_demo_tag} SUCCESS
07-26 09:42:08.957 3893-19351/com.example.evernoteprob.evernotejobprob D/JobProxy21: Schedule periodic (flex support) jobInfo success, request{id=1, tag=job_demo_tag}, start 00:10:00, end 00:15:00, flex 00:05:00

Job was cancelled, but rescheduled.

07-26 09:53:23.413 3893-30731/com.example.evernoteprob.evernotejobprob D/PlatformJobService: Run job, request{id=1, tag=job_demo_tag}, waited 00:11:14, interval 00:15:00, flex 00:05:00
07-26 09:53:23.414 3893-30731/com.example.evernoteprob.evernotejobprob I/JobExecutor: Executing request{id=1, tag=job_demo_tag}, context PlatformJobService
07-26 09:53:23.420 3893-30733/com.example.evernoteprob.evernotejobprob I/job_demo_tag: Entering onRunJob
07-26 09:53:23.420 3893-30733/com.example.evernoteprob.evernotejobprob I/job_demo_tag: Waiting (releasing intrinsic lock)

Job ran again.

