package trainings;

import java.util.UUID;

class TrainingEditionReservationCanceled {

    private final ReservationId reservationId;
    private final UUID trainingId;
    private final int resultingCapacity;

    public TrainingEditionReservationCanceled(ReservationId reservationId, UUID trainingId, int resultingCapacity) {

        this.reservationId = reservationId;
        this.trainingId = trainingId;
        this.resultingCapacity = resultingCapacity;
    }
}
