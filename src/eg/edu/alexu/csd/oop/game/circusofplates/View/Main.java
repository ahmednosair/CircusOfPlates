package eg.edu.alexu.csd.oop.game.circusofplates.View;

import eg.edu.alexu.csd.oop.game.circusofplates.Model.CustomLog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Winnie and Tigger");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);
        CustomLog.getInstance().getL().info("Program interface started");
    }


    public static void main(String[] args) {

          launch(args);
    }
}
