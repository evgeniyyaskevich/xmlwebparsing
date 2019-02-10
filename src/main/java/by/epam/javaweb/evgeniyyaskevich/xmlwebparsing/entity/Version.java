package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

import java.util.Objects;

public class Version {
    private Consistency consistency;
    private Certificate certificate;
    private Pack pack;
    private Dosage dosage;

    public Consistency getConsistency() {
        return consistency;
    }

    public void setConsistency(Consistency consistency) {
        this.consistency = consistency;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Version version = (Version) o;
        return consistency == version.consistency &&
                certificate.equals(version.certificate) &&
                pack.equals(version.pack) &&
                dosage.equals(version.dosage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consistency, certificate, pack, dosage);
    }
}
