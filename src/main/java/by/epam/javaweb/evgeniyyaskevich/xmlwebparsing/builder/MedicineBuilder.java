package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Analog;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Group;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Version;

import java.util.ArrayList;
import java.util.List;

public class MedicineBuilder {
    private String name;
    private Group group;
    private String producer;
    private List<Analog> analogs = new ArrayList<>();
    private List<Version> versions = new ArrayList<>();

    public MedicineBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MedicineBuilder setGroup(Group group) {
        this.group = group;
        return this;
    }

    public MedicineBuilder addAnalog(Analog analog) {
        this.analogs.add(analog);
        return this;
    }

    public MedicineBuilder addVersion(Version version) {
        this.versions.add(version);
        return this;
    }

    public MedicineBuilder setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public Medicine build() {
        Medicine newMedicine = new Medicine();
        newMedicine.setAnalogs(this.analogs);
        newMedicine.setVersions(this.versions);
        newMedicine.setName(this.name);
        newMedicine.setGroup(this.group);
        newMedicine.setProducer(this.producer);
        return newMedicine;
    }
}
