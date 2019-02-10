package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.List;

public interface XmlParser<T> {
    List<T> parse(InputStream xmlStream) throws ParserConfigurationException, SAXException, InvalidInputStreamException;
}
