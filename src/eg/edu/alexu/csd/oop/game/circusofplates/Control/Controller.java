package eg.edu.alexu.csd.oop.game.circusofplates.Control;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.circusofplates.Model.BaseWorld;
import eg.edu.alexu.csd.oop.game.circusofplates.Model.CustomLog;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javax.swing.*;
import java.awt.*;

public class Controller {
    private GameEngine.GameController gameController;
    private JMenuBar menuBar;
    private JMenuItem newEasyMenuItem;
    private JMenuItem newMediumMenuItem;
    private JMenuItem newHardMenuItem;
    private JMenuItem newNightmareMenuItem;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem exitMenuItem;
    public Controller() {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu newMenuItem = new JMenu("New");
        newEasyMenuItem = new JMenuItem("Easy");
        newMediumMenuItem = new JMenuItem("Medium");
        newHardMenuItem = new JMenuItem("Hard");
        newNightmareMenuItem = new JMenuItem("Nightmare");
        pauseMenuItem = new JMenuItem("Pause");
        resumeMenuItem = new JMenuItem("Resume");
        exitMenuItem = new JMenuItem("Exit");
        newMenuItem.add(newEasyMenuItem);
        newMenuItem.add(newMediumMenuItem);
        newMenuItem.add(newHardMenuItem);
        newMenuItem.add(newNightmareMenuItem);
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
    }

    @FXML
    private void exit() {
        CustomLog.getInstance().getL().warning("Exiting");
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void easy() {
        gameController = GameEngine.start("Winnie and Tigger", new BaseWorld(600, 1024, "Easy"), menuBar, Color.BLACK);
        intialize();


    }


    @FXML
    private void medium() {
        gameController = GameEngine.start("Winnie and Tigger", new BaseWorld(600, 1024, "Medium"), menuBar, Color.BLACK);
        intialize();

    }

    @FXML
    private void hard() {
        gameController = GameEngine.start("Winnie and Tigger", new BaseWorld(600, 1024, "Hard"), menuBar, Color.BLACK);
        intialize();

    }

    @FXML
    private void nightmare() {
        gameController = GameEngine.start("Winnie and Tigger", new BaseWorld(600, 1024, "Nightmare"), menuBar, Color.BLACK);
        intialize();

    }

    private void intialize() {
        newEasyMenuItem.addActionListener(e -> gameController.changeWorld(new BaseWorld(600, 1024, "Easy")));
        newMediumMenuItem.addActionListener(e -> gameController.changeWorld(new BaseWorld(600, 1024, "Medium")));
        newHardMenuItem.addActionListener(e -> gameController.changeWorld(new BaseWorld(600, 1024, "Hard")));
        newNightmareMenuItem.addActionListener(e -> gameController.changeWorld(new BaseWorld(600, 1024, "Nightmare")));
        pauseMenuItem.addActionListener(e -> gameController.pause());
        resumeMenuItem.addActionListener(e -> gameController.resume());
        exitMenuItem.addActionListener(e -> exit());
        Platform.exit();
    }

}
