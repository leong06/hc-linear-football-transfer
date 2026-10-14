package com.hcLinear.backendTest.services;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TransferDocumentToPDFService {

    private BaseFont loadHungarianFont() throws IOException, DocumentException {
        return BaseFont.createFont("fonts/DejaVuSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    }

}
