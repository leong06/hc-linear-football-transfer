package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.entities.TeamEntity;
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
            PlayerEntity player = transferDocument.getPlayer();
            TeamEntity sourceTeam = transferDocument.getSourceTeam();
            TeamEntity targetTeam = transferDocument.getTargetTeam();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            // PDF létrehozása
            document.add(new Paragraph("ÁTIGAZOLÁSI DOKUMENTUM \n", textFont));
            document.add(new Paragraph("Játékos: \n", textFont));
            document.add(new Paragraph("Név: " + player.getLastName() + " " + player.getFirstName(), textFont));
            document.add(new Paragraph("Forrás csapat: \n", textFont));

            if(sourceTeam != null){
                document.add(new Paragraph("Név: " + sourceTeam.getName(), textFont));
                document.add(new Paragraph("Város: " + sourceTeam.getCity(), textFont));
            } else {
                document.add(new Paragraph("Név: - (Nincs csapata) ", textFont));
            }

            document.add(new Paragraph("Cél csapat: \n", textFont));
            document.add(new Paragraph("Név: " + targetTeam.getName(), textFont));
            document.add(new Paragraph("Város: " +targetTeam.getCity(), textFont));

            document.add(new Paragraph("Átigazolási összeg: \n" + transferDocument.getAmount() + " Ft", textFont));
            document.add(new Paragraph("Létrehozás ideje: " + transferDocument.getCreatedAt(), textFont));



            document.close();
            return out.toByteArray();

        } catch (IOException | DocumentException e) {
            throw new IllegalStateException("Nem sikerült a pdf generálása!", e);
        }

    }


}
