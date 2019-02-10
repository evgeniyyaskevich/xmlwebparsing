package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.factory;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidParserNameException;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.DomXmlMedicineParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.SaxXmlMedicineParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.StaxXmlMedicineParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.XmlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XmlParserFactory {
    private static final Logger LOGGER = LogManager.getLogger(XmlParserFactory.class);

    private static final class SingletonHolder {
        private static final XmlParserFactory INSTANCE = new XmlParserFactory();
    }

    private XmlParserFactory() {}

    public static XmlParserFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public XmlParser<Medicine> createMedicineParser(String parserName) throws InvalidParserNameException {
        parserName = parserName.toLowerCase();
        switch (parserName) {
            case "sax":
                return new SaxXmlMedicineParser();
            case "stax":
                return new StaxXmlMedicineParser();
            case "dom":
                return new DomXmlMedicineParser();
            default:
                LOGGER.error("Exception: Factory doesn`t know parser with this name.");
                throw new InvalidParserNameException("There are no parser with this name.");
        }
    }

}
