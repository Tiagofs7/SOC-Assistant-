package com.socassistant.regras;

import com.socassistant.modelo.Alerta;
import com.socassistant.modelo.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegraSucessoPosFalha implements RegraSeguranca {

    private static final int LIMITE_FALHAS = 3;

    @Override
    public List<Alerta> analisar(List<Evento> eventos) {
        List<Alerta> alertas = new ArrayList<>();
        Map<String, Integer> falhasPorIp = new HashMap<>();

        for (Evento evento : eventos) {
            String ip = evento.getIp();
            
            // Se falhar, soma 1 na conta desse IP
            if (evento.getTipo().contains("FAILED")) {
                falhasPorIp.put(ip, falhasPorIp.getOrDefault(ip, 0) + 1);
            } 
            // Se for sucesso, verifica se ele tinha falhado muitas vezes antes
            else if (evento.getTipo().contains("SUCCESS")) {
                int falhasAnteriores = falhasPorIp.getOrDefault(ip, 0);
                
                if (falhasAnteriores >= LIMITE_FALHAS) {
                    alertas.add(new Alerta(
                            ip,
                            "CRITICA",
                            "Login de SUCESSO após " + falhasAnteriores + " falhas seguidas. Possível conta comprometida!"
                    ));
                }
                // Zera as falhas para esse IP, pois ele já logou com sucesso
                falhasPorIp.put(ip, 0);
            }
        }
        return alertas;
    }
}
