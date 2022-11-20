package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.exceptions.TransferNotFoundException;


public interface transfertService {

    public Transfer save(Transfer t);
    public List<Transfer> getAllTransfer();
    public Transfer getTrabsferById(Long id) throws TransferNotFoundException ;
    public Transfer updateTransfer(Transfer t,Long id) throws TransferNotFoundException;
    public void deleteTransfer(Long id);
    public Transfer createTransfer(TransferDto transferDto) throws CompteNonExistantException, TransactionException;
}
