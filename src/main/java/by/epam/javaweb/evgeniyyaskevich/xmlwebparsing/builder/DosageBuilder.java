package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Dosage;

public class DosageBuilder {
    private String dose;
    private String periodicity;

    public DosageBuilder setDose(String dose) {
        this.dose = dose;
        return this;
    }

    public DosageBuilder setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
        return this;
    }

    public Dosage build() {
        Dosage newDosage = new Dosage();
        newDosage.setPeriodicity(periodicity);
        newDosage.setDose(dose);
        return newDosage;
    }
}
