package com.socassistant.modelo;

public class Alerta {
    private String ip;
    private String severidade;
    private String motivo;

    public Alerta(String ip, String severidade, String motivo) {
        this.ip = ip;
        this.severidade = severidade;
        this.motivo = motivo;
    }
    public String getIp() {
        return ip;
    }
    public String getSeveridade() {
        return severidade;
    }
    public String getMotivo() {
        return motivo;
    }
    @Override
    public String toString() {
        return "[ALERTA " + severidade + "] IP: " + ip + " - Motivo: " + motivo;
    }
}
