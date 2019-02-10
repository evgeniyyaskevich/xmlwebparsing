package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Certificate {
    private String id;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String registeringOrganization;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRegisteringOrganization() {
        return registeringOrganization;
    }

    public void setRegisteringOrganization(String registeringOrganization) {
        this.registeringOrganization = registeringOrganization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Certificate that = (Certificate) o;
        return id.equals(that.id) &&
                issueDate.equals(that.issueDate) &&
                expirationDate.equals(that.expirationDate) &&
                registeringOrganization.equals(that.registeringOrganization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueDate, expirationDate, registeringOrganization);
    }
}
