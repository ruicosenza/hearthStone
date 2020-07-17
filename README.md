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


* OBJETIVO

Criar um programa para o cenário descrito abaixo. Atentar às seguintes orientações:

• Utilize Java 8 ou superior.

• Criar o projeto no GitHub e nos enviar a URL.

• Definir no readme.md como compilar o projeto.

• Criar no Postman a collection de teste usada.

• Utilize os padrões de mercado para criação da API Rest

• Para a implementação do padrão pode usar SpringBoot, Quarkus, Thorntail ou outra implementação que gere um uberjar.


* CENÁRIO

• O jogo HearthStone é um jogo de cartas onde cada oponente precisa eliminar o outro.

• Ele é jogado em turnos e cada jogador recebe suas cartas, que são divididas em classes (Mago, Paladino, Caçador, Druida e “qualquer classe”).

• As cartas podem ser de criaturas ou de magias e possuem seu custo em mana, podendo ser de zero a dez. Há uma descrição da ação da carta, um nome e seu poder de dano e defesa.

• Cada jogador pode montar um baralho com até 30 cartas da sua classe ou cartas que permitem qualquer classe. É permitido no máximo duas cartas iguais no mesmo baralho.

Carta

Id - Inteiro - Identificador da carta

Nome - String - Nome da carta

Descrição - String - Descrição da carta

Ataque - Inteiro - Pode ser de 0 até 10

Defesa - Inteiro - Pode ser de 0 até 10

Tipo - Enumerado - Magia ou Criatura

Classe - Enumerado - Mago, Paladino, Caçador, Druida e Qualquer


FEATURES QUE DEVERÃO SER PROGRAMADAS

1 - Criar uma API Rest com o path “/carta” usando JAXRS para cadastrar, consultar e excluir uma carta. Permitir consultar uma carta por nome, id, classe ou tipo. Utilize os padrões de mercado para criação da API Rest.

2 - Criar uma API Rest com o path “/baralho” usando JAXRS para cadastrar um novo baralho, consultar e excluir, permitir consultar um baralho por nome, id e classe. Permitir detalhar as cartas de um baralho

3 - O armazenamento pode ser feito usando um banco H2 ou qualquer outro em memória.
