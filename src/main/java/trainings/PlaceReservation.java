package trainings;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

class PlaceReservation {

    @TargetAggregateIdentifier
    private UUID trainingId;
    private ReservationId reservationId;
    private Participant[] participants;

    private PlaceReservation(UUID trainingId, ReservationId reservationId, Participant[] participants) {
        this.trainingId = trainingId;
        this.reservationId = reservationId;
        this.participants = participants;
    }

    /**
     * A factory method to create a reservation for a simple participant. Sounded a little better than new.
     * @param trainingId
     * @param reservationId the unique id for this specific reservation.
     * @param name
     * @param surname
     * @param email
     * @return
     */
    static PlaceReservation forOne(UUID trainingId, ReservationId reservationId, String name, String surname, String email) {
        Participant[] list = { new Participant(name, surname, email)};
        return new PlaceReservation(trainingId, reservationId, list);
    }

    static PlaceReservation forMany(UUID trainingId, ReservationId reservationId, Participant[] participants) {
        return new PlaceReservation(trainingId, reservationId, participants);
    }

    UUID getTrainingId() {
        return trainingId;
    }

    int getSeats() {
        return participants.length;
    }

    Participant[] getParticipants() {
        return participants;
    }

    ReservationId getReservationId() { return reservationId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceReservation that = (PlaceReservation) o;
        return trainingId.equals(that.trainingId) &&
                Arrays.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(trainingId);
        result = 31 * result + Arrays.hashCode(participants);
        return result;
    }
}
