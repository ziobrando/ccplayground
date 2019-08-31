package trainings;

import java.time.LocalDate;
import java.util.UUID;


/**
 * @DomainEvent just in case-
 */
public class TrainingEditionScheduled {

    private final UUID trainingId;
    private final int capacity;
    private final String title;
    private final LocalDate date;
    private final int duration;

    public TrainingEditionScheduled(UUID trainingId, int capacity, String title, LocalDate date, int duration) {
        this.trainingId = trainingId;
        this.capacity = capacity;
        this.title = title;
        this.date = date;
        this.duration = duration;
    }

    public UUID getTrainingId() {
        return trainingId;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }
}
