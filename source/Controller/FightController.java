package Controller;
//controller for running the main battle loop at the start of a fight scene.
//Stores a list of players.


import Model.*;
import View.InterPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.time.Duration;
import java.util.List;

public class FightController implements FightControllerInterface {
    private List<PlayerImpl> players;


    public FightController() {
        return;
    }

    public void setPlayers(List<PlayerImpl> players) {
        this.players = players;
    }

    public AnimationTimer runFight(CombatManager manager, GraphicsContext graphics, GraphicsContext graphics1) {
        System.out.println(players.get(0).getxOffset());
        System.out.println(players.get(1).getxOffset());
        System.out.println(players.get(0).getyOffset());
        System.out.println(players.get(1).getyOffset());


        for (int i = 0; i < players.size(); i += 1) {
            PlayerImpl curPlayer = players.get(i);
            for (int j = 0; j < curPlayer.getBuildings().size(); j = j + 1) {
                BuildingImpl building = curPlayer.getBuildings().get(j);
                building.spawnMinion(manager);
            }
        }

//        for (int q = 0; q < players.size(); q = q + 1) {
//            for (int w = 0; w < players.size(); w = w + 1) {
//                if ((q != w) && (players.get(q).getyOffset() == players.get(w).getyOffset())) {
//                    players.get(q).setOpponent(players.get(w));
//                }
//            }
//        }

        for (int i = 0; i <players.size()/2; i += 1) {
            players.get(i).setOpponent(players.get(i+players.size()/2));
        }

//        Timeline animationTimeLine = new Timeline();
//
//        animationTimeLine.setCycleCount(Timeline.INDEFINITE);
//
//        animationTimeLine.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(30), new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    Thread.sleep(50);
//                }
//                catch (InterruptedException e) {
//                }
//
//                manager.doCombat(players.get(0), players.get(players.size()/2));
//
//
//                graphics.clearRect(0,0,2000,1000);
//                for (int i = 0; i < manager.getAllInstances().size(); i = i + 1) {
//                    MinionImpl minion = manager.getAllInstances().get(i);
//                    double coords[] = minion.getCoords();
//                    if (minion.master.getTeam() == 1) {
//                        graphics.setFill(Color.RED);
//                        graphics.fillRect(minion.getCoords()[0], minion.getCoords()[1], 30, 4);
//                        graphics.setFill(Color.GREEN);
//                        graphics.fillRect(minion.getCoords()[0], minion.getCoords()[1], 30*minion.getHealthPercent(), 4);
//                    } else {
//                        graphics.setFill(Color.RED);
//                        graphics.fillRect(minion.getCoords()[0]+20, minion.getCoords()[1], 30, 4);
//                        graphics.setFill(Color.GREEN);
//                        graphics.fillRect(minion.getCoords()[0]+20, minion.getCoords()[1], 30*minion.getHealthPercent(), 4);
//                    }
//
//                    minion.sprite.setPos(coords[0], coords[1]);
//                    minion.render(graphics);
//                }
//                graphics.setFill(Color.RED);
//                graphics.fillRect(players.get(0).myKing.kingPos[0] -200, players.get(0).myKing.kingPos[1]+60, 210, 10);
//                graphics.setFill(Color.GREEN);
//                graphics.fillRect(players.get(0).myKing.kingPos[0] -200, players.get(0).myKing.kingPos[1]+60, 210*players.get(0).myKing.getHpPercent(), 10);
//
//                graphics.setFill(Color.RED);
//                graphics.fillRect(players.get(players.size()/2).myKing.kingPos[0] + 50, players.get(players.size()/2).myKing.kingPos[1]+60, 210, 10);
//                graphics.setFill(Color.GREEN);
//                graphics.fillRect(players.get(players.size()/2).myKing.kingPos[0] + 50, players.get(players.size()/2).myKing.kingPos[1]+60, 210*players.get(players.size()/2).myKing.getHpPercent(), 10);
//
//            }
//        }));
//
//        animationTimeLine.play();

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                }

                if (manager.getAllInstances().size() == 0) {

                    InterPlayer interPlayer = new InterPlayer(manager.getCurrentController(), manager.getCurrentController().getPlayers().get(0));
                  manager.getCurrentController().setNumRemainingPlayers(manager.getCurrentController().getNumRemainingPlayers() - 1);
                    try {
                        interPlayer.start(interPlayer.getInterPlayerStage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    manager.getCurrentGameStage().close();
                    this.stop();
                }

//                for (int q = 0; q < players.size(); q = q + 1) {
//                    for (int w = 0; w < players.size(); w = w + 1) {
//                        if ((q != w) && (players.get(q).getyOffset() == players.get(w).getyOffset())) {
//                            manager.doCombat(players.get(q), players.get(w));
//                        }
//                    }
//                }
                manager.doCombat(players.get(0), players.get(players.size()/2));

                /*
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(MinionImpl.class.getResource("MinionGrunt.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                    }
                catch (Exception e) {
                    System.out.println(e);
                    }
                    */

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
                graphics.fillRect(players.get(0).myKing.kingPos[0] -200, players.get(0).myKing.kingPos[1]+60, 210, 10);
                graphics.setFill(Color.GREEN);
                graphics.fillRect(players.get(0).myKing.kingPos[0] -200, players.get(0).myKing.kingPos[1]+60, 210*players.get(0).myKing.getHpPercent(), 10);

                graphics.setFill(Color.RED);
                graphics.fillRect(players.get(players.size()/2).myKing.kingPos[0] + 50, players.get(players.size()/2).myKing.kingPos[1]+60, 210, 10);
                graphics.setFill(Color.GREEN);
                graphics.fillRect(players.get(players.size()/2).myKing.kingPos[0] + 50, players.get(players.size()/2).myKing.kingPos[1]+60, 210*players.get(players.size()/2).myKing.getHpPercent(), 10);

//                for (int j = 0; j < player2.minions.size(); j = j + 1) {
//                    MinionImpl minion = player2.minions.get(j);
//                    double coords[] = minion.getCoords();
//                    minion.sprite.setPos(coords[0], coords[1]);
//                    minion.render(graphics);
//                }

            }
        };

        return animationTimer;
    }

    public void showBuildings(GraphicsContext gc) {

        for (int i = 0; i < players.size(); i = i + 1) {
            PlayerImpl curPlayer = players.get(i);
            for (int j = 0; j < curPlayer.getBuildings().size(); j = j + 1) {
                BuildingImpl building = curPlayer.getBuildings().get(j);
                building.render(gc);
            }
        }
    }
}