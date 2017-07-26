#Cancel periodic job failure if running
When a periodic job is running, JobManager::cancelAll() does not prevent it from running in the future. 

##Steps to reproduce

1. Launch MainActivity
2. Press "Schedule Job"
3. Wait (10-15 min) for Log statement: 
4. Press "Cancel Jobs, Then notify() so job can finish"
5. Notice job is rescheduled:
6. Wait (10-15 min), notice job runs again:


## Annotated log sample:


## Other info
I'm running this from Android Studio 2.3.3. On an Android 6.0.1 device (API 23)

