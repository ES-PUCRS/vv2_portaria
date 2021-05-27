# Verificação e Validação de Software II
[![Build Status](https://travis-ci.com/ES-PUCRS/vv2_portaria.svg?branch=master)](https://travis-ci.org/ES-PUCRS/vv2_portaria)
[![Coverage Status](https://coveralls.io/repos/github/ES-PUCRS/vv2_portaria/badge.svg)](https://coveralls.io/github/ES-PUCRS/vv2_portaria)

## Requisitos
Deseja-se um sistema para controlar entregas em um condomínio.
O sistema deverá ser implementado preferencialmente em Web Puro (apenas
HTML+CSS+Javascript). Se desejarem usar algum framework, certificar-se de que seja
adequado ao desenvolvimento deste trabalho e de que todos os membros do grupo possuam
domínio técnico.
O sistema deverá iniciar com alguns dados já preenchidos (em bom número e de boa
qualidade), de forma a facilitar os testes.
Detalhamento

1. [X] O sistema deverá permitir escolher o operador/usuário atual.
2. [X] O sistema deverá permitir incluir um novo operador (não é necessário
implementar edição).
3. [X] O sistema deverá permitir excluir um operador desde que não existam registros
associados a ele.
4. [X] O sistema deverá permitir registrar uma nova entrega, com data e hora, descrição
e apartamento de destino, bem como o operador que recebeu a entrega. Sugerese gerar um ID numérico sequencial a cada nova entrega. (Sempre utilizar
referências aos objetos).
5. [X] O sistema deverá manter uma lista de moradores (nome, RG e nro do
apartamento).
6. [X] O sistema deverá permitir incluir um novo morador.
7. [X] O sistema deverá permitir marcar um morador como “inativo” (não é necessário
implementar edição nem exclusão). Um morador inativo não poderá ser mais
associado a novas entregas.
8. [X] O sistema deverá permitir ao operador registrar a retirada de uma entrega por um
morador. Registrar data e hora, o morador que retirou, relacionando com qual
entrega já registrada anteriormente.
9. [X] Deverá ser possível listar todos os moradores do prédio.
10. [X] Deverá ser possível procurar entregas pela descrição. Listar todas as encontradas.
11. [X] Deverá ser possível listar todas as entregas que ainda não foram retiradas,
ordenadas pela data.
12. [X] Deverá haver um painel (dashboard) com as seguintes informações:


    a. Nro total de entregas nos últimos 30 dias.

    b. Quantidade de entregas ainda não retiradas (total).

    c. Tempo médio entre registro e retirada das entregas.


13. [X] Deverá ser possível gerar um relatório como o exemplo abaixo, entre uma data
inicial e uma data final escolhidas pelo operador (note que há entregas ainda não
retiradas):

| Entrega |    Data/hora   |         Descrição           | Apto | Operador |    Retirada    |     Morador     |
|:-------:| -------------- | --------------------------- |:----:|:--------:|:--------------:| --------------- |
|    22   | 05/10/20 10:23 | Caixa grande azul Submarino |  201 |    JM    | 05/10/20 12:10 | Carlos Silveira |
|    23   | 05/10/20 14:18 | Caixa nro 109244            |  302 |    PO    |                |                 |
|    24   | 05/10/20 17:50 | Envelope pequeno            |  602 |    TF    | 06/10/20 09:00 | Marcia Duarte   |
|    25   | 06/10/20 08:20 | Carta registrada nro 98/233 |  502 |    JM    | 06/10/20 08:50 | Paula Borges    |
|    26   | 06/10/20 13:40 | Caixa pequena Americanas    |  203 |    PL    |                |                 |
