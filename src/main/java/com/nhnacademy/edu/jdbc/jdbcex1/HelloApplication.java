package com.nhnacademy.edu.jdbc.jdbcex1;

import com.nhnacademy.edu.jdbc.jdbcex1.model.StudentModel;
import com.nhnacademy.edu.jdbc.jdbcex1.view.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StudentModel model = new StudentModel();
        RootPane rootPane = new RootPane(model);

        Scene scene = new Scene(rootPane);

        stage.titleProperty().bind(model.getFormInstance().titleProperty());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}