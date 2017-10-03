package net.dirox.auluxa.data.sample;

import java.util.HashMap;

/**
 * Created by an on 6/14/2017.
 */

public class ScheduleItem {

    public String name;
    public String select_hour;
    private HashMap<Day, Boolean> mRepeatDay;

    public ScheduleItem(String name, String select_hour) {
        this.name = name;
        this.select_hour = select_hour;
        mRepeatDay = new HashMap<>();
        mRepeatDay.put(Day.MON, false);
        mRepeatDay.put(Day.TUE, false);
        mRepeatDay.put(Day.WED, false);
        mRepeatDay.put(Day.THU, false);
        mRepeatDay.put(Day.FRI, false);
        mRepeatDay.put(Day.SAT, false);
        mRepeatDay.put(Day.SUN, false);
    }

    public boolean repeatDay(Day day) {
        return mRepeatDay.get(day).booleanValue();
    }

    public boolean toggleRepeatDay(Day day) {
        boolean oldValue = mRepeatDay.get(day).booleanValue();
        mRepeatDay.put(day, !oldValue);
        return !oldValue;
    }

    public enum Day {
        MON("MON"),
        TUE("TUE"),
        WED("WED"),
        THU("THU"),
        FRI("FRI"),
        SAT("SAT"),
        SUN("SUN");

        final String mValue;

        Day(String value) {
            mValue = value;
        }

        public String toString() {
            return mValue;
        }
    }
}
