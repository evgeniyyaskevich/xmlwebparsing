package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.factory;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.DomXmlMedicineParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.SaxXmlMedicineParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.StaxXmlMedicineParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.XmlParser;

public class XmlParserFactory {

    private static final class SingletonHolder {
        private static final XmlParserFactory INSTANCE = new XmlParserFactory();
    }

    private XmlParserFactory() {}

    public static XmlParserFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public XmlParser<Medicine> createMedicineParser(String parserName) {
        parserName = parserName.toLowerCase();
        switch (parserName) {
            case "sax":
                return new SaxXmlMedicineParser();
            case "stax":
                return new StaxXmlMedicineParser();
            case "dom":
                return new DomXmlMedicineParser();
            default:
                //TODO: throw exception
                throw new NullPointerException();
        }
    }

}
