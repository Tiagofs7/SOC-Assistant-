package com.socassistant.regras;

import com.socassistant.modelo.Alerta;
import com.socassistant.modelo.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegraViagemImpossivel implements RegraSeguranca {

    @Override
    public List<Alerta> analisar(List<Evento> eventos) {
        List<Alerta> alertas = new ArrayList<>();
        // Mapeia o Nome do Usuário para o último IP de sucesso que ele usou
        Map<String, String> ultimoIpDoUsuario = new HashMap<>();

        for (Evento evento : eventos) {
            if (evento.getTipo().contains("SUCCESS")) {
                String usuario = evento.getUsuario();
                String ipAtual = evento.getIp();
                
                // Pega o IP antigo que estava salvo para esse usuário
                String ipAntigo = ultimoIpDoUsuario.get(usuario);
                
                // Se ele já tinha um IP antigo salvo, e o IP de agora é diferente...
                if (ipAntigo != null && !ipAntigo.equals(ipAtual)) {
                    alertas.add(new Alerta(
                            ipAtual,
                            "ALTA",
                            "Usuário '" + usuario + "' acessou via IPs diferentes! Antes: " + ipAntigo + ", Agora: " + ipAtual
                    ));
                }
                
                // Atualiza o mapa com o IP mais recente do usuário
                ultimoIpDoUsuario.put(usuario, ipAtual);
            }
        }
        return alertas;
    }
}
