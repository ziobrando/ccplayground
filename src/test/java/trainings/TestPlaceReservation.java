package trainings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlaceReservation {

    private UUID trainingId;

    @BeforeEach
    void setUp() {
        trainingId = UUID.randomUUID();
    }

    @Test
    @DisplayName("A single reservation has one seat")
    void testSingleAttendee() {
        String name = "name";
        String surname = "surname";
        String email = "name.surname@azienda.it";

        PlaceReservation single = PlaceReservation.forOne(trainingId, name, surname, email);

        assertEquals(1, single.getSeats());
    }

    @Test
    @DisplayName("A reservation for many has the correct count")
    void testManyAttendees() {

        Participant[] participants = {
                new Participant("Mario", "Rossi", "mario.rossi@email.com"),
                new Participant("Giorgio", "Paletta", "giorgio.paletta@email.com")
        };

        PlaceReservation group = PlaceReservation.forMany(trainingId, participants);
        assertEquals(participants.length, group.getSeats());
    }

    @Test
    @DisplayName("Equals works as expected")
    void testEquals() {
        PlaceReservation single = PlaceReservation.forOne(trainingId, "Gino", "Pilotino", "gino.pilotino@gmail.com");
        PlaceReservation other = PlaceReservation.forOne(trainingId, "Gino", "Pilotino", "gino.pilotino@gmail.com");

        assertEquals(single, other);
    }
}
