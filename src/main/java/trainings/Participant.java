package trainings;

import java.util.Objects;

/**
 * Probably too early to say whether we need a ValueObject for a Participant
 * but, nothing wrong in making it immutable.
 */
public class Participant {
    private final String firstName;
    private final String familyName;
    private final String email;

    Participant(String firstName, String familyName, String email) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return firstName.equals(that.firstName) &&
                familyName.equals(that.familyName) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, familyName, email);
    }
}
