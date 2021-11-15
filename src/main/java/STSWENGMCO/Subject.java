package STSWENGMCO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.notBlank;

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
