# Cash Flow

Aplicação simples em arquitetura hexagonal com possibilidade de cadastro de transações de entrada e de saída e relatório de saldo consolidado por dia.

## Tecnologias

```
Spring-Boot 3
Spring-Data-JpaHibernate
Java 17
gradle
JUnit 5 (testes unitários)
TestContainers (testes de integração)
Arquitetura Hexagonal
Jacoco (Test Coverage/Report)
MapStruct (Mapper)
OpenApi (Documentação)
Liquibase (Database Migrations/versioning)
Probes Health/Liveness/Probeness
Metricas Prometheus
DTO

``` 

### Iniciando a aplicação e dependências

Obs: Necessário docker

Pensando em facilitar que tudo rode 100% indiferente do sistema operacional e ambiente a ideia é que tudo rode só com o comando de docker-compose up.

Acesse a pasta /infra e execute o docker-compose para iniciar o banco e buildar e executar a imagem docker da aplicação.

```
docker-compose up
```

O container do banco de dados postgres vai iniciar na porta 5432 e a aplicação iniciará na porta 8080. 
Se for necessário alterar alguma configuração para subir na sua máquina assim como porta ou algo do tipo as alterações precisam ser feitas no
arquivo src/main/resources/application.yaml e se necessário no /infra/docker-compose.yaml.

Obs: Pode ser que demore a primeira execução para baixar todas as dependências.

### Usando
A aplicação expõe um swagger onde é possível fazer uso das APIs, que poderá ser acessado pelo:
http://localhost:8080/swagger-ui/index.html

A aplicação também expõe as specificações no json open-api:
http://localhost:8080/v3/api-docs

Acredito que as versões mais atuais do Postman e ou do Insomnia são capazes de importar essas configurações no json de 
especificação open-api para usar nas respectivas aplicações.

#### Mais informações

É possível cadastrar transações de entrada e saída, recuperar uma transação e listar todas e no endpoint de relatório,
ter um consolidado do saldo do dia passando a data de ínicio e a data final como filtro.

A aplicação expões métricas prometheus no endpoint /prometheus e métricas do spring-boot, diversas no /metrics.

Também tem /health que valida se a app está health e validando conexão com banco, /health/liveness e
/health/readiness para ser usado como probe para o kubernetes geralmente.

### Testes

A aplicação contém testes unitários e de integração usando testcontainers, se houver interesse, para rodalos, precisará 
ter JDK17 disponível no ambiente e basta rodar o comando:
```
./gradlew clean build test
```
No ouput irá ter links de relatórios falando sobre percentual de cobertura criados pelo jacoco, esse relatório pode ser 
exposto para alguma ferramenta como sonarqube para mais validações e notificações em um pipeline por exemplo.

![coverage.jpg](readme%2Fcoverage.jpg)

## Próximos passos

A ideia era adicionar novas funcionalidades e transformar o projeto em uma arquitetura mais "robusta" como forma 
de demonstrar como pode-se aplicar tecnologias.

Adicionar um vault para rotacionamento de senhas de banco de dados.

Configurar a aplicação para usar o vault para gerenciar configurações assim como as chaves de acesso/senhas.

Extrair a parte de relatórios para um novo microserviço de relatórios com um banco réplica ou com os dados replicados via stream/kafka.

Adicionar um api-gateway como camada de entrada controlando as rotas para facilitar essa migração para 2 microserviços.

Ver a viabilidade de feature-flag para essa migração ser transparente aos usuários.

Pensando em segurança, adicionar para trabalhar junto do api-gateway um outro serviço como keycloak/gerenciador de credenciais para colocar uma camada OAuth/segurança/Autenticação nas requisições.

Adicionar serviços de cache, controlar load-balance, permitir que a aplicação escale.

Rodar tudo dentro de um minikube além do docker-compose.

Controle de comunicação usando Istio.

Adicionar validações SAST/Segurança no processo de test/build.

Adicionar funcionalidade de open-telemetry/tracing, x-b3 pattern. (Spring-Sleuth with Istio)

Adicionar funcionalidade de logs em formato JSON com dados de tracing. (Spring/Log4j/JSON Layout)

# Solução proposta

Inicialmente o código representa o MS Flow e MS Report(ainda não separados em 2 ms). A solução final proposta a fim de demonstrar uma proposta com recursos avançados poderia ficar como a seguir: 

![img.png](readme%2Fsolution.jpg)