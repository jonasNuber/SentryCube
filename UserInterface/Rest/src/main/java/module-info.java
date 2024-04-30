module org.nuberjonas.sentrycube.userinterface.rest {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter.web;
    requires spring.context;
    requires spring.web;
    requires spring.beans;
    requires spring.data.jpa;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires org.postgresql.jdbc;

    requires org.nuberjonas.sentrycube.infrastructure.persistence.jpa;
    requires org.nuberjonas.sentrycube.core.usermanagement;

    exports org.nuberjonas.sentrycube.userinterface.rest;
    exports org.nuberjonas.sentrycube.userinterface.rest.api;
}