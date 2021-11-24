package STSWENGMCO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.locks.*;

import static org.apache.commons.lang3.Validate.*;

class Section {



    private final String sectionId;
    private final Schedule schedule;
    private Room room;
    private Subject subjectId;
    private int numberOfStudents;
    private Collection<Subject> subjects =new HashSet<>();
    private Collection<Subject> prerequisite =new HashSet<>();
    private final ReentrantLock lock = new ReentrantLock();

    Section (String sectionId, Schedule schedule, Room room, Subject subject,  Collection <Subject> subjects, Collection <Subject> prerequisite, int numberOfStudents) {
        notBlank(sectionId, "sectionId can't be null or whitespace");
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was: " + sectionId
        );

        this.prerequisite = prerequisite;
        this.subjects.addAll(subjects);
        this.sectionId = sectionId;
        this.schedule = schedule;
        this.subjectId = subject;

        this.room = new Room(room.getNameOfRoom(), room.getMaxCapacity());
        isTrue(numberOfStudents >= 0, "numberOfStudents must be non-negative, was: " + numberOfStudents);
        this.numberOfStudents = numberOfStudents;
    }

//    Section(String sectionId, Subject subject, Schedule schedule, Room room, int numberOfStudents){
//        this(sectionId, subject, schedule, room);
//        isTrue(numberOfStudents >= 0, "numberOfStudents must be non-negative, was: " + numberOfStudents);
//        this.numberOfStudents = numberOfStudents;
//    }

    void checkForConflict(Section other) {
        if (this.schedule.equals(other.schedule)) {
            throw new ScheduleConflictException(
                    "schedule conflict between current section" + this
                            + " and new section" +
                            other + "at schedule" + this.schedule);
        }

//        Subject a =  new Subject("ccprog1");
//        Subject b =  new Subject("algcm");
//        subjects.add(a);
//        subjects.add(b);

        if (this.subjectId.equals(other.subjectId)) {
            throw new SubjectConflictException(
                    "SubjectID " +
                            other.subjectId + "  conflict with another section");
        }
    }


    void checkPrereqSubjects(Collection<Subject> subjectsTaken) {
        notNull(subjectsTaken);
        Collection<Subject> copy = new HashSet<>(subjectsTaken);
        subjectId.checkPrerequisites(copy);
    }

    void lock(){
        lock.lock();
    }

    void unlock(){
        lock.unlock();
    }

    void incrementNumberOfStudents(){
        numberOfStudents++;
    }

    public Room getRoom(){ return room; }

    @Override
    public String toString() {
        return sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        return sectionId != null ? sectionId.equals(section.sectionId) : section.sectionId == null;
    }

    @Override
    public int hashCode() {
        return sectionId != null ? sectionId.hashCode() : 0;
    }

}