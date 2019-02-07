package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

public class Analog {
    private Origin origin = Origin.UNSPECIFIED;
    private String name;

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
