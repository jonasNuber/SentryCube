module org.nuberjonas.sentrycube.intrastructure.persistence.jpa {
    requires spring.boot.starter.data.jpa;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires spring.data.jpa;

    exports org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables;
    exports org.nuberjonas.sentrycube.intrastructure.persistence.jpa.data;
    exports org.nuberjonas.sentrycube.intrastructure.persistence.jpa.repositories;
}