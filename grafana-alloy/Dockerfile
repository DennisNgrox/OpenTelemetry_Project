# Usar a imagem base oficial do Grafana Alloy
FROM grafana/alloy:latest

# Definir o diretório de trabalho
WORKDIR /etc/alloy

# Copiar o arquivo de configuração para o contêiner
COPY config.alloy /etc/alloy/config.alloy

# Expor a porta que o Alloy vai escutar
EXPOSE 12345

# Comando para rodar o Alloy com o arquivo de configuração
CMD ["run", "--server.http.listen-addr=0.0.0.0:12345", "--storage.path=/var/lib/alloy/data", "/etc/alloy/config.alloy"]