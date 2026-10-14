package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.TransferDocumentEntity;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class TransferDocumentToPDFService {

    private BaseFont loadHungarianFont() throws IOException, DocumentException {
        return BaseFont.createFont("fonts/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    }

    public byte[] generatePDF(TransferDocumentEntity transferDocument){
        try {
            BaseFont baseFont = loadHungarianFont();
            Font textFont = new Font(baseFont, 12);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("ÁTIGAZOLÁSI DOKUMENTUM", textFont));
            document.close();
            return out.toByteArray();

        } catch (IOException | DocumentException e) {
            throw new IllegalStateException("Nem sikerült a pdf generálása!", e);
        }

    }


}
