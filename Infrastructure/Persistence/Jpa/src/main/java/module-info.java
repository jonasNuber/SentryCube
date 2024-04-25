module org.nuberjonas.sentrycube.intrastructure.persistence.jpa {
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires jakarta.validation;

    exports org.nuberjonas.sentrycube.intrastructure.persistence.jpa.tables;
    exports org.nuberjonas.sentrycube.intrastructure.persistence.jpa.data;
}