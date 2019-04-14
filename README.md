### Docker
Utilizando o Aplicativo

Dockerfile
```
FROM openjdk:8
ADD target/docker-transacoes.jar docker-fiap_trabalho_estatisticas.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "docker-fiap_trabalho_estatisticas.jar"]
```

BUILD
```
docker build -f Dockerfile -t docker-fiap_trabalho_estatisticas.jar .
```

Container 1
```
docker run --name Aplicacao1 -p 8080:9091 docker-transacoes.jar
```

Container 2
```
docker run --name Aplicacao2 -p 8081:9091 docker-transacoes.jar
```
  
### POST "8080:9091"
curl -X POST \
  http://localhost:8080/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 9ab7f331-55ea-47f4-adfe-84218fc00994' \
  -H 'cache-control: no-cache' \
  -d '{
"amount": 25000.15,
"timestamp": 1478221904000
}
'

### GET "8080:9091"
curl -X GET \
  http://localhost:8080/statistics \
  -H 'Postman-Token: a5b95e3c-e889-4668-8e75-1588da642ca2' \
  -H 'cache-control: no-cache'

### POST "8081:9091"
curl -X POST \
  http://localhost:8081/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: d198bb1a-93fc-4058-b34e-ca41ad9222a6' \
  -H 'cache-control: no-cache' \
  -d '{
"amount": 25000.15,
"timestamp": 1478221904000
}
'
### GET "8081:9091"
curl -X GET \
  http://localhost:8081/statistics \
  -H 'Postman-Token: 72904c13-fbe7-4ad5-a175-8a183507326f' \
  -H 'cache-control: no-cache'
