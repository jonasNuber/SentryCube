module org.nuberjonas.sentrycube.core.usermanagement {
    exports org.nuberjonas.sentrycube.core.usermanagement.application;
    exports org.nuberjonas.sentrycube.core.usermanagement.application.commands;
    exports org.nuberjonas.sentrycube.core.usermanagement.application.dtos;

    requires org.nuberjonas.sentrycore.core.sharedkernel;
    requires org.apache.commons.lang3;
}