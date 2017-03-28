package paydaycards.models.cards;

import java.util.ArrayList;
import java.util.Random;

public class CardPile {
    private ArrayList<Card> cards;
    private ArrayList<Card> discarded;
    
    /**
     * constructor
     * creates 2 new arrayLists , one for the cards and one for the discarded cards
     * PostCondition: a CardPile object
     */
    public CardPile() {
        cards     = new ArrayList<>();
        discarded = new ArrayList<>();
    }
    
    public ArrayList<Card> getCards(){ return cards; }
    public ArrayList<Card> getDiscarded(){ return discarded; }
    
    /**
     * PreCondition: ** card is not null, and is not already contained in the list.
     * PostCondition: the card was successfully added to the card list.
     * @param card 
     */
    public void addCard(Card card) {
        cards.add(card);
    }
    
    /**
     * PreCondition: that there is at least 2 cards to shuffle
     * PostCondition: cardpile has been suffled
     */
    public void shuffle() {
        for(int i = 1; i<100; i++){
            Random rn = new Random();
            int j = rn.nextInt(cards.size()) ;
            int k = rn.nextInt(cards.size()) ;
            Card temp = cards.get(j);
            cards.set(j, cards.get(k));
            cards.set(k, temp);
        }
    }
    
    /**
     * PreCondition: none
     * PostCondition: successfully draws a card
     * @return card
     */
    public Card drawCard() {
        if (cards.isEmpty()){
            reshuffle();
        }
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
    
    /**
     * PreConditions: none(?)
     * PostConditions: successfully discards a card
     * @param card 
     */
    public void discardCard(Card card)  {
        discarded.add(card);
    }
    
    /**
     * PreCondition: none
     * PostCondition: re-adds all discards again and shuffles the cards
     */
    public void reshuffle() {
        cards.addAll(discarded);
        discarded.clear();
        shuffle();
    }
}
