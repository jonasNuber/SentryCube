module org.nuberjonas.sentrycube.userinterface.rest {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter.web;
    requires spring.web;

    requires org.nuberjonas.sentrycube.intrastructure.persistence.jpa;
    requires org.nuberjonas.sentrycube.core.usermanagement;

    exports org.nuberjonas.sentrycube.userinterface.rest;
    exports org.nuberjonas.sentrycube.userinterface.rest.api;
}