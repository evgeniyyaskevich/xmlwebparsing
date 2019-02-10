package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.List;

public class StaxXmlMedicineParser implements XmlParser<Medicine> {

    @Override
    public List<Medicine> parse(InputStream xmlStream) throws ParserConfigurationException, SAXException, InvalidInputStreamException {
        return null;
    }
}

