package com.socassistant.regras;

import com.socassistant.modelo.Alerta;
import com.socassistant.modelo.Evento;

import java.util.List;

public interface RegraSeguranca {
    List<Alerta> analisar(List<Evento> eventos);
}
