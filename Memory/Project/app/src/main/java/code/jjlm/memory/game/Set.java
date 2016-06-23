package code.jjlm.memory.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jens on 21.06.2016.
 */
public class Set implements Serializable {

    private boolean defaultSet = false;
    private String name;
    private ArrayList<Card> cards;

    public Set(String name) {
        this.name = name;
        cards = new ArrayList<Card>();
    }

    public Set(String name, boolean defaultSet) {
        this(name);
        this.defaultSet = defaultSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public ArrayList<Card> getDeck() {
        return getCards(); // TODO: Duplicate and shuffle
    }

}
