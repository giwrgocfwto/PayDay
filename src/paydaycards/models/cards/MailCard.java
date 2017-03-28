package paydaycards.models.cards;

import java.awt.Image;

/**
 * class for the mail cards
 */
public class MailCard implements Card{
    
    private Type type;
    private String message;
    private String choice;
    private int euro;
    private Image image;
    
    /**
     * creates a new MailCard with all these given parameters
     * @param type
     * @param message
     * @param choice
     * @param euro
     * @param image 
     */
    public MailCard (Type type, String message, String choice, int euro , Image image){
        this.type = type;
        this.message = message;
        this.choice = choice;
        this.euro = euro;
        this.image = image;
    }
    
    
    /**
     * all setters and getters
     * @param type 
     */
    @Override
    public void setType(Type type) { this.type = type; }
    @Override
    public Type getType() { return type; }
    @Override
    public void setMessage(String message){ this.message = message; }
    @Override
    public String getMessage(){ return message; }
    /**
     * Sets the choice text to be displayed.
     * @param choice The message. Must not be null
     */
    public void setChoice(String choice){ this.choice = choice; }
    /**
     * 
     * @return The message
     */
    public String getChoice(){ return choice; }
    @Override
    public void setImage(Image image){ this.image = image; }
    @Override
    public Image getImage(){ return image; }
    public void setEuro(int euro){ this.euro = euro; }
    public int getEuro(){ return euro; }
    
    @Override
    public String toString(){ return type + message + choice + euro + image; }
}
