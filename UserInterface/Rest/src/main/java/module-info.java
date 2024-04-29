module org.nuberjonas.sentrycube.userinterface.rest {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter.web;
    requires spring.web;
    requires spring.beans;
    requires spring.webmvc;

    //requires org.nuberjonas.sentrycube.intrastructure.persistence.jpa;
    requires org.nuberjonas.sentrycube.core.usermanagement;
    requires spring.data.jpa;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires org.postgresql.jdbc;
    requires com.fasterxml.jackson.annotation;

    exports org.nuberjonas.sentrycube.userinterface.rest;
    exports org.nuberjonas.sentrycube.userinterface.rest.api;
    exports org.nuberjonas.sentrycube.userinterface.rest.jpa.tables;
    exports org.nuberjonas.sentrycube.userinterface.rest.jpa.data;
    exports org.nuberjonas.sentrycube.userinterface.rest.jpa.repositories;
}