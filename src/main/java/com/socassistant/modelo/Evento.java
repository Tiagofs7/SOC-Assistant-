package com.socassistant.modelo;

public class Evento{
	private String dataHora;
	private String tipo;
	private String usuario;
	private String ip;

	public Evento(String dataHora, String tipo, String usuario, String ip){
		this.dataHora = dataHora;
		this.tipo = tipo;
		this.usuario = usuario;
		this.ip = ip;
}

public String getDataHora(){
	return dataHora;
}

public String getTipo(){
	return tipo;
}

public String getUsuario(){
	return usuario;
}

 public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "dataHora='" + dataHora + '\'' +
                ", tipo='" + tipo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
