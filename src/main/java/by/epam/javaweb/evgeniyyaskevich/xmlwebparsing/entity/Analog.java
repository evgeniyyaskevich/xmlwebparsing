package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Analog analog = (Analog) o;
        return origin == analog.origin &&
                name.equals(analog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, name);
    }
}
