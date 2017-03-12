package Controller;


import Model.CombatManager;
import Model.MinionImpl;
import Model.Player;
import Model.PlayerImpl;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;


public class FightController implements FightControllerInterface {
    private PlayerImpl[] players;


    public FightController() {
        return;
    }

    public void setPlayers(PlayerImpl[] players) {
        this.players = players;
    }

    public void runFight(CombatManager manager, GraphicsContext graphics) {
        PlayerImpl player1 = players[0];
        PlayerImpl player2 = players[1];

        player1.setOpponent(player2);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    System.out.println(e);
                }

                manager.doCombat(player1, player2);
                graphics.clearRect(0,0,2000,1000);
                for (int i = 0; i < manager.getAllInstances().size(); i = i + 1) {
                    MinionImpl minion = manager.getAllInstances().get(i);
                    double coords[] = minion.getCoords();
                    if (minion.master.getTeam() == 1) {
                        graphics.setFill(Color.RED);
                        graphics.fillRect(minion.getCoords()[0], minion.getCoords()[1], 30, 4);
                        graphics.setFill(Color.GREEN);
                        graphics.fillRect(minion.getCoords()[0], minion.getCoords()[1], 30*minion.getHealthPercent(), 4);
                    } else {
                        graphics.setFill(Color.RED);
                        graphics.fillRect(minion.getCoords()[0]+20, minion.getCoords()[1], 30, 4);
                        graphics.setFill(Color.GREEN);
                        graphics.fillRect(minion.getCoords()[0]+20, minion.getCoords()[1], 30*minion.getHealthPercent(), 4);
                    }

                    minion.sprite.setPos(coords[0], coords[1]);
                    minion.render(graphics);
                }
                graphics.setFill(Color.RED);
                graphics.fillRect(player1.myKing.kingPos[0] -200, player1.myKing.kingPos[1]+60, 210, 10);
                graphics.setFill(Color.GREEN);
                graphics.fillRect(player1.myKing.kingPos[0] -200, player1.myKing.kingPos[1]+60, 210*player1.myKing.getHpPercent(), 10);

                graphics.setFill(Color.RED);
                graphics.fillRect(player2.myKing.kingPos[0] + 50, player2.myKing.kingPos[1]+60, 210, 10);
                graphics.setFill(Color.GREEN);
                graphics.fillRect(player2.myKing.kingPos[0] + 50, player2.myKing.kingPos[1]+60, 210*player2.myKing.getHpPercent(), 10);

//                for (int j = 0; j < player2.minions.size(); j = j + 1) {
//                    MinionImpl minion = player2.minions.get(j);
//                    double coords[] = minion.getCoords();
//                    minion.sprite.setPos(coords[0], coords[1]);
//                    minion.render(graphics);
//                }

            }
        }.start();
    }
}