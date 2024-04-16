package org.nuberjonas.sentrycube.core.usermanagement.domain.valueobjects;

public enum Gender {
    MALE("M", "Male"),
    FEMALE("F", "Female"),
    NON_BINARY("D", "Non-Binary"),
    NOT_LISTED("NL", "Not listed"),
    UNSURE("U", "Unsure"),
    DID_NOT_WANT_TO_SPECIFY("DNWTS", "Did not want to specify");

    private final String abbreviation;
    private final String value;

    private Gender(String abbreviation, String value){
        this.abbreviation = abbreviation;
        this.value = value;
    }

    public static Gender forAbbreviation(String abbreviation){
        return switch (abbreviation.toUpperCase()) {
            case "M" -> MALE;
            case "F" -> FEMALE;
            case "D" -> NON_BINARY;
            case "NL" -> NOT_LISTED;
            case "U" -> UNSURE;
            default -> DID_NOT_WANT_TO_SPECIFY;
        };
    }

    public static Gender forValue(String abbreviation){
        return switch (abbreviation.toLowerCase()) {
            case "male" -> MALE;
            case "female" -> FEMALE;
            case "non-binary" -> NON_BINARY;
            case "not listed" -> NOT_LISTED;
            case "unsure" -> UNSURE;
            default -> DID_NOT_WANT_TO_SPECIFY;
        };
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getValue() {
        return value;
    }
}
