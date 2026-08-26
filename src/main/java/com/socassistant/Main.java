package com.socassistant;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;

 public class Main{
  public static void main(String[] args){
   Path caminho = Path.of("logs/eventos.log");
  try{
	for(String linha: Files.readAllLines(caminho)){
		System.out.println(linha);
}
}
catch(IOException erro){
	System.out.println("Erro ao ler o arquivo de log.");
 }
 }
}
