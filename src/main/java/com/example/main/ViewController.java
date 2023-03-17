package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;

public class ViewController {

    String linha = null, dir = "A:\\Ulisses\\Projetos de Desenvolvimento\\Main\\Historico.txt";

    int qtd = 0;

    @FXML
    private TextField textValor;

    @FXML
    private TextField textDiretorio;

    @FXML
    private TextArea lista = new TextArea();

    @FXML
    public void onBtLocalizarAction() throws IOException {

        textValor.getText();
        textDiretorio.getText();

        checarArquivos();


        if (qtd > 0) {

            lista.appendText("\nContém " + qtd + " arquivo(s) neste valor \"" + textValor.getText() + "\".\n");

            armazenarHistorico();

            lista.appendText("----------------------------------------------------------------------------------\n\n");
        }

        qtd = 0;
    }

    @FXML
    public void onBtHistoricoAction() throws IOException {

        File arquivo = new File(dir);

        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        linha = br.readLine();

        if (linha != null) {

            lista.appendText("Histórico\n\n");

            while (linha != null) {

                lista.appendText(linha + "\n");

                linha = br.readLine();
            }

            lista.appendText("----------------------------------------------------------------------------------\n\n");
        } else {
            Alerta.showAlerta("Não tem nenhum histórico armazenado", Alert.AlertType.WARNING);
        }

        fr.close();
        br.close();
    }

    @FXML
    public void onBTLimparHistoricoAction() throws IOException {

        File arquivo = new File(dir);

        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        linha = br.readLine();

        if (linha != null) {

            BufferedWriter bw = new BufferedWriter(new FileWriter(dir));

            bw.write("");
            bw.close();

            lista.appendText("Histórico apagado.\n");
        }
    }

    @FXML
    public void onBtLimparAction() {

        lista.clear();
    }

    private void checarArquivos() throws IOException {

        File file = new File(textDiretorio.getText());
        File[] arquivo = file.listFiles();

        try {

            for (int i = 0; i < arquivo.length; ++i) {

                FileReader fr = new FileReader(arquivo[i]);
                BufferedReader br = new BufferedReader(fr);

                linha = br.readLine();

                while (linha != null) {

                    if (linha.contains(textValor.getText())) {

                        lista.appendText(arquivo[i].getName() + "\n");

                        ++qtd;
                    }

                    linha = br.readLine();
                }
            }
        } catch (FileNotFoundException f) {

            lista.appendText("Não existe nenhum arquivo com esse valor \"" + textValor.getText() + "\".\n");
        } catch (NullPointerException n) {

            Alerta.showAlerta("Não é possivel localizar com campo(s) em branco", Alert.AlertType.WARNING);
        }
    }

    public void armazenarHistorico() throws IOException {

        String s = textDiretorio.getText() + " -------------- valor: " + textValor.getText() + "\n";

        File arquivo = new File(dir);
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));

        bw.write(s);
        bw.close();
    }
}