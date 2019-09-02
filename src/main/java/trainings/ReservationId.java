package trainings;

import java.util.Objects;
import java.util.UUID;

class ReservationId {
    private String reservationId;

    private ReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    static ReservationId generateNew() {
        return new ReservationId(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationId that = (ReservationId) o;
        return reservationId.equals(that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }
}
