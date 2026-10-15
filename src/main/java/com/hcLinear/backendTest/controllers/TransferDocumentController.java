package com.hcLinear.backendTest.controllers;

import com.hcLinear.backendTest.dto.TransferDocumentDTO;
import com.hcLinear.backendTest.entities.TransferDocumentEntity;
import com.hcLinear.backendTest.services.TransferDocumentService;
import com.hcLinear.backendTest.services.TransferDocumentToPDFService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Átigazolási Dokumentumok", description = "Átigazolási dokumentumok kezelésére szolgáló REST API")
@RestController
@RequestMapping("/transfer-documents")
public class TransferDocumentController {
    private final TransferDocumentService transferDocumentService;
    private final TransferDocumentToPDFService transferDocumentToPDFService;

    public TransferDocumentController(TransferDocumentService transferDocumentService, TransferDocumentToPDFService transferDocumentToPDFService) {
        this.transferDocumentService = transferDocumentService;
        this.transferDocumentToPDFService = transferDocumentToPDFService;
    }


    @Operation(summary = "Átigazolási dokumentumok listázása", description = "Átigazolási dokumentumok listázása")
    @ApiResponse(responseCode = "200", description = "Átigazolási dokumentumok listája")
    @GetMapping
    public List<TransferDocumentDTO> findAllDocuments() {
        return transferDocumentService.findAll();
    }


    @Operation(summary = "Átigazolási dokumentum lekérése azonosító alapján", description = "Átigazolási dokumentum lekérése az adott azonosítójú dokumentumhoz")
    @ApiResponse(responseCode = "200", description = "Átigazolási dokumentum listázása")
    @ApiResponse(responseCode = "404", description = "Átigazolási dokumentum nem található")
    @GetMapping("/{id}")
    public TransferDocumentDTO findDocumentById(@PathVariable Long id) {
        return transferDocumentService.findById(id);
    }


    @Operation(summary = "Átigazolási dokumentum PDF generálása azonosító alapján", description = "Átigazolási dokumentum PDF generálása az adott azonosítójú dokumentumhoz")
    @ApiResponse(responseCode = "200", description = "Átigazolási dokumentum PDF")
    @ApiResponse(responseCode = "404", description = "Átigazolási dokumentum nem található")
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> documentToPDF(@PathVariable Long id){
        TransferDocumentEntity document = transferDocumentService.findEntityById(id);
        byte[] pdf_document = transferDocumentToPDFService.generatePDF(document);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "attachment; filename=transfer_document.pdf")
                .body(pdf_document);
    }



}
