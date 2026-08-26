SOC Assistant 🛡️

Ferramenta de análise de logs desenvolvida em Java
com foco em fundamentos de cibersegurança.

#Sobre o projeto

O SOC Assistant analisa arquivos de log e procura
padrões que possam indicar atividades suspeitas.

Inicialmente, o sistema identifica:

- múltiplas tentativas de login;
- IPs recorrentes;
- acessos negados;
- atividades em horários incomuns.

Quando um comportamento suspeito é encontrado,
o sistema gera um alerta explicando o motivo.

#Tecnologias

- Java
- Maven
- Git
- GitHub

#Arquitetura

Log → Processamento → Análise → Alerta → Relatório

#Evolução

- [x] Leitura de logs
- [ ] Detecção baseada em regras
- [ ] Sistema de alertas
- [ ] Banco de dados
- [ ] API com Spring Boot
- [ ] Integração com IA
- [ ] Dashboard

#Objetivo

Projeto desenvolvido para estudar Java,
programação orientada a objetos e fundamentos
de análise de eventos de segurança.