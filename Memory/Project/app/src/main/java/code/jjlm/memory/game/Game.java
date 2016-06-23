package code.jjlm.memory.game;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

import code.jjlm.memory.MainActivity;
import code.jjlm.memory.R;

/**
 * Created by Jens on 21.06.2016.
 */
public abstract class Game {

    private Set set;
    private ArrayList<DeckCard> cards;
    private int gameState = 0;
    private int defaultCard;
    private int remainingPairs;
    protected Activity activity;

    public Game(Set set, Activity activity) {
        this.set = set;
        this.activity = activity;
        cards = new ArrayList<DeckCard>();
        for(Card c : set.getCards()) {
            for(int i = 0; i < 2; i++) { cards.add(new DeckCard(c)); }
        }

        Collections.shuffle(cards);

        defaultCard = R.drawable.back;
        remainingPairs = set.getCards().size();
    }

    public Set getSet() {
        return set;
    }
    public ArrayList<DeckCard> getDeckCards() { return cards; }

    public void startGame() {
        updateRemaining(remainingPairs);
    }

    private void updateRemaining(int r) {
        TextView remaining = (TextView) activity.findViewById(R.id.text_remaining);
        remaining.setText(activity.getResources().getString(R.string.remaining) + ' ' + ((Integer) r).toString());
    }

    public abstract void endGame();

    public void openCard(DeckCard card) {
        if(!card.state && !card.isFound && gameState != 2) {




            gameState++;
            card.state = true;
            card.getView().setImageResource(card.getCard().getImage());
            if (gameState == 2) {
                DeckCard[] pair = new DeckCard[2];
                int i = 0;
                for (DeckCard c : cards) {
                    if (c.state) {
                        pair[i] = c;
                        i++;
                    }
                }

                openPair(pair[0], pair[1]);
            }
        }
    }
    protected void openPair(DeckCard card1, DeckCard card2) {
        card1.state = false;
        card2.state = false;

        final DeckCard c1 = card1;
        final DeckCard c2 = card2;

        if(card1.card == card2.card) {
            pairFound(card1, card2);
            gameState = 0;
            //playSound(MainActivity.player2);
        } else {
            //playSound(MainActivity.player1);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    c1.getView().setImageResource(defaultCard);
                    c2.getView().setImageResource(defaultCard);
                    gameState = 0;
                }
            }, 1000);
        }
    }

    protected void pairFound(DeckCard card1, DeckCard card2) {
        card1.getView().setImageResource(0);
        card2.getView().setImageResource(0);

        card1.isFound = true;
        card2.isFound = true;
        remainingPairs--;

        if(remainingPairs == 0) {
            endGame();
            return;
        }

        updateRemaining(remainingPairs);
    }

    protected boolean isMatch(DeckCard card1, DeckCard card2) {
        return card1.card == card2.card;
    }

    private void playSound(MediaPlayer mp) {

        mp.start();

    }

    public class DeckCard {
        private Card card;
        private ImageView img;
        public boolean state = false;
        public boolean isFound = false;
        public DeckCard(Card card) {
            this.card = card;
        }

        public ImageView getView() {
            return img;
        }

        public void setView(ImageView view) {
            this.img = view;
        }

        public Card getCard() {
            return card;
        }
    }

}
