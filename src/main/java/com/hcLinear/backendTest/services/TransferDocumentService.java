package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.dto.TransferDocumentDTO;
import com.hcLinear.backendTest.dto.TransferDocumentMapper;
import com.hcLinear.backendTest.entities.TransferDocumentEntity;
import com.hcLinear.backendTest.entities.TransferRequestEntity;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.repositories.TransferDocumentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public TransferDocumentEntity createDocument(TransferRequestEntity transferRequest){
        long sequence = transferDocumentRepository.nextDocumentNumber();

        TransferDocumentEntity document = new TransferDocumentEntity();
        document.setDocumentNumber(String.format("%s%06d%s", PREFIX, sequence, SUFFIX));
        document.setTransferRequest(transferRequest);
        document.setCreatedAt(LocalDateTime.now());
        document.setPlayer(transferRequest.getPlayer());
        document.setSourceTeam(transferRequest.getSourceTeam());
        document.setTargetTeam(transferRequest.getTargetTeam());
        document.setAmount(transferRequest.getAmount());

        return transferDocumentRepository.save(document);
    }

    public List<TransferDocumentDTO> findAll(){
        List<TransferDocumentDTO> documents = new ArrayList<>();
        for(TransferDocumentEntity document: transferDocumentRepository.findAll())
            documents.add(transferDocumentMapper.toTransferDocumentDTO(document));

        return documents;
    }

    public TransferDocumentDTO findById(Long id){
        return transferDocumentMapper.toTransferDocumentDTO(transferDocumentRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("A dokumentum nem található a megadott ID-vel: " + id)));
    }

    public TransferDocumentEntity findEntityById(Long id){
        return transferDocumentRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("A dokumentum nem található a megadott ID-vel: " + id));
    }







}
