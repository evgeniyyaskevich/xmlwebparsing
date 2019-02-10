package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class StaxXmlMedicineParserTest {

    private static StaxXmlMedicineParser staxXmlMedicineParser = new StaxXmlMedicineParser();

    @Test
    public void parse() throws FileNotFoundException, InvalidInputStreamException {
        List<Medicine> expected = XmlMedicineParsingExpectedResult.getInstance().getMedicines();

        File file = new File("src/test/resource/testMedicines.xml");
        List<Medicine> result = staxXmlMedicineParser.parse(new FileInputStream(file));

        Assert.assertEquals(result, expected);
    }
}