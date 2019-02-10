package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.servlet;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidInputStreamException;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.exceptions.InvalidParserNameException;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.factory.XmlParserFactory;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.XmlParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/parsing")
@MultipartConfig
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = -2250669219016950199L;
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private final String INVALID_FILE_ERROR_PAGE = "/WEB-INF/jsp/errors/invalidFileError.jsp";
    private final String MEDICINE_TABLE_PAGE = "/WEB-INF/jsp/medicineTable.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part filePart = request.getPart("inputFile");

        try (InputStream xmlStreamForValidation = filePart.getInputStream()) {
            XmlValidator validator = XmlValidator.getInstance();
            if (!validator.validate(new StreamSource(xmlStreamForValidation), "medicines.xsd")) {
                LOGGER.warn("File isn`t valid.");
                getServletContext().getRequestDispatcher(INVALID_FILE_ERROR_PAGE)
                        .forward(request, response);
                return;
            }

            XmlParser<Medicine> medicineParser;
            String parserName = request.getParameter("parser");
            XmlParserFactory parserFactory = XmlParserFactory.getInstance();
            medicineParser = parserFactory.createMedicineParser(parserName);

            List<Medicine> medicines;
            try (InputStream xmlStreamForParsing = filePart.getInputStream()) {
                medicines = medicineParser.parse(xmlStreamForParsing);
            }
            request.setAttribute("medicines", medicines);
            getServletContext().getRequestDispatcher(MEDICINE_TABLE_PAGE)
                    .forward(request, response);
        } catch (InvalidInputStreamException e) {
            LOGGER.warn("Invalid input stream " + e.getMessage());
            e.printStackTrace();
            getServletContext().getRequestDispatcher(INVALID_FILE_ERROR_PAGE)
                    .forward(request, response);
        } catch (InvalidParserNameException | ParserConfigurationException | SAXException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            response.sendError(500);
        }
    }
}
