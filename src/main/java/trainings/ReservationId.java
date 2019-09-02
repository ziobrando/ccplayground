package trainings;

import java.util.UUID;

class ReservationId {
    private String reservationId;

    private ReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    static ReservationId generateNew() {
        return new ReservationId(UUID.randomUUID().toString());
    }
}
