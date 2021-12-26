package task2;

public class Flashcard {
    private final String term;
    private final String definition;
    private int wrongAnswers;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
        this.wrongAnswers = 0;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void addWrongAnswer() {
        this.wrongAnswers++;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

}
