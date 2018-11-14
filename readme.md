# ![RealWorld Example App](logo.png)

> ### Java EE codebase containing real world examples (CRUD, auth, advanced patterns, etc) that adheres to the [RealWorld](https://github.com/gothinkster/realworld) spec and API.


### [Demo](https://github.com/gothinkster/realworld)&nbsp;&nbsp;&nbsp;&nbsp;[RealWorld](https://github.com/gothinkster/realworld)

[![Build Status](https://travis-ci.com/pkuijpers/realworld-java-ee.svg?branch=master)](https://travis-ci.com/pkuijpers/realworld-java-ee)

[![SonarCloud Status](https://sonarcloud.io/api/project_badges/measure?project=pkuijpers_realworld-java-ee&metric=alert_status)](https://sonarcloud.io/dashboard?id=pkuijpers_realworld-java-ee)

This codebase was created to demonstrate a fully fledged fullstack application built with Java EE including CRUD operations, authentication, routing, pagination, and more.

We've gone to great lengths to adhere to the Java EE community styleguides & best practices.

For more information on how to this works with other frontends/backends, head over to the [RealWorld](https://github.com/gothinkster/realworld) repo.


# How it works

The application uses Java EE in a [Wildfly Swarm](http://wildfly-swarm.io) container.

Technologies used:

* Java
* Maven
* [Junit](https://junit.org/junit4/)
* JAX-RS
* [Codeship](https://app.codeship.com/projects/281870)
* [Arquillian](http://arquillian.org)

# Getting started

You need Java and Maven installed.

    mvn wildfly-swarm:run
    open http://localhost:8080/hello

