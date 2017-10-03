package net.dirox.auluxa.utils;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by trungnq on 21/06/2017.
 */

public class DateTimeUtil {
    public static void getCurrentTime(TextView textView) {
        Calendar currentTime = Calendar.getInstance();
        String timeSet = "";
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int min = currentTime.get(Calendar.MINUTE);

        if (hour > 12) {
            hour -= 12;
            timeSet = "PM";
        } else if (hour == 0) {
            hour += 12;
            timeSet = "AM";
        } else if (hour == 12) {
            timeSet = "PM";
        } else {
            timeSet = "AM";
        }

        String minutes = "";
        if (min < 10)
            minutes = "0" + String.valueOf(min);
        else
            minutes = String.valueOf(min);

        String time = new StringBuilder().append(hour).append(':').append(min)
                .append(" ")
                .append(timeSet).toString();

        textView.setText(time);
    }

    public static void getCurrentDate(TextView textView) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        c.set(year, month, day);

        SimpleDateFormat date = new SimpleDateFormat("MMMM dd, yyyy");
        textView.setText(date.format(c.getTime()));
    }

    public static void getDayOfWeek(TextView textView) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        textView.setText(dayOfTheWeek);
    }
}
