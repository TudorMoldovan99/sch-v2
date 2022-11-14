package com.sunnyside.Scheduler.controller;


import com.sunnyside.Scheduler.service.PDFGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PDFGeneratorController {

    private final PDFGeneratorService pdfGeneratorService;


    @GetMapping("/export")
    public void generatePDF(@RequestParam Integer invoiceId, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=invoice_" + currentDateTime + ".pdf";
        httpServletResponse.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(invoiceId, httpServletResponse);
    }


}
