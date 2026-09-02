package com.socassistant.regras;

import com.socassistant.modelo.Alerta;
import com.socassistant.modelo.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegraForcaBruta implements RegraSeguranca {

    private static final int LIMITE = 3; 

    @Override
    public List<Alerta> analisar(List<Evento> eventos) {
        List<Alerta> alertas = new ArrayList<>();
        Map<String, Integer> falhasPorIp = new HashMap<>();

        for (Evento evento : eventos) {
            if (evento.getTipo().contains("FAILED")) {
                String ip = evento.getIp();
                falhasPorIp.put(ip, falhasPorIp.getOrDefault(ip, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : falhasPorIp.entrySet()) {
            if (entry.getValue() >= LIMITE) {
                alertas.add(new Alerta(
                        entry.getKey(), 
                        "ALTA", 
                        "Múltiplos acessos negados (" + entry.getValue() + " tentativas)"
                ));
            }
        }

        return alertas;
    }
}
