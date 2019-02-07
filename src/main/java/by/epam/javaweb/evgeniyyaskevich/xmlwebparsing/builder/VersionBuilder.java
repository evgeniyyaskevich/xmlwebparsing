package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder;


import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.*;

public class VersionBuilder {
    private Consistency consistency;
    private Certificate certificate;
    private Pack pack;
    private Dosage dosage;

    public VersionBuilder setConsistency(Consistency consistency) {
        this.consistency = consistency;
        return this;
    }

    public VersionBuilder setCertificate(Certificate certificate) {
        this.certificate = certificate;
        return this;
    }

    public VersionBuilder setPack(Pack pack) {
        this.pack = pack;
        return this;
    }

    public VersionBuilder setDosage(Dosage dosage) {
        this.dosage = dosage;
        return this;
    }

    public Version build() {
        Version newVersion = new Version();
        newVersion.setConsistency(this.consistency);
        newVersion.setPack(this.pack);
        newVersion.setDosage(this.dosage);
        newVersion.setCertificate(this.certificate);
        return newVersion;
    }
}
