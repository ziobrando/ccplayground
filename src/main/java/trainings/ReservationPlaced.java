package trainings;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class ReservationPlaced {
    private final UUID trainingId;
    private final Participant[] participants;
    private final int resultingAvailability;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationPlaced that = (ReservationPlaced) o;
        return resultingAvailability == that.resultingAvailability &&
                trainingId.equals(that.trainingId) &&
                Arrays.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(trainingId, resultingAvailability);
        result = 31 * result + Arrays.hashCode(participants);
        return result;
    }

    public ReservationPlaced(UUID trainingId, Participant[] participants, int resultingAvailability) {
        this.trainingId = trainingId;
        this.participants = participants;
        this.resultingAvailability = resultingAvailability;
    }

    public UUID getTrainingId() {
        return trainingId;
    }

    public int getResultingAvailability() {
        return resultingAvailability;
    }
}
