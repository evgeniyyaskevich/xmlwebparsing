package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

public enum Group {
    PAIN_RELIEVER("Pain Reliever"),
    ANTIBIOTIC("Antibiotic"),
    ANTIVIRAL("Antiviral"),
    ANTIFEBRILE("Antifebrile"),
    SEDATIVE("Sedative"),
    UNKNOWN("unknown");

    private String name;

    Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Group valueByString(String name) {
        for (Group group : Group.values()) {
            if (name.equals(group.getName())) {
                return group;
            }
        }
        return UNKNOWN;
    }
}
