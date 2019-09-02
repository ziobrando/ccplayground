package trainings;

import java.util.UUID;

class TrainingEditionReservationCanceled {

    private final ReservationId reservationId;
    private final UUID trainingId;
    private final int resultingAvailability;

    public TrainingEditionReservationCanceled(ReservationId reservationId, UUID trainingId, int resultingAvailability) {

        this.reservationId = reservationId;
        this.trainingId = trainingId;
        this.resultingAvailability = resultingAvailability;
    }

    ReservationId getReservationId() {
        return reservationId;
    }

    public UUID getTrainingId() {
        return trainingId;
    }

    int getResultingAvailability() {
        return resultingAvailability;
    }
}
