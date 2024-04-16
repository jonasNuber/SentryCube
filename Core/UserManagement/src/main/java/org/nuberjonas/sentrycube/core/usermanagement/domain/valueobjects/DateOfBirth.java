package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateOfBirth {

    public enum Format{
        YEAR_MONTH_DAY("yyyy-MM-dd"),
        DAY_MONTH_YEAR("dd-MM-yyyy"),
        MONTH_DAY_YEAR("MM-dd-yyyy");

        private final String value;

        private Format(String value) {
            this.value = value;
        }
    }
    private final LocalDate date;

    public DateOfBirth(LocalDate date) {
        this.date = date;
    }

    public DateOfBirth change(LocalDate newDate){
        return new DateOfBirth(newDate);
    }

    public String get(Format format){
        return date.format(DateTimeFormatter.ofPattern(format.value));
    }

    public String get(){
        return get(Format.YEAR_MONTH_DAY);
    }
}
