# Modelling Story

## Training Aggregate

### Storing state

An interesting thing to notice when testing in an Event-Driven fashion, is that it usually takes _one more test_ to be completely sure about the internals of an aggregate.
Keep that in mind: an aggregate is a solid building block as long as the te set of commands and outgoing events is  tested as a whole. But ...we are learning as we go, so that's fine.

### Cancelling a reservation

The moment we enter the `Cancel Reservation` scenario, we realize that we have to find a way to retrieve the pending
reservation in order to cancel the right one.

There may be two way to do it:

1. Every reservation has an ID, but its lifecycle is entirely contained inside the `TrainingEdition` (we can still make sure it's a Value Object)
2. The `Reservation` becomes an independent Aggregate, and we'll need a policy to keep the two different perspectives in sync.

There are pros and cons for both scenarios: the former feels simple and better protect the aggregate invariants, while the latter is interesting exactly fo0r the opposite reason: it allows us to model situations where having two reservations that exceed the class capacity is a viable scenario (two salespeople talking with two customers at the same time, maybe)

### Visualizing outcome