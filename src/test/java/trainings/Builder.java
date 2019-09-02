package trainings;

public class Builder {

    static Participant participant() {
        return new Participant("Name", "Surname", emailFrom("Name", "Surname"));
    }

    private static String emailFrom(String name, String surname) {
        return name + '.' + surname + "@company.com";
    }

}
