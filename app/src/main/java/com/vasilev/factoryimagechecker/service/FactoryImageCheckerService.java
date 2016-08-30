package com.vasilev.factoryimagechecker.service;

import android.app.NotificationManager;
import android.support.v7.app.NotificationCompat;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.vasilev.factoryimagechecker.R;
import com.vasilev.factoryimagechecker.util.CheckerTags;
import com.vasilev.factoryimagechecker.util.PageChecker;

public final class FactoryImageCheckerService extends GcmTaskService {

    private static final String URL = "https://developers.google.com/android/nexus/images";

    @Override
    public int onRunTask(TaskParams taskParams) {
        final String tag = taskParams.getTag();
        final String toCheck = CheckerTags.match(tag);

        final boolean result = PageChecker.fromUrl(URL, page -> page.contains(toCheck));

        if (result) {
            final NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("Nougat goodies");
            builder.setContentText("Go grab that tasty treat for your " + toCheck.substring(0, toCheck.length() - 1));
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder.setDefaults(NotificationCompat.DEFAULT_ALL);

            final int notificationId = 1997;

            final NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId, builder.build());
        }
        return GcmNetworkManager.RESULT_SUCCESS;
    }
}
