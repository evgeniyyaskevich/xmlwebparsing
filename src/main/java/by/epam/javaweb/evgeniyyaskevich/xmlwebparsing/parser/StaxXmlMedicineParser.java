package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StaxXmlMedicineParser implements XmlParser<Medicine> {

    @Override
    public List<Medicine> parse(InputStream xmlStream) throws ParserConfigurationException, SAXException, IOException {
        return null;
    }
}

