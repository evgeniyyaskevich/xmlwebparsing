package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

import java.util.List;
import java.util.Objects;

public class Medicine {
    private String name;
    private Group group;
    private String producer;
    private List<Analog> analogs;
    private List<Version> versions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Analog> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<Analog> analogs) {
        this.analogs = analogs;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medicine medicine = (Medicine) o;
        return name.equals(medicine.name) &&
                group == medicine.group &&
                producer.equals(medicine.producer) &&
                analogs.equals(medicine.analogs) &&
                versions.equals(medicine.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group, producer, analogs, versions);
    }
}
