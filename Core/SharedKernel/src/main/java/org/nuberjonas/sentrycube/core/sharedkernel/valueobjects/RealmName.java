package org.nuberjonas.sentrycube.core.sharedkernel.valueobjects;

import java.util.Objects;

public record RealmName(String name) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealmName realmName = (RealmName) o;
        return Objects.equals(name, realmName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
