package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.validator;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XmlValidator {
    private static final Logger LOGGER = LogManager.getLogger(XmlValidator.class);

    private static final class SingletonHolder {
        private static final XmlValidator INSTANCE = new XmlValidator();
    }

    private XmlValidator() {}

    public static XmlValidator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean validate(StreamSource xmlSource, String schemeName) throws InvalidInputStreamException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            ClassLoader classLoader = XmlValidator.class.getClassLoader();
            File schemaLocation =  new File(Objects.requireNonNull(classLoader.getResource(schemeName)).getFile());
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            return true;
        } catch (SAXException e) {
            return false;
        } catch (IOException exception) {
            LOGGER.error("Stream isn`t valid for scheme validation.");
            throw new InvalidInputStreamException(exception);
        }
    }
}
