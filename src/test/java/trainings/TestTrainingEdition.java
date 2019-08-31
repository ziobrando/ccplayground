package trainings;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TestTrainingEdition {

    private AggregateTestFixture fixture;
    private int capacity;
    private UUID trainingId;
    private TrainingEditionScheduled trainingEditionScheduled;
    private ScheduleTraining scheduleTraining;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(TrainingEdition.class);
        capacity = 20;
        String title = "Amazing training";
        LocalDate date = LocalDate.now().plusMonths(6);
        int duration = 2;
        trainingId = UUID.randomUUID();

        trainingEditionScheduled = new TrainingEditionScheduled(trainingId, capacity, title, date, duration);
        scheduleTraining = new ScheduleTraining(trainingId, capacity, title, date, duration);
    }

    @Test
    @DisplayName("We can schedule a training edition.")
    void canSchedule() {
        fixture.givenNoPriorActivity()
                .when(scheduleTraining)
                .expectEvents(trainingEditionScheduled);
    }

    @Test
    @DisplayName("Placing reservations reduces availability")
    void canPlaceReservation() {
        String firstName = "firstName";
        String lastName = "lastName";
        String email = firstName + "." + lastName + "@testcompany.com";

        PlaceReservation placeReservation = PlaceReservation.forOne(trainingId, firstName, lastName, email);
        Participant[] participants = { new Participant(firstName, lastName, email)};
        ReservationPlaced reservationPlaced = new ReservationPlaced(trainingId, participants, capacity - 1);

        fixture.given(trainingEditionScheduled)
                .when(placeReservation)
                .expectEvents(reservationPlaced);
    }

    @Test
    void canReserveMultipleSeats() {
        Participant[] participants = {
                new Participant("Mario", "Rossi", "mario.rossi@azienda.it"),
                new Participant("Giorgio", "Paletta", "giorgio.paletta@azienda.it")
        };
        PlaceReservation placeReservation = PlaceReservation.forMany(trainingId, participants);
    }

    @Test
    public void cannotExceedCapacity() {

    }

}
