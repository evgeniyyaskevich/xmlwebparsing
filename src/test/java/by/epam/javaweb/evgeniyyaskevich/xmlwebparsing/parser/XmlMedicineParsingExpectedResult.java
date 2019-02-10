package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.*;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class XmlMedicineParsingExpectedResult {

    private List<Medicine> medicines = new ArrayList<>();

    private static final class SingletonHolder {
        private static final XmlMedicineParsingExpectedResult INSTANCE = new XmlMedicineParsingExpectedResult();
    }

    private XmlMedicineParsingExpectedResult() {
        MedicineBuilder medicineBuilder = new MedicineBuilder();
        PackBuilder packBuilder = new PackBuilder();
        CertificateBuilder certificateBuilder = new CertificateBuilder();
        VersionBuilder versionBuilder = new VersionBuilder();
        AnalogBuilder analogBuilder = new AnalogBuilder();
        DosageBuilder dosageBuilder = new DosageBuilder();

        medicineBuilder.setName("Aspirin")
                .setProducer("Bayer Company")
                .setGroup(Group.PAIN_RELIEVER);

        analogBuilder.setName("Epicor")
                .setOrigin(Origin.FOREIGN);
        medicineBuilder.addAnalog(analogBuilder.build());

        analogBuilder = new AnalogBuilder();
        analogBuilder.setName("Kardiomagnyl");
        medicineBuilder.addAnalog(analogBuilder.build());

        certificateBuilder.setId("ID-28012019228")
                .setIssueDate(LocalDate.parse("2019-01-28"))
                .setExpirationDate(LocalDate.parse("2020-12-31"))
                .setRegisteringOrganization("Official Bayer Protection Company");
        packBuilder.setAmount(20)
                .setPrice(25.3)
                .setPackType(PackType.BOX);
        dosageBuilder.setDose("81 mg")
                .setPeriodicity("3-5 days");
        versionBuilder.setConsistency(Consistency.PILL)
                .setDosage(dosageBuilder.build())
                .setPack(packBuilder.build())
                .setCertificate(certificateBuilder.build());
        medicineBuilder.addVersion(versionBuilder.build());
        medicines.add(medicineBuilder.build());

        medicineBuilder = new MedicineBuilder();
        medicineBuilder.setName("Groprinosin")
                .setProducer("Gedeon Richter")
                .setGroup(Group.ANTIVIRAL);

        analogBuilder = new AnalogBuilder();
        analogBuilder.setName("Anapheron")
                .setOrigin(Origin.LOCAL);
        medicineBuilder.addAnalog(analogBuilder.build());

        analogBuilder = new AnalogBuilder();
        analogBuilder.setName("Arbidol")
                .setOrigin(Origin.FOREIGN);
        medicineBuilder.addAnalog(analogBuilder.build());

        versionBuilder = new VersionBuilder();
        certificateBuilder = new CertificateBuilder();
        certificateBuilder.setId("ID-28012019232")
                .setIssueDate(LocalDate.parse("2000-01-13"))
                .setExpirationDate(LocalDate.parse("2020-12-21"))
                .setRegisteringOrganization("Sun Pharmaceutical Industries");
        packBuilder = new PackBuilder();
        packBuilder.setAmount(50)
                .setPrice(13.0)
                .setPackType(PackType.BOX);
        dosageBuilder = new DosageBuilder();
        dosageBuilder.setDose("500 mg")
                .setPeriodicity("5-7 days");
        versionBuilder.setConsistency(Consistency.PILL)
                .setCertificate(certificateBuilder.build())
                .setPack(packBuilder.build())
                .setDosage(dosageBuilder.build());
        medicineBuilder.addVersion(versionBuilder.build());

        versionBuilder = new VersionBuilder();
        certificateBuilder = new CertificateBuilder();
        certificateBuilder.setId("ID-28012019233")
                .setIssueDate(LocalDate.parse("2014-05-13"))
                .setExpirationDate(LocalDate.parse("2020-12-21"))
                .setRegisteringOrganization("Sun Pharmaceutical Industries");
        packBuilder = new PackBuilder();
        packBuilder.setPackType(PackType.BOTTLE)
                .setAmount(250)
                .setPrice(10.0);
        dosageBuilder = new DosageBuilder();
        dosageBuilder.setDose("10 ml")
                .setPeriodicity("5-7 days");
        versionBuilder.setConsistency(Consistency.SYRUP)
                .setCertificate(certificateBuilder.build())
                .setPack(packBuilder.build())
                .setDosage(dosageBuilder.build());
        medicineBuilder.addVersion(versionBuilder.build());

        medicines.add(medicineBuilder.build());
    }

    public static XmlMedicineParsingExpectedResult getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }
}
