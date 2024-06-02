package zw.co.els.englishlearningsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zw.co.els.englishlearningsystem.utils.Config;

import java.io.IOException;

public class EnglishLearningSystemApplication extends Application {

    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the login page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();

            // Set up the login scene
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome Learner");

            // Show the login stage
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
////        Config.inConfig();
////        System.out.println("-------------------------------------------------------------");
//        Config inConfig = new Config();
////        inConfig.inConfig2();
//        inConfig.inConfig3();
        launch(args);
    }
}