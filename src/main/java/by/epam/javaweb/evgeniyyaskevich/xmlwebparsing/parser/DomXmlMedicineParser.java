package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.*;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.*;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomXmlMedicineParser implements XmlParser<Medicine> {
    private static final Logger LOGGER = LogManager.getLogger(DomXmlMedicineParser.class);

    public List<Medicine> parse(InputStream xmlStream) throws ParserConfigurationException, SAXException, InvalidInputStreamException {
        List<Medicine> medicines = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlStream);
            document.getDocumentElement().normalize();

            NodeList medicineList = document.getElementsByTagName("medicine");
            for (int i = 0; i < medicineList.getLength(); ++i) {
                Node medicineNode = medicineList.item(i);
                Element medicineElement = (Element) medicineNode;

                Medicine medicine = buildMedicine(medicineElement);
                medicines.add(medicine);
            }
            return medicines;
        } catch (IOException exception) {
            LOGGER.error("Stream isn`t valid for parsing.");
            throw new InvalidInputStreamException(exception);
        }
    }

    private Medicine buildMedicine(Element medicineElement) {
        MedicineBuilder medicineBuilder = new MedicineBuilder();

        String name = medicineElement.getElementsByTagName("name").item(0).getTextContent();
        String producer = medicineElement.getElementsByTagName("producer").item(0).getTextContent();
        String group = medicineElement.getElementsByTagName("group").item(0).getTextContent();
        medicineBuilder.setName(name)
                .setProducer(producer)
                .setGroup(Group.valueByString(group));


        NodeList analogNodeList = medicineElement.getElementsByTagName("analog");
        for (int j = 0; j < analogNodeList.getLength(); ++j) {
            Node analogNode = analogNodeList.item(j);
            Element analogElement = (Element) analogNode;
            Analog analog = buildAnalog(analogElement);
            medicineBuilder.addAnalog(analog);
        }

        NodeList versionNodeList = medicineElement.getElementsByTagName("version");
        for (int k = 0; k < versionNodeList.getLength(); ++k) {
            Node versionNode = versionNodeList.item(k);
            Element versionElement = (Element) versionNode;
            Version version = buildVersion(versionElement);
            medicineBuilder.addVersion(version);
        }

        return medicineBuilder.build();
    }

    private Analog buildAnalog(Element analogElement) {
        AnalogBuilder analogBuilder = new AnalogBuilder();
        String origin = analogElement.getAttribute("origin");
        String analogName = analogElement.getTextContent();
        analogBuilder.setName(analogName)
                .setOrigin(Origin.valueByString(origin));
        return analogBuilder.build();
    }

    private Dosage buildDosage(Element dosageElement) {
        DosageBuilder dosageBuilder = new DosageBuilder();
        String dose = dosageElement.getElementsByTagName("dose").item(0).getTextContent();
        String periodicity = dosageElement.getElementsByTagName("periodicity").item(0).getTextContent();
        dosageBuilder.setDose(dose)
                .setPeriodicity(periodicity);
        return dosageBuilder.build();
    }

    private Version buildVersion(Element versionElement) {
        VersionBuilder versionBuilder = new VersionBuilder();

        String consistency = versionElement.getAttribute("consistency");
        versionBuilder.setConsistency(Consistency.valueByString(consistency));

        Element certificateElement = (Element) versionElement.getElementsByTagName("certificate").item(0);
        Certificate certificate = buildCertificate(certificateElement);
        versionBuilder.setCertificate(certificate);

        Element packElement = (Element) versionElement.getElementsByTagName("package").item(0);
        Pack pack = buildPack(packElement);
        versionBuilder.setPack(pack);

        Element dosageElement = (Element) versionElement.getElementsByTagName("dosage").item(0);
        Dosage dosage = buildDosage(dosageElement);
        versionBuilder.setDosage(dosage);
        return versionBuilder.build();
    }

    private Certificate buildCertificate(Element certificateElement) {
        CertificateBuilder certificateBuilder = new CertificateBuilder();

        String id = certificateElement.getAttribute("id");
        String issueDate = certificateElement.getElementsByTagName("issueDate").item(0).getTextContent();
        String expirationDate = certificateElement.getElementsByTagName("expirationDate").item(0).getTextContent();
        String registeringOrganization = certificateElement.getElementsByTagName("registeringOrganization")
                .item(0).getTextContent();
        certificateBuilder.setId(id)
                .setIssueDate(LocalDate.parse(issueDate))
                .setExpirationDate(LocalDate.parse(expirationDate))
                .setRegisteringOrganization(registeringOrganization);
        return certificateBuilder.build();
    }

    private Pack buildPack(Element packElement) {
        PackBuilder packBuilder = new PackBuilder();

        String packType = packElement.getAttribute("type");
        String amount = packElement.getElementsByTagName("amount").item(0).getTextContent();
        String price = packElement.getElementsByTagName("price").item(0).getTextContent();
        packBuilder.setAmount(Integer.parseInt(amount))
                .setPrice(Double.parseDouble(price))
                .setPackType(PackType.valueByString(packType));
        return packBuilder.build();
    }
}
