package com.sunnyside.Scheduler.service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sunnyside.Scheduler.dto.Customer;
import com.sunnyside.Scheduler.dto.Invoice;
import com.sunnyside.Scheduler.dto.InvoiceLine;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

@Service
@Data
public class PDFGeneratorService {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private CustomerService customerService;


    public void export(Integer invoiceId, HttpServletResponse httpServletResponse) throws IOException {

        Invoice invoice = invoiceService.getInvoice(invoiceId);
        Customer customer = customerService.findCustomerById(invoice.getCustomerId());

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.RED);
        font.setSize(18);

        Paragraph title = new Paragraph("SunnySide HVAC - INVOICE", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph invoiceDate = new Paragraph("Invoice date: " + invoice.getDate().toString());
        invoiceDate.setAlignment(Element.ALIGN_CENTER);
        invoiceDate.setSpacingBefore(15);
        document.add(invoiceDate);

        PdfPTable entitiesTable = new PdfPTable(2);
        entitiesTable.setWidthPercentage(100);
        entitiesTable.setSpacingBefore(30);
        entitiesTable.setSpacingAfter(30);
        entitiesTable.setWidths(new float[] {5.5f, 5.5f});
        writeEntitiesHeader(entitiesTable);
        writeEntitiesContent(entitiesTable, customer);
        document.add(entitiesTable);

       PdfPTable pdfPTable = new PdfPTable(3);
       pdfPTable.setWidthPercentage(100);
       pdfPTable.setSpacingBefore(30);
       pdfPTable.setWidths(new float[] {5.5f, 1.5f, 1.5f});
       writeTableHeader(pdfPTable);
       writeTableData(pdfPTable, invoice.getInvoiceLines());
       writeTableFooter(pdfPTable, invoice.getInvoiceLines());

       document.add(pdfPTable);

       document.close();
    }

    private void writeTableHeader(PdfPTable pdfPTable) {
        PdfPCell pdfCell = new PdfPCell();
        pdfCell.setBackgroundColor(Color.BLACK);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(14);
        font.setColor(Color.WHITE);
        pdfCell.setPhrase(new Phrase("Description", font));
        pdfPTable.addCell(pdfCell);
        pdfCell.setPhrase(new Phrase("Units", font));
        pdfPTable.addCell(pdfCell);
        pdfCell.setPhrase(new Phrase("Amount", font));
        pdfPTable.addCell(pdfCell);
    }

    private void writeEntitiesHeader(PdfPTable pdfPTable) {
        PdfPCell pdfCell = new PdfPCell();
        pdfCell.setBackgroundColor(Color.BLUE);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(16);
        font.setColor(Color.WHITE);
        pdfCell.setPhrase(new Phrase("EMIS DE", font));
        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfCell.setExtraParagraphSpace(5f);
        pdfPTable.addCell(pdfCell);
        pdfCell.setPhrase(new Phrase("PENTRU", font));
        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfCell.setExtraParagraphSpace(5f);
        pdfPTable.addCell(pdfCell);
    }

    private void writeEntitiesContent(PdfPTable pdfPTable, Customer customer) {

        Phrase phrase = new Phrase("SunnySide HVAC", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
        Phrase phrase1 = new Phrase("Company registration number: 43367342");
        Phrase phrase2 = new Phrase("Address: Pacii 12, Turda, CJ,  Romania 401073");

        PdfPCell pdfCell = new PdfPCell();
        pdfCell.addElement(phrase);
        pdfCell.addElement(phrase1);
        pdfCell.addElement(phrase2);

        Phrase customerPhrase = new Phrase(customer.getName(), FontFactory.getFont(FontFactory.HELVETICA_BOLD));
        Phrase customerPhrase2 = new Phrase("Address: " + customer.getAddress());
        Phrase customerPhrase3 = new Phrase("Email: " + customer.getEmailAddress());

        PdfPCell pdfCell2 = new PdfPCell();
        pdfCell2.addElement(customerPhrase);
        pdfCell2.addElement(customerPhrase2);
        pdfCell2.addElement(customerPhrase3);

        pdfPTable.addCell(pdfCell);
        pdfPTable.addCell(pdfCell2);
    }

    private void writeTableFooter(PdfPTable pdfPTable, List<InvoiceLine> invoiceLines) {

        BigDecimal totalSum = invoiceLines.stream()
            .map(InvoiceLine::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        pdfPTable.addCell("TOTAL: ");
        pdfPTable.addCell("");
        pdfPTable.addCell(String.valueOf(totalSum));
    }

    private void writeTableData(PdfPTable pdfPTable, List<InvoiceLine> invoiceLines) {
        invoiceLines.forEach(invoiceLine -> {
            pdfPTable.addCell(invoiceLine.getDescription());
            pdfPTable.addCell(String.valueOf(invoiceLine.getUnits()));
            pdfPTable.addCell(String.valueOf(invoiceLine.getValue()));
        });
    }
}
