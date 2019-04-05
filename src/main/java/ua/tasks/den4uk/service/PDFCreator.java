package ua.tasks.den4uk.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tasks.den4uk.model.Job;
import ua.tasks.den4uk.model.User;

import java.io.ByteArrayOutputStream;

public class PDFCreator {

    private static final Logger logger = LogManager.getLogger(PDFCreator.class);

    public static ByteArrayOutputStream getPDF(User user) {
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(90);

            addPCell("First Name", table);
            addPCell(user.getFirstName(), table);
            addPCell("Last Name", table);
            addPCell(user.getLastName(), table);
            addPCell("Birthday", table);
            addPCell(user.getBirthday(), table);
            addPCell("Email", table);
            addPCell(user.getEmail(), table);
            addPCell("Phone number", table);
            addPCell(user.getPhoneNumber(), table);
            addPCell("Jobs", table);

            addPCell(" ", table);

            for (Job job : user.getJobs()) {
                addPCell("Position", table);
                addPCell(job.getPosition(), table);
                addPCell("Company Name", table);
                addPCell(job.getCompanyName(), table);
                addPCell("Begin", table);
                addPCell(job.getBegin(), table);
                addPCell("End", table);
                addPCell(job.getEnd(), table);

                addPCell(" ", table);
                addPCell(" ", table);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return out;
    }

    private static void addPCell(String phrase, PdfPTable table) {
        PdfPCell pCell;

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        pCell = new PdfPCell(new Phrase(phrase, font));
        pCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        table.addCell(pCell);
    }

}
