package pl.android.client.util;

import android.text.Html;
import android.widget.Toast;
import android.app.Application;

public final class NotificationUtil {

    public static void showErrorNotification(Application activity, String msg) {

        Toast.makeText(activity, Html.fromHtml("<font color=\"red\"><big><b>" + msg + "</b></big></font>"), Toast.LENGTH_SHORT).show();
    }

    public static void showSuccessNotification(Application activity, String msg) {

        Toast.makeText(activity, Html.fromHtml("<font color=\"green\"><big><b>" + msg + "</b></big></font>"), Toast.LENGTH_SHORT).show();
    }
}
