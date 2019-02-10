package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.*;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.*;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaxXmlMedicineParser extends DefaultHandler implements XmlParser<Medicine> {
    private static final Logger LOGGER = LogManager.getLogger(SaxXmlMedicineParser.class);

    private List<Medicine> medicines;
    private MedicineBuilder medicineBuilder;
    private PackBuilder packBuilder;
    private CertificateBuilder certificateBuilder;
    private VersionBuilder versionBuilder;
    private AnalogBuilder analogBuilder;
    private DosageBuilder dosageBuilder;
    private String currentElement;

    @Override
    public void startDocument() {
        LOGGER.debug("SAX parsing started.");
        medicines = new ArrayList<>();
    }

    @Override
    public void endDocument() {
        LOGGER.debug("SAX parsing ended.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
        switch (currentElement) {
            case "medicine":
                medicineBuilder = new MedicineBuilder();
                break;
            case "analog":
                analogBuilder = new AnalogBuilder();
                String origin = attributes.getValue("origin");
                origin = (origin == null) ? "unspecified" : origin;
                analogBuilder.setOrigin(Origin.valueByString(origin));
                break;
            case "version":
                versionBuilder = new VersionBuilder();
                String consistency = attributes.getValue("consistency");
                versionBuilder.setConsistency(Consistency.valueByString(consistency));
                break;
            case "certificate":
                certificateBuilder = new CertificateBuilder();
                String id = attributes.getValue("id");
                certificateBuilder.setId(id);
                break;
            case "package":
                packBuilder = new PackBuilder();
                String packType = attributes.getValue("type");
                packType = (packType == null) ? "box" : packType;
                packBuilder.setPackType(PackType.valueByString(packType));
                break;
            case "dosage":
                dosageBuilder = new DosageBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "version":
                medicineBuilder.addVersion(versionBuilder.build());
                break;
            case "certificate":
                versionBuilder.setCertificate(certificateBuilder.build());
                break;
            case "package":
                versionBuilder.setPack(packBuilder.build());
                break;
            case "analog":
                medicineBuilder.addAnalog(analogBuilder.build());
                break;
            case "dosage":
                versionBuilder.setDosage(dosageBuilder.build());
                break;
            case "medicine":
                medicines.add(medicineBuilder.build());
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String info = new String(ch, start, length);
        info = info.replace("\n", "").trim();
        if (!info.isEmpty()) {
            switch (currentElement) {
                case "name":
                    medicineBuilder.setName(info);
                    break;
                case "producer":
                    medicineBuilder.setProducer(info);
                    break;
                case "group":
                    medicineBuilder.setGroup(Group.valueByString(info));
                    break;
                case "analog":
                    analogBuilder.setName(info);
                    break;
                case "issueDate":
                    certificateBuilder.setIssueDate(LocalDate.parse(info));
                    break;
                case "expirationDate":
                    certificateBuilder.setExpirationDate(LocalDate.parse(info));
                    break;
                case "registeringOrganization":
                    certificateBuilder.setRegisteringOrganization(info);
                    break;
                case "amount":
                    packBuilder.setAmount(Integer.parseInt(info));
                    break;
                case "price":
                    packBuilder.setPrice(Double.parseDouble(info));
                    break;
                case "dose":
                    dosageBuilder.setDose(info);
                    break;
                case "periodicity":
                    dosageBuilder.setPeriodicity(info);
                    break;
            }
        }
    }

    public List<Medicine> parse(InputStream xmlStream) throws InvalidInputStreamException, SAXException, ParserConfigurationException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlStream, this);
            return medicines;
        } catch (IOException exception) {
            LOGGER.error("Stream isn`t valid for parsing.");
            throw new InvalidInputStreamException(exception);
        }
    }
}
