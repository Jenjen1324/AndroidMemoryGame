package code.jjlm.memory.game;

/**
 * Created by Jens on 21.06.2016.
 */
public abstract class Game {

    private Set set;

    public Game(Set set) {
        this.set = set;
    }

    public Set getSet() {
        return set;
    }

    protected abstract void startGame();
    protected abstract void endGame();
    protected void openCard(Card card) {}
    protected abstract void openPair(Card card1, Card card2);

}
