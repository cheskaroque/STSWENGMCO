package STSWENGMCO;

import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {


    @Test
    void enlist_two_section_no_conflict(){

        Subject sub1 =  new Subject("algcm");
        Subject sub2 =  new Subject("ccprog1");


        //Given
        Student student = new Student(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("JK107", 3), new Subject("ccprog2"), Collections.emptyList(),  2);
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H1000), new Room("JK101", 3), new Subject("cssweng"), Collections.emptyList(), 2);
        //When


        student.enlist(sec1);
        student.enlist(sec2);
        //Then
        Collection<Section> sections = student.getSections();
        assertAll(
                () -> assertTrue(sections.containsAll(List.of(sec1, sec2))),
                () -> assertEquals(2, sections.size())
        );


    }

    @Test
    void enlist_two_sections_same_schedule() {
        //Given

        Subject sub1 =  new Subject("algcm");
        Subject sub2 =  new Subject("ccprog1");

        Student student = new Student(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("JK101", 3),new Subject("stsweng"), Collections.emptyList(),  2);
        Section sec2 = new Section("B", new Schedule(Days.MTH, Period.H0830), new Room("JK107", 3),new Subject("stadvdb"), Collections.emptyList(), 2);
        //When
        student.enlist(sec1);
        //Then
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));
    }

    @Test
    void enlist_in_full_cap(){
        // Given
        Subject sub1 =  new Subject("ccprog1");

        Section sec = new Section("A", new Schedule(Days.MTH,Period.H0830), new Room("JK101", 5),new Subject("ccprog2"), Collections.emptyList(), 2);

        Student student1 = new Student(1);
        Student student2 = new Student(2);
        Student student3 = new Student(3);
        Student student4 = new Student(4);
        Student student5 = new Student(5);

        // When
        student1.enlist(sec);
        student2.enlist(sec);
        student3.enlist(sec);
        student4.enlist(sec);
        student5.enlist(sec);


        Student newStudent = new Student(6);

        // Then
        assertThrows(Exception.class,() -> newStudent.enlist(sec));
    }

    @Test
    void cancel_enlist_section(){
        //Given

        Subject sub1 =  new Subject("algcm");
        Subject sub2 =  new Subject("ccprog1");

        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("JK101", 3),new Subject("ccprog2"), Collections.emptyList(),2);
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H1000), new Room("JK107", 3),new Subject("ccprog3"), Collections.emptyList(),  2);
        Student student =  new Student(1);
        //When
        student.enlist(sec1);
        student.enlist(sec2);
        //Then
        assertAll(
                () -> student.cancelEnlist(sec1),
                () -> student.cancelEnlist(sec2)
        );
    }

    @Test
    void enlist_section_with_slots_left(){
        // Given

        Subject sub1 =  new Subject("ccprog1");

        Section sec = new Section("A", new Schedule(Days.TF,Period.H1430), new Room("JK101", 7),new Subject("ccprog2"), Collections.emptyList(),  2);

        Student student1 = new Student(1);
        Student student2 = new Student(2);
        Student student3 = new Student(3);
        Student student4 = new Student(4);

        // When
        student1.enlist(sec);
        student2.enlist(sec);
        student3.enlist(sec);
        student4.enlist(sec);

        Student newStudent = new Student(5);
        newStudent.enlist(sec);

        Student newStudent2 = new Student(6);
        newStudent2.enlist(sec);

        // Then
        assertAll(() -> sec.getRoom().checkRoomCapacity());
    }

    @Test
    void enlist_students_at_capacity_in_two_sections_sharing_the_same_room() {
        // Given 2 sections that share same room w/ capacity 1, and 2 students

//        Subject sub1 =  new Subject("algcm");
//        Subject sub2 =  new Subject("ccprog1");

        final int CAPACITY = 1;
        Room room = new Room("SEC11", CAPACITY);
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("JK107", 3), new Subject("ccprog2"), Collections.emptyList(), 2);
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H0830), new Room("JK101", 3), new Subject("csarch1"), Collections.emptyList(), 2);
        Student student1 = new Student(1);
        Student student2 = new Student(2);
        // When each student enlists in a different section
        student1.enlist(sec1);
        student2.enlist(sec2);
        // No exception should be thrown
    }

    @Test
    void enlist_section_prereqs_taken(){
        Subject prereq1 = new Subject("prereq1");
        Subject prereq2 = new Subject("prereq2");
        Subject subject = new Subject("subject", List.of(prereq1, prereq2));
        Subject otherSubject = new Subject("otherSubject");
        List<Subject> subjectsTaken = List.of(prereq1, prereq2, otherSubject);
        Student student = new Student(1);
        Section sec = new Section("A", new Schedule(Days.TF,Period.H1430), new Room("JK101", 7),new Subject("ccprog2"), Collections.emptyList(),  1);
        // When student enlists
        student.enlist(sec);
        // Then enlistment is successful
        Collection<Section> sections = student.getSections();
        assertAll(
                () -> assertTrue(student.getSections().contains(sec)),
                () -> assertEquals(2, sec.getNumberOfStudents())
        );
    }

    @Test
    void enlist_section_prereq_missing(){
        Subject prereq1 = new Subject("prereq1");
        Subject prereq2 = new Subject("prereq2");
        Subject prereq3 = new Subject("prereq3");
        Subject prereq4 = new Subject("prereq4");
        Subject subject = new Subject("subject", List.of(prereq1, prereq2, prereq3, prereq4));
        Subject othersubject = new Subject("othersubject");
        List<Subject> FinishedSubjects = List.of(prereq1, prereq2, othersubject);
        Student student = new Student(1);
        Section sec = new Section("A", new Schedule(Days.TF,Period.H1430), new Room("JK101", 7),subject, FinishedSubjects, 1);

        assertThrows(PreReqMissingException.class,
                () -> student.enlist(sec));
    }
}
