package trainings;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


public class TrainingEdition {

    @AggregateIdentifier
    private UUID trainingId;
    private int availability;

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

        if (availability < command.getSeats()) {
            throw new AvailableCapacityExceeded("Reservation exceeding current capacity");
        }

        apply(new ReservationPlaced(
                command.getTrainingId(),
                command.getParticipants(),
                availability - command.getSeats())
        );
    }

    @EventHandler
    public void on(TrainingEditionScheduled event) {
        this.trainingId = event.getTrainingId();
        this.availability = event.getCapacity();
    }

    @EventHandler
    public void on(ReservationPlaced event) {
        this.availability = event.getResultingAvailability();
    }



}
