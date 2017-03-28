package paydaycards;

import java.io.IOException;
import paydaycards.controllers.GameController;
import paydaycards.models.GameModel;
import paydaycards.views.GameView;

public class PayDayCards {
    
    /**
     * the main 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        final GameModel model = new GameModel();
        final GameView  view  = new GameView();
        final GameController controller = new GameController(model, view);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.setVisible(true);
                controller.startGame();
            }
        });

    }
}
