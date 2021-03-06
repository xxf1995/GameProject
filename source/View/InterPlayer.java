package View;

import Controller.GameController;
import Model.Player;
import Model.PlayerImpl;
import com.sun.org.glassfish.gmbal.GmbalException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by cheny2 on 3/15/17.
 */
public class InterPlayer extends Application {

    static Stage interPlayerStage = new Stage();

    private PlayerImpl currentPlayer;
    private GameController gameController;

    public InterPlayer(GameController controller, PlayerImpl player) {
        currentPlayer = player;
        gameController = controller;
    }

    public Stage getInterPlayerStage() {
        return interPlayerStage;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        interPlayerStage = primaryStage;

        if (currentPlayer != null) {
            VBox popUpRoot = new VBox();
            popUpRoot.setAlignment(Pos.CENTER);
            popUpRoot.setSpacing(20);

            Text instruction = new Text("Now it's turn for " + currentPlayer.getPlayerName() +
                    " to construct new buildings" + "\nYou have 1 minute to construct! Do it quick!");
            instruction.setFont(Font.font(null, 16));
            Button startTimer = new Button("Start Construct");

            popUpRoot.getChildren().addAll(instruction, startTimer);
            Scene popUpScene = new Scene(popUpRoot, 500, 200);
            interPlayerStage.setScene(popUpScene);
            interPlayerStage.show();

            startTimer.setOnAction(event -> {
                ConstructBuilding constructBuilding = new ConstructBuilding(gameController);
                interPlayerStage.close();
                try {
                    constructBuilding.start(ConstructBuilding.constructStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
    }
}
