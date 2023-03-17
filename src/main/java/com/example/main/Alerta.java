package com.example.main;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Alerta {

    public static void showAlerta(String mensagem, Alert.AlertType tipo) {

        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(mensagem);
        alerta.setTitle(null);
        alerta.show();
    }
}
