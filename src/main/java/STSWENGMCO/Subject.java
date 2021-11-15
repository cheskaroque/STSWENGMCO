package STSWENGMCO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.notBlank;

//[ ] A section has a subject. (just 1 subject, not 'block section")
//[X] A subject is identified by its alphanumeric,Subject ID
//[ ] A student cannot enlist in two sections with the same subject.
//[ ] A subject may or may not have one or more prerequisite subjects.
//[ ] A student may not enlist in a section if the student has not yet taken the prerequisite subjects.

public class Subject {
    private String subjectId;
    private int isOneSubject;
    private boolean getSubj;

    public Subject(String nameOfSubject, String subjectId){
        notBlank(nameOfSubject, "Please provide subject name. Subject name cannot be left blank");
        Validate.isTrue(StringUtils.isAlphanumeric(subjectId),"Subject Name must be in alphanumeric, was: " + subjectId);

        this.subjectId = subjectId;
        this.isOneSubject = isOneSubject;
    }

    @Override
    public String toString() {
        return subjectId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return subjectId != null ? subjectId.equals(subject.subjectId) : subject.subjectId == null;
    }

    @Override
    public int hashCode() {
        return subjectId != null ? subjectId.hashCode() : 0;
    }

}
