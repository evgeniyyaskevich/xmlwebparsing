package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

import java.util.List;

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
}
