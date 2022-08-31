# INTRODUCCIÓN MVN-GIT Y HEROKU

## Autor

Jhon Sebastian Piñeros Barrera

## Fecha

30/08/2022

## Heroku

https://sparkwebapparem.herokuapp.com/

## Descripcion

Este proyecto consiste en realizar el diseño y construccion de un programa que demuestre una simple arquitectura de Microservicios desplegada en Heroku.

### LOC/h

Se realizaron 505 lineas de codigo en aproximadamente 9 horas de trabajo, es decir, 56.1 lineas de codigo por hora.

### Prerrequisitos

- JAVA
- Maven
- GIT

### Estructura del proyecto

```
C:.
└───main
   ├───java
   │   └───co
   │       └───edu
   │           └───escuelaing
   │               └───arem
   │                   │   SparkWebApp.java
   │                   │
   │                   └───api
   │                           AlphaGetter.java
   │                           ApiGetter.java
   │                           Cache.java
   │                           Daily.java
   │                           IntraDay.java
   │                           Monthly.java
   │                           Weekly.java
   │
   └───resources
       └───public
           │   api.html
           │   getPost.html
           │   index.html
           │
           ├───css
           │       api.css
           │
           └───js
                   api.js
```

### JavaDoc

El javadoc se puede vizualizar con el comando 

```
mvn javadoc:javadoc
```

y posteriormente vamos a la carpeta de nuestro proyecto y buscamos en target/site/apidocs/index.html