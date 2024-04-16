module org.nuberjonas.sentrycube.core.usermanagement {
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires org.nuberjonas.sentrycore.core.sharedkernel;

    exports org.nuberjonas.sentrycube.core.usermanagement.application;
    exports org.nuberjonas.sentrycube.core.usermanagement.application.commands;
    exports org.nuberjonas.sentrycube.core.usermanagement.application.dtos;
}