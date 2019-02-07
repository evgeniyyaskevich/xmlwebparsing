package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface XmlParser<T> {
    List<T> parse(InputStream xmlStream) throws ParserConfigurationException, SAXException, IOException;
}
