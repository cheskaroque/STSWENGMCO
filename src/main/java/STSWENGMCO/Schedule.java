package STSWENGMCO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.Validate.notNull;

class Schedule {
    private final Days days;
    //private final String periodId;
    public static String startTime;
    public static String endTime;
    public Collection<String> periods = new HashSet<>();


    Schedule(Days days, String startTime, String endTime) {
        notNull(days);
        notNull(periods);
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public void checkPeriodDuration(String timeStart, String timeEnd) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(timeStart);
        Date date2 = format.parse(timeEnd);
        long difference = date2.getTime() - date1.getTime();

        // int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(difference);

        long diffMinutes = difference / (60 * 1000) % 60;
        long diffHours = difference / (60 * 60 * 1000) % 24;

        if (diffMinutes < 30 && diffHours < 1) {
            throw new PreReqMissingException(
                    "ERROR! in time " + timeStart + " " + timeEnd);
        }

    }

    @Override
    public String toString() {
        return days + " " + startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return days == schedule.days && Objects.equals(periods, schedule.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(days, periods);
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        return startTime != null ? startTime.equals(schedule.startTime) : schedule.startTime == null;
    }

    @Override
    public int hashCode() {
        int result = days != null ? days.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        return result;
    }
}*/


    enum Days {
        MTH, TF, WS
    }

    enum Period {
        H0830, H1000, H1300, H1430, H1600
    }
}