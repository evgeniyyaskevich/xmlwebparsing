package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.CertificateBuilder;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.MedicineBuilder;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.PackBuilder;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.VersionBuilder;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.*;
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

    private List<Medicine> medicines;
    private MedicineBuilder medicineBuilder;
    private PackBuilder packBuilder;
    private CertificateBuilder certificateBuilder;
    private VersionBuilder versionBuilder;
    private String currentElement;
    //TODO: QUESTION: I haven`t builder for this classes because of a little amount of parameters in constructor
    //TODO: but here builder is very convenient, set in object isn`t good?
    private Analog analog;
    private Dosage dosage;

    @Override
    public void startDocument() throws SAXException {
        //TODO: Log parse starting...
        medicines = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        switch (currentElement) {
            case "medicine":
                medicineBuilder = new MedicineBuilder();
                break;
            case "analog":
                analog = new Analog();
                String origin = attributes.getValue("origin");
                origin = (origin == null) ? "unspecified" : origin;
                analog.setOrigin(Origin.valueByString(origin));
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
                dosage = new Dosage();
                break;
            default:
                //TODO: Log this event!!!
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
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
                medicineBuilder.addAnalog(analog);
                break;
            case "dosage":
                versionBuilder.setDosage(dosage);
                break;
            case "medicine":
                medicines.add(medicineBuilder.build());
                break;
            default:
                //TODO: log this event!!!
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
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
                    analog.setName(info);
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
                    dosage.setDose(info);
                    break;
                case "periodicity":
                    dosage.setPeriodicity(info);
                    break;
                default:
                    //TODO: log this event!!!

            }
        }
    }

    public List<Medicine> parse(InputStream xmlStream) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlStream, this);
        return medicines;
    }
}
