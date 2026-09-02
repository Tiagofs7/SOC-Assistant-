package com.socassistant.regras;

import com.socassistant.modelo.Alerta;
import com.socassistant.modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class RegraHorarioIncomum implements RegraSeguranca {

    private static final int HORA_INICIO = 0; // Meia-noite
    private static final int HORA_FIM = 5;    // 5 da manhã

    @Override
    public List<Alerta> analisar(List<Evento> eventos) {
        List<Alerta> alertas = new ArrayList<>();

        for (Evento evento : eventos) {
            // Separa a String "2026-08-26 10:02:15" no espaço
            String[] partesDataHora = evento.getDataHora().split(" ");

            if (partesDataHora.length > 1) {
                String horaCompleta = partesDataHora[1]; // "10:02:15"
                String apenasHora = horaCompleta.substring(0, 2); // "10"
                try {
                    int hora = Integer.parseInt(apenasHora);
                    if (hora >= HORA_INICIO && hora <= HORA_FIM) {
                        alertas.add(new Alerta(
                                evento.getIp(),
                                "MEDIA",
                                "Atividade na madrugada às " + horaCompleta + " do usuário " + evento.getUsuario()
                        ));
                    }
                } catch (NumberFormatException e) {
                    // Ignora se não for possível converter
                }
            }
        }
        return alertas;
    }
}
