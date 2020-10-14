package bowlinggame;

public class Game {

    private int itsCurrentFrame = 1;

    private boolean firstThrowInFrame = true;

    private Scorer itsScorer = new Scorer();


    public int score() {
        return scoreForFrame(getCurrentFrame());
    }

    public int scoreForFrame(int theFrame) {
        return itsScorer.scoreForFrame(theFrame);
    }

    public int getCurrentFrame() {
        return itsCurrentFrame;
    }

    public void add(int pins) {
        itsScorer.addThrows(pins);
        adjustCurrentFrame(pins);
    }

    public void add(int[] pines) {
        for (int pins : pines) {
            add(pins);
        }
    }

    private void adjustCurrentFrame(int pins) {
        if (lastBallInFrame(pins)) {
            advanceFrame();
        } else {
            firstThrowInFrame = false;
        }
    }

    private void advanceFrame() {
        itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);
    }

    private boolean strike(int pins) {
        return firstThrowInFrame && pins == 10;
    }

    private boolean lastBallInFrame(int pins) {
        return strike(pins) || !firstThrowInFrame;
    }
}
