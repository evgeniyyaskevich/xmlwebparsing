package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Certificate;

import java.time.LocalDate;

public class CertificateBuilder {
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String registeringOrganization;
    private String id;

    public CertificateBuilder setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public CertificateBuilder setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public CertificateBuilder setRegisteringOrganization(String registeringOrganization) {
        this.registeringOrganization = registeringOrganization;
        return this;
    }

    public CertificateBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public Certificate build() {
        Certificate newCertificate = new Certificate();
        newCertificate.setId(id);
        newCertificate.setExpirationDate(expirationDate);
        newCertificate.setIssueDate(issueDate);
        newCertificate.setRegisteringOrganization(registeringOrganization);
        return newCertificate;
    }
}
