package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.builder.*;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.*;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaxXmlMedicineParser implements XmlParser<Medicine> {

    private static final Logger LOGGER = LogManager.getLogger(StaxXmlMedicineParser.class);
    private List<Medicine> medicines;
    private MedicineBuilder medicineBuilder;
    private PackBuilder packBuilder;
    private CertificateBuilder certificateBuilder;
    private VersionBuilder versionBuilder;
    private AnalogBuilder analogBuilder;
    private DosageBuilder dosageBuilder;
    private String elementValue;

    @Override
    public List<Medicine> parse(InputStream xmlStream) throws InvalidInputStreamException {
        medicines = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(xmlStream);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_DOCUMENT:
                        LOGGER.debug("StAX parsing started.");
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        processStartElement(startElement);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        elementValue = characters.getData();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        processEndElement(endElement);
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        LOGGER.debug("StAX parsing ended.");
                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new InvalidInputStreamException(e.getMessage(), e);
        }

        return medicines;
    }

    private void processStartElement(StartElement startElement) {
        String name = startElement.getName().getLocalPart();
        switch (name) {
            case "medicine":
                medicineBuilder = new MedicineBuilder();
                break;
            case "analog":
                analogBuilder = new AnalogBuilder();
                Attribute origin = startElement.getAttributeByName(new QName("origin"));
                if (origin != null) {
                    analogBuilder.setOrigin(Origin.valueByString(origin.getValue()));
                }
                break;
            case "version":
                versionBuilder = new VersionBuilder();
                Attribute consistency = startElement.getAttributeByName(new QName("consistency"));
                versionBuilder.setConsistency(Consistency.valueByString(consistency.getValue()));
                break;
            case "certificate":
                certificateBuilder = new CertificateBuilder();
                Attribute id = startElement.getAttributeByName(new QName("id"));
                certificateBuilder.setId(id.getValue());
                break;
            case "package":
                packBuilder = new PackBuilder();
                Attribute packType = startElement.getAttributeByName(new QName("type"));
                if (packType != null) {
                    packBuilder.setPackType(PackType.valueByString(packType.getValue()));
                }
                break;
            case "dosage":
                dosageBuilder = new DosageBuilder();
                break;
        }
    }

    private void processEndElement(EndElement endElement) {
        String name = endElement.getName().getLocalPart();
        switch (name) {
            case "medicine":
                medicines.add(medicineBuilder.build());
                break;
            case "name":
                medicineBuilder.setName(elementValue);
                break;
            case "producer":
                medicineBuilder.setProducer(elementValue);
                break;
            case "group":
                medicineBuilder.setGroup(Group.valueByString(elementValue));
                break;
            case "analog":
                analogBuilder.setName(elementValue);
                medicineBuilder.addAnalog(analogBuilder.build());
                break;
            case "issueDate":
                certificateBuilder.setIssueDate(LocalDate.parse(elementValue));
                break;
            case "expirationDate":
                certificateBuilder.setExpirationDate(LocalDate.parse(elementValue));
                break;
            case "registeringOrganization":
                certificateBuilder.setRegisteringOrganization(elementValue);
                break;
            case "certificate":
                versionBuilder.setCertificate(certificateBuilder.build());
                break;
            case "amount":
                packBuilder.setAmount(Integer.parseInt(elementValue));
                break;
            case "price":
                packBuilder.setPrice(Double.parseDouble(elementValue));
                break;
            case "package":
                versionBuilder.setPack(packBuilder.build());
                break;
            case "dose":
                dosageBuilder.setDose(elementValue);
                break;
            case "periodicity":
                dosageBuilder.setPeriodicity(elementValue);
                break;
            case "dosage":
                versionBuilder.setDosage(dosageBuilder.build());
                break;
            case "version":
                medicineBuilder.addVersion(versionBuilder.build());
                break;
        }
    }
}

