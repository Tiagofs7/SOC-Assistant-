package com.socassistant;

import com.socassistant.modelo.Evento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        Path caminho = Path.of("logs/eventos.log");

        try {
            for (String linha : Files.readAllLines(caminho)) {

                String[] partes = linha.split(" ");

                String dataHora = partes[0] + " " + partes[1];
                String tipo = partes[2];
                String usuario = partes[3].split("=")[1];
                String ip = partes[4].split("=")[1];

                Evento evento = new Evento(
                        dataHora,
                        tipo,
                        usuario,
                        ip
                );

                System.out.println(evento);
            }

        } catch (IOException erro) {
		 System.out.println("Erro ao ler o arquivo de log.");
  }
  }
}
