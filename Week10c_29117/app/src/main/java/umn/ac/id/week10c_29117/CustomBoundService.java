package umn.ac.id.week10c_29117;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomBoundService extends Service {
    public IBinder customBinder = new CustomLocalBinder();

    public CustomBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return customBinder;
    }

    public class CustomLocalBinder extends Binder {
        CustomBoundService getService() {
            return CustomBoundService.this;
        }
    }

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
        return (dateFormat.format(new Date()));
    }
}