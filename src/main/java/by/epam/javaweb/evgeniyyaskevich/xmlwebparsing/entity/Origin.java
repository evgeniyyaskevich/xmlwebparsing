package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

public enum Origin {
    FOREIGN("foreign"),
    LOCAL("local"),
    UNSPECIFIED("unspecified");

    private String name;

    Origin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Origin valueByString(String name) {
        for (Origin origin : Origin.values()) {
            if (name.equals(origin.getName())) {
                return origin;
            }
        }
        return UNSPECIFIED;
    }
}

