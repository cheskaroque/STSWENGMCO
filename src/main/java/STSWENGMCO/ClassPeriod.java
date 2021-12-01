package STSWENGMCO;

import java.time.LocalTime;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.notNull;

public class ClassPeriod {
    LocalTime start;
    LocalTime end;
    LocalTime periodStart = LocalTime.of(8,29);
    LocalTime periodEnd = LocalTime.of(17,31);

    ClassPeriod(LocalTime start, LocalTime end){
        notNull(start);
        notNull(end);
        checkPeriod(start,end);
        this.start = start;
        this.end= end;
    }

    void checkPeriod(LocalTime start, LocalTime end){

        //Check if within 8:30 am - 5:30 pm
       if (!start.isAfter(periodStart) || !start.isBefore(periodEnd)) {
           throw new ScheduleConflictException("Time not within valid start time: " +
                   start);
       } else if (!end.isBefore(periodEnd) || !end.isAfter(periodStart)) {
           throw new ScheduleConflictException("Time not within valid end time: " +
                   end);
       }

        //Check if end is after start
        if(end.isBefore(start)){
            throw new ScheduleConflictException("End time" + end +" is after start time " +
                    start);
        }

        //Check if start is after end
        if(start.isAfter(end)){
            throw new ScheduleConflictException("Start time" + start +" is after end time " +
                    end);
        }

        //Check if end time is same as start time
        if(end.compareTo(start) == 0){
            throw new ScheduleConflictException("Starting time" + start + " is the same as end time " +
                    end);
        }

        //Check if period begins and end at either top of each hour (10:00, 14:00) or end of each hour (12:30, 16:30)
        if (!(start.getMinute() == 30 || start.getMinute() == 0)) {
            throw new ScheduleConflictException("Time doesn't follow the condition for start time: " +
                    start.toString());
        } else if (!(end.getMinute() == 30 || end.getMinute() == 0)){
            throw new ScheduleConflictException("Time doesn't follow the condition for end time: " +
                    end.toString());
        }


    }

    @Override
    public String toString() {
        return "Start: " + start.toString() + " End: " + end.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassPeriod period = (ClassPeriod) o;
        return Objects.equals(start, period.start) && Objects.equals(end, period.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }


}
