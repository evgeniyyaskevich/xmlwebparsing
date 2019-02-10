package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class SaxXmlMedicineParserTest {

    private static SaxXmlMedicineParser saxXmlMedicineParser = new SaxXmlMedicineParser();

    @Test
    public void parse() throws FileNotFoundException, InvalidInputStreamException,
            SAXException, ParserConfigurationException {
        List<Medicine> expected = XmlMedicineParsingExpectedResult.getInstance().getMedicines();

        File file = new File("src/test/resource/testMedicines.xml");
        List<Medicine> result = saxXmlMedicineParser.parse(new FileInputStream(file));

        Assert.assertEquals(result, expected);
    }
}