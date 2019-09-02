package trainings;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


public class TrainingEdition {

    @AggregateIdentifier
    private UUID trainingId;
    private int availability;
    private HashMap<ReservationId, Reservation> reservations = new HashMap<>();

    TrainingEdition() {
    }

    @CommandHandler
    public TrainingEdition(ScheduleTraining command) {
        // Guard conditions should be here!
        apply(new TrainingEditionScheduled(
                command.getTrainingId(),
                command.getCapacity(),
                command.getTitle(), command.getDate(), command.getDuration()));
    }

    @CommandHandler
    public void placeReservation(PlaceReservation command) {

        if (this.availability < command.getSeats()) {
            throw new AvailableCapacityExceeded("Reservation exceeding current capacity");
        }

        apply(new ReservationPlaced(
                command.getTrainingId(),
                command.getReservationId(),
                command.getParticipants(),
                availability - command.getSeats())
        );
    }

    @CommandHandler
    public void cancelReservation(CancelReservation command) {
        if (reservations.containsKey(command.getReservationId())) {
            throw new RuntimeException("no reservation found with id: " + command.getReservationId());
        } else {
            Reservation reservation = reservations.get(command.getReservationId());
            apply(new TrainingEditionReservationCanceled(
                    command.getReservationId(),
                    command.getTrainingId(),
                    availability + reservation.getSeats()
            ));
        }
    }

    @EventHandler
    public void on(TrainingEditionScheduled event) {
        this.trainingId = event.getTrainingId();
        this.availability = event.getCapacity();
    }

    @EventHandler
    public void on(ReservationPlaced event) {
        this.availability = event.getResultingAvailability();
        this.reservations.put(event.getReservationId(), new Reservation(event.getReservationId(), event.getSeats()));
    }


    private class Reservation {
        private ReservationId id;
        private int seats;

        Reservation(ReservationId reservationId, int seats) {
            id = reservationId;
            this.seats = seats;
        }

        int getSeats() {
            return seats;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Reservation that = (Reservation) o;
            return seats == that.seats &&
                    id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, seats);
        }
    }
}
