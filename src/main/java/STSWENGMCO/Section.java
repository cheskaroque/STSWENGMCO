package STSWENGMCO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.notBlank;
public class Section {

    private final String sectionId;

    Section (String sectionId) {
        notBlank(sectionId, "sectionId can't be null or whitespace");

        Validate.isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectiond must be alphanumeric, was: " + sectionId
        );


		/*if (!StringUtils.isAlphanumeric(sectionId)) {
			throw new IllegalArgumentException("sectiond must be alphanumeric, was: " + sectionId);
		}*/

        this.sectionId = sectionId;
    }

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