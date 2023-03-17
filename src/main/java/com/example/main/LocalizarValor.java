package com.example.main;

import java.io.*;

public class LocalizarValor {

    String valor = null, diretorio = null, linha = null, dir = "A:\\Ulisses\\Projetos de Desenvolvimento\\LocalizeValor\\Historico.txt";

    int qtd = 0;

    LocalizarValor() throws IOException {

        checarArquivos();

        if (qtd == 0) {

            System.out.println("Não existe nenhum arquivo com esse valor \"" + valor + "\".");
        } else {

            System.out.println("\nContém " + qtd + " arquivo(s) nesta valor \"" + valor + "\".");

            DiretorioArmazenado();
        }

        System.out.println("----------------------------------------------------------------------------------\n");
    }

    private void checarArquivos() throws IOException {

        File file = new File(diretorio);
        File[] arquivo = file.listFiles();

        for (int i = 0; i < arquivo.length; ++i) {

            FileReader fr = new FileReader(arquivo[i]);
            BufferedReader br = new BufferedReader(fr);

            linha = br.readLine();

            while (linha != null) {

                if (linha.contains(valor)) {

                    System.out.println(arquivo[i].getName());

                    ++qtd;
                }

                linha = br.readLine();
            }
        }
    }

    public void DiretorioArmazenado() throws IOException {

        String s = diretorio + " -------------- valor: " + valor + "\n";

        File arquivo = new File(dir);
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));

        bw.write(s);
        bw.close();
    }
}
