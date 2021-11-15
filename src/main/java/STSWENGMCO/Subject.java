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
    private String nameOfSubject;
    private int isOneSubject;

    public Subject(String nameOfSubject, int isOneSubject){
        notBlank(nameOfSubject, "Please provide subject name. Subject name cannot be left blank");
        Validate.isTrue(StringUtils.isAlphanumeric(nameOfSubject),"Subject Name must be in alphanumeric, was: " + nameOfSubject);

        this.nameOfSubject = nameOfSubject;
        this.isOneSubject = isOneSubject;
    }

}
