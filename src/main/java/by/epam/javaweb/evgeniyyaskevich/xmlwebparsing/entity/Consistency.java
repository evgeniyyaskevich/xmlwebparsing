package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

public enum Consistency {
    PILL("pill"),
    SYRUP("syrup"),
    DROPS("drops"),
    OINTMENT("ointment"),
    CAPSULE("capsule"),
    POWDER("powder"),
    TABLET("tablet"),
    UNKNOWN("unknown");

    private String name;

    Consistency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Consistency valueByString(String name) {
        for (Consistency consistency : Consistency.values()) {
            if (name.equals(consistency.getName())) {
                return consistency;
            }
        }
        return UNKNOWN;
    }
}
