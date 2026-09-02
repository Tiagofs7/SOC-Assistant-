package com.socassistant;

import com.socassistant.modelo.Evento;
import com.socassistant.modelo.Alerta;
import com.socassistant.regras.RegraForcaBruta;
import com.socassistant.regras.RegraSeguranca;
import com.socassistant.regras.RegraSucessoPosFalha;
import com.socassistant.regras.RegraHorarioIncomum;
import com.socassistant.regras.RegraViagemImpossivel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path caminho = Path.of("logs/eventos.log");
        List<Evento> eventos = new ArrayList<>();

        try {
            for (String linha : Files.readAllLines(caminho)) {
                String[] partes = linha.split(" ");
                String dataHora = partes[0] + " " + partes[1];
                String tipo = partes[2];
                String usuario = partes[3].split("=")[1];
                String ip = partes[4].split("=")[1];
                
                eventos.add(new Evento(dataHora, tipo, usuario, ip));
            }
            List<RegraSeguranca> regrasAtivas = new ArrayList<>();
            regrasAtivas.add(new RegraForcaBruta());
            regrasAtivas.add(new RegraSucessoPosFalha());
            regrasAtivas.add(new RegraHorarioIncomum());
            regrasAtivas.add(new RegraViagemImpossivel());
            
            List<Alerta> todosAlertas = new ArrayList<>();
            for (RegraSeguranca regra : regrasAtivas) {
                todosAlertas.addAll(regra.analisar(eventos));
            }

            System.out.println("--- RESULTADO DA ANÁLISE ---");
            if (todosAlertas.isEmpty()) {
                System.out.println("Nenhum comportamento suspeito detectado.");
            } else {
                for (Alerta alerta : todosAlertas) {
                    System.out.println(alerta);
                }
            }

        } catch (IOException erro) {
            System.out.println("Erro ao ler o log.");
        }
    }
}
