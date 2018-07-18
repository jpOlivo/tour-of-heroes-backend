# Tour of heroes - backend

The app developed following the [Angular Tutorial: Tour of Heroes](https://angular.io/tutorial) uses both a [Mock](https://angular.io/tutorial/toh-pt2) and a [Simulated data server](https://angular.io/tutorial/toh-pt6) in order to fetch the data of heroes.

This implementation is a real backend built on Java using Spring Boot and serves the data of heroes (persisted on a Local MongoDB instance) through of an API Rest.
 

# API REST
The services offered enable to perform CRUD operations on a local MongoDB instance

![img](https://i.imgur.com/eU0eM7q.png)

There is a docs of this API available in [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)


# Running the app
From Eclipse IDE `tourheroes.Application -> Run As -> Java Application`

# Invoking services

1- Get __/api/heroes__

```
c:\>curl http://localhost:8080/api/heroes 

[{"id":"5b36453bbe56c4301cb3ef9b","name":"Valento"},{"id":"5b34fc35aab609d649a73f5a","name":"Marcos Rojo"},{"id":"5b3644cfbe56c4301cb3ef99","name":"Juampa"},{"id":"5b34fbf9aab609d649a73f59","name":"Gabi Mercado"},{"id":"5b364524be56c4301cb3ef9a","name":"El Brian"}]
```

2- Get __/api/heroes/{id}__

```
c:\>curl http://localhost:8080/api/heroes/5b364524be56c4301cb3ef9a

{"id":"5b364524be56c4301cb3ef9a","name":"El Brian"}
```