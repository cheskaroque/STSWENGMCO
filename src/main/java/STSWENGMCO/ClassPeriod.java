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
        if (start.isBefore(periodStart) ) {
            throw new ScheduleConflictException("Time not within valid start time: " + start);
        } else if (end.isAfter(periodEnd)) {
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

        if ((start.getMinute() == 30 || start.getMinute() == 0)) {
           throw new ScheduleConflictException("Time not increment of 30 in start time: " +start.toString() + " and  end time: " + end.toString() );
        }

        LocalTime compareTime = end.minusMinutes(30);

        //Check if there is a 30 minute increment on the periods
        if (compareTime.isBefore(start) == false) {
            throw new ScheduleConflictException("Period does not have a 30 minute increment duration or more with the start time at" + start.toString() +"and end time at" + end.toString() );
        }


    }

    public void checkSame(LocalTime start1, LocalTime start2, LocalTime end1, LocalTime end2){

        // validate
        if (end1.isBefore(start1)) {
            throw new ScheduleConflictException("end1 " + end1 + " must not be before begin1 " + start1);
        }

        // validate
        if (end2.isBefore(start2)) {
            throw new ScheduleConflictException("end2 " + end2 + " must not be before begin2 " + start2);
        }

        if (end2.isAfter(start1) && end1.isAfter(start2)) {
            throw  new ScheduleConflictException("Schedule overlaps in time " + end1 + " and " + end2);
        } else {

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