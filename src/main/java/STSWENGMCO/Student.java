package STSWENGMCO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Student {
    private final int studentNumber;
    private final Collection <Section> sections =new ArrayList<> ();

    Student (int studentNumber, Collection <Section> sections) {
        if (studentNumber < 0) {
            throw new IllegalArgumentException("studentNumber can't be negative, was: " + studentNumber);
        }

        if (sections == null) {
            throw new NullPointerException("sections can't be null");
        }

        this.studentNumber = studentNumber;
        this.sections.addAll(sections);
        this.sections.removeIf(Objects::isNull);
    }

    void enlist  (Section section) {
        if(section == null) {
            throw new NullPointerException ("section can't be null");
        }
        sections.add(section);
    }

    @Override
    public String toString() {
        return "Student #" + studentNumber;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Student student = (Student) o;

        return studentNumber == student.studentNumber;
    }

    @Override
    public int hashCode() {
        return studentNumber;
    }
}
