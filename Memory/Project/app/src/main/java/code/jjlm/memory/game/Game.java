package code.jjlm.memory.game;

import java.util.ArrayList;

/**
 * Created by Jens on 21.06.2016.
 */
public abstract class Game {

    private Set set;
    private ArrayList<DeckCard> cards;
    private int gameState;

    public Game(Set set) {
        this.set = set;
        cards = new ArrayList<DeckCard>();
        for(Card c : set.getCards()) {
            for(int i = 0; i < 2; i++) { cards.add(new DeckCard(c)); }
        }
    }

    public Set getSet() {
        return set;
    }
    public ArrayList<DeckCard> getDeckCards() { return cards; }

    protected abstract void startGame();
    protected abstract void endGame();
    public void openCard(DeckCard card) {
        gameState++;
        card.state = true;
        if(gameState == 2) {
            DeckCard[] pair = new DeckCard[2];
            int i = 0;
            for(DeckCard c : cards) {
                if(c.state) {
                    pair[i] = c;
                    i++;
                }
            }

            openPair(pair[0],pair[1]);

            gameState = 0;
        }
    }
    protected abstract void openPair(DeckCard card1, DeckCard card2);

    public class DeckCard {
        private Card card;
        public boolean state = false;
        public DeckCard(Card card) {
            this.card = card;
        }
    }

}
