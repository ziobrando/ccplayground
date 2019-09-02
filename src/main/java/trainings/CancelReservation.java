package trainings;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

public class CancelReservation {

    @TargetAggregateIdentifier
    private final UUID trainingId;

    private final ReservationId reservationId;

    public CancelReservation(ReservationId reservationId, UUID trainingId) {
        this.reservationId = reservationId;
        this.trainingId = trainingId;
    }

    public ReservationId getReservationId() {
        return reservationId;
    }

    public UUID getTrainingId() {
        return trainingId;
    }
}
