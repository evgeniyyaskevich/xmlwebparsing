package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity;

import java.util.Objects;

public class Dosage {
    private String dose;
    private String periodicity;

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dosage dosage = (Dosage) o;
        return dose.equals(dosage.dose) &&
                periodicity.equals(dosage.periodicity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dose, periodicity);
    }
}
