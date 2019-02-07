package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.validator;

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

    private static final class SingletonHolder {
        private static final XmlValidator INSTANCE = new XmlValidator();
    }

    private XmlValidator() {}

    public static XmlValidator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean validate(StreamSource xmlSource, String schemeName) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            ClassLoader classLoader = XmlValidator.class.getClassLoader();
            File shemeLocation =  new File(Objects.requireNonNull(classLoader.getResource(schemeName)).getFile());
            Schema schema = factory.newSchema(shemeLocation);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            return true;
        } catch (SAXException e) {
            //TODO: Log this event!!!
            e.printStackTrace();
            System.out.println("NOT VALID");
            return false;
        } catch (IOException e) {
            //TODO: Log this event!!!
            e.printStackTrace();
            return false;
        }
    }
}
