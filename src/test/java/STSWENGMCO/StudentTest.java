package STSWENGMCO;

import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void enlist_two_section_no_conflict(){
        //Given
        Student student = new Student(studentNumber:1);
        Section sec1 = new Section(sectionId: "A", new Schedule(Days.MTH, Period.H0830));
        Section sec2 = new Section(sectionId: "B", new Schedule(Days.TF, Period.H1000));
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
    void enlist_two_sections_same_schedule(){
        //Given
        Student student = new Student(studentNumber: 1, Collections.emptyList());
        Section sec1 = new Section(sectionId: "A", new Schedule(Days.MTH, Period.H0830));
        Section sec2 = new Section(sectionId: "B", new Schedule(Days.MTH, Period.H0830));
        //When
        student.enlist(sec1);
        //Then
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));
    }

}
