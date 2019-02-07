package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

public enum PackType {
    BOX("box"),
    BOTTLE("bottle"),
    TUBE("tube"),
    UNKNOWN("unknown");

    private String name;

    PackType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PackType valueByString(String name) {
        for (PackType packType : PackType.values()) {
            if (name.equals(packType.getName())) {
                return packType;
            }
        }
        return UNKNOWN;
    }
}
