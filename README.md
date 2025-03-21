<h1 align="center">Project Instrumetation with OpenTelemtry and Grafana</h1>

  
  ![Open Source ❤️](https://img.shields.io/badge/Open%20Source-black) ![Grafana](https://img.shields.io/badge/Grafana-orange) ![otpl](https://img.shields.io/badge/OpenTelemetry-blue) ![Project-learning](https://img.shields.io/badge/Learning%20Project-green)




This project aims to showcase my knowledge in application instrumentation and data sending to backups

Project:
![image](https://github.com/user-attachments/assets/753951b3-35f4-4b1f-b50b-82329374fddb)




--

I am realizing instrumentation the more applications sending your metrics, logs and traces to backends like Grafana-Tempo, Prometheus, Loki, theses datas will be displayed in the granfana-web.

Observation:

I built all the Docker Compose files, configs.yaml, and changed the application code with the purpose of learning about Instrumentation, Collector-OTPL, backends, etc.




<h2 align="center">Composition of Project</h2>


I created two hosts

```
Host A - Address: 192.168.15.141 - responsible for being the hardware of the applications and collectors
Host B - Address: 192.168.15.142 - responsible for being the hardware of the backends that will receive the data
```

Aplications generate traces, metrics and logs -> OpenTelemtry Collector -> Alloy Collector -> Tempo, Loki or other...

