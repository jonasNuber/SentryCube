module org.nuberjonas.sentrycube.infrastructure.persistence.jpa {
    requires spring.boot.starter.data.jpa;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires spring.data.jpa;
    requires spring.context;

    exports org.nuberjonas.sentrycube.infrastructure.persistence.jpa.tables;
    exports org.nuberjonas.sentrycube.infrastructure.persistence.jpa.data;
    exports org.nuberjonas.sentrycube.infrastructure.persistence.jpa.repositories;
}