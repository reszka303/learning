package javaStart.task22_Exceptions.exercise2.JS;

public class DuplicateException extends RuntimeException {
    private Participant participant;

    public DuplicateException(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipant() {
        return participant;
    }
}
