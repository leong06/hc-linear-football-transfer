package com.hcLinear.backendTest.controllers;

import com.hcLinear.backendTest.dto.TransferDocumentDTO;
import com.hcLinear.backendTest.services.TransferDocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transfer-documents")
public class TransferDocumentController {
    private final TransferDocumentService transferDocumentService;

    public TransferDocumentController(TransferDocumentService transferDocumentService) {
        this.transferDocumentService = transferDocumentService;
    }

    @GetMapping
    public List<TransferDocumentDTO> findAllDocuments() {
        return transferDocumentService.findAll();
    }

    @GetMapping("/{id}")
    public TransferDocumentDTO findDocumentById(@PathVariable Long id) {
        return transferDocumentService.findById(id);
    }


}
