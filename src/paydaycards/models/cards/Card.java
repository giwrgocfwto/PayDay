package paydaycards.models.cards;

import java.awt.Image;

/**
 * its the interface of the Cards
 */
public interface Card {
    public void setType(Type type);
    public Type getType();
    public void setMessage(String message);
    public String getMessage();
    public void setImage(Image image);
    public Image getImage();
}