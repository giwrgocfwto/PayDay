package paydaycards.models.cards;

import java.awt.Image;

/**
 * class for the deal cards
 */
public class DealCard implements Card{
    
    private Type type;
    private String message;
    private int cost;
    private int value;
    private Image icon;
    private String choice1,choice2;

    /**
     * creates a new DealCard with all these given parameters
     * @param message
     * @param cost
     * @param value
     * @param icon
     * @param choice1
     * @param choice2 
     */
    public DealCard(String message,int cost, int value, Image icon, String choice1, String choice2){
        this.message = message;
        this.cost = cost;
        this.value = value;
        this.icon = icon;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    
    /**
     * all setters and getters
     * @return 
     */
    public String getChoice1() {
        return choice1;
    }
    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }
    public String getChoice2() {
        return choice2;
    }
    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }
    @Override
    public void setType(Type type) { this.type = type; }
    @Override
    public Type getType() { return type; }
    @Override
    public void setMessage(String message){ this.message = message; }
    @Override
    public String getMessage(){ return message; }
    public void setCost(int cost){ this.cost = cost; }
    public int getCost(){ return cost; }
    public void setValue(int value){ this.value = value; }
    public int getValue(){ return value; }
    @Override
    public void setImage(Image icon){ this.icon = icon; }
    @Override
    public Image getImage(){ return icon; }
    
    @Override
    public String toString(){ return type + message + cost + icon + choice1 + choice2; }
}
