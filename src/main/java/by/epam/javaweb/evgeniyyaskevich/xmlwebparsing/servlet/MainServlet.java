package by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.servlet;

import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.entity.Medicine;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.factory.XmlParserFactory;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.parser.XmlParser;
import by.epam.javaweb.evgeniyyaskevich.xmlwebparsing.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part filePart = request.getPart("inputFile");

        try (InputStream xmlStream = filePart.getInputStream()) {
            XmlValidator validator = XmlValidator.getInstance();
            if (!validator.validate(new StreamSource(xmlStream), "medicines.xsd")) {
                //TODO: redirect to error page
                return;
            }
            XmlParser<Medicine> medicineParser;
            String parserName = request.getParameter("parser");
            XmlParserFactory parserFactory = XmlParserFactory.getInstance();
            medicineParser = parserFactory.createMedicineParser(parserName);

            final Logger LOGGER = LogManager.getLogger(MainServlet.class);
            LOGGER.error("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            LOGGER.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            LOGGER.trace("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            LOGGER.debug("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            LOGGER.warn("AWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
            LOGGER.fatal("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            InputStream xmlStream1 = filePart.getInputStream();
            List<Medicine> medicines = medicineParser.parse(xmlStream1);

            request.setAttribute("medicines", medicines);
            //request.setAttribute("parser", parserName);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/medicineTable.jsp");
            dispatcher.forward(request, response);
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            //TODO: log this event!!!
        }
    }
}
