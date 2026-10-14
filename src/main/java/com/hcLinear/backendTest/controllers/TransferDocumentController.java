package com.hcLinear.backendTest.controllers;

import com.hcLinear.backendTest.dto.TransferDocumentDTO;
import com.hcLinear.backendTest.entities.TransferDocumentEntity;
import com.hcLinear.backendTest.services.TransferDocumentService;
import com.hcLinear.backendTest.services.TransferDocumentToPDFService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transfer-documents")
public class TransferDocumentController {
    private final TransferDocumentService transferDocumentService;
    private final TransferDocumentToPDFService transferDocumentToPDFService;

    public TransferDocumentController(TransferDocumentService transferDocumentService, TransferDocumentToPDFService transferDocumentToPDFService) {
        this.transferDocumentService = transferDocumentService;
        this.transferDocumentToPDFService = transferDocumentToPDFService;
    }

    @GetMapping
    public List<TransferDocumentDTO> findAllDocuments() {
        return transferDocumentService.findAll();
    }

    @GetMapping("/{id}")
    public TransferDocumentDTO findDocumentById(@PathVariable Long id) {
        return transferDocumentService.findById(id);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> documentToPDF(@PathVariable Long id){
        TransferDocumentEntity document = transferDocumentService.findEntityById(id);
        byte[] pdf_document = transferDocumentToPDFService.generatePDF(document);
        return ResponseEntity.ok(pdf_document);
    }



}
