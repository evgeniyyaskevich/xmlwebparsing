package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Analog;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Origin;

public class AnalogBuilder {
    private Origin origin = Origin.UNSPECIFIED;
    private String name;

    public AnalogBuilder setOrigin(Origin origin) {
        this.origin = origin;
        return this;
    }

    public AnalogBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Analog build() {
        Analog newAnalog = new Analog();
        newAnalog.setName(name);
        newAnalog.setOrigin(origin);
        return newAnalog;
    }
}
