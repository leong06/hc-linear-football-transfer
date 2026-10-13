package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.dto.TransferDocumentMapper;
import com.hcLinear.backendTest.repositories.TransferDocumentRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferDocumentService {
    private static final String PREFIX = "ATIG";
    private static final String SUFFIX = "HU";

    private final TransferDocumentRepository transferDocumentRepository;
    private final TransferDocumentMapper transferDocumentMapper;

    public TransferDocumentService(TransferDocumentRepository transferDocumentRepository, TransferDocumentMapper transferDocumentMapper) {
        this.transferDocumentRepository = transferDocumentRepository;
        this.transferDocumentMapper = transferDocumentMapper;
    }

    


}
