# hearthStone
# build
O build é feito através do comando mvn clean install.
Serão expostos os serviços:

#baralho
- GET
* http://localhost:8080/v1/baralho
* http://localhost:8080/v1/baralho/{id}
* http://localhost:8080/v1/baralho/classe/{classe}
* http://localhost:8080/v1/baralho/delete/{id}

- POST
http://localhost:8080/v1/baralho

#carta
- GET
* http://localhost:8080/v1/carta
* http://localhost:8080/v1/carta/{id}
* http://localhost:8080/v1/carta/classe/{classe}
* http://localhost:8080/v1/carta/tipo/{tipo}
* http://localhost:8080/v1/carta/delete/{id}

- POST
http://localhost:8080/v1/carta
http://localhost:8080/v1/carta/addCartas

Na raiz do projeto segue o script de testes do postman