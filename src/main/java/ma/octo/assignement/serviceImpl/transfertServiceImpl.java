package ma.octo.assignement.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.AuditTransfer;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.exceptions.TransferNotFoundException;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.transfertService;


@Service
@Transactional
public class transfertServiceImpl implements transfertService{
public static final int MONTANT_MAXIMAL = 10000;
Logger LOGGER = LoggerFactory.getLogger(transfertServiceImpl.class);

	
	@Autowired
	private TransferRepository transferRep;
	
	@Autowired
	private  CompteService serviceCompte;

	@Autowired
    private AuditService auditservice;
	@Override
	public Transfer save(Transfer t) {
		// TODO Auto-generated method stub
		return transferRep.save(t);
	}

	@Override
	public List<Transfer> getAllTransfer() {
		// TODO Auto-generated method stub
		return transferRep.findAll();
	}

	@Override
	public Transfer getTrabsferById(Long id) throws TransferNotFoundException {
		// TODO Auto-generated method stub
		return transferRep.findById(id).orElseThrow(()-> new TransferNotFoundException("Ce transfert n'existe pas"));
	}

	@Override
	public Transfer updateTransfer(Transfer t, Long id) throws TransferNotFoundException {
		// TODO Auto-generated method stub
		Transfer transfer = transferRep.findById(id).orElseThrow(()-> new TransferNotFoundException("Ce transfert n'existe pas"));
		transfer.setMontantTransfer(t.getMontantTransfer());
		transfer.setCompteBeneficiaire(t.getCompteBeneficiaire());
		transfer.setCompteEmetteur(t.getCompteEmetteur());
		transfer.setDateExecution(t.getDateExecution());
		transfer.setMotifTransfer(t.getMotifTransfer());
		return transferRep.save(transfer);
 		}

	@Override
	public void deleteTransfer(Long id) {
transferRep.deleteById(id);		
	}

	@Override
	public Transfer createTransfer(TransferDto transferDto) throws CompteNonExistantException, TransactionException {
		  Compte c1 = serviceCompte.findByNrCompte(transferDto.getNrCompteEmetteur());
	        Compte f12 = serviceCompte.findByNrCompte(transferDto.getNrCompteBeneficiaire());

	        

	        if (transferDto.getMontant().equals(null)) {
	            System.out.println("Montant vide");
	            throw new TransactionException("Montant vide");
	        } else if (transferDto.getMontant().intValue() == 0) {
	            System.out.println("Montant vide");
	            throw new TransactionException("Montant vide");
	        } else if (transferDto.getMontant().intValue() < 10) {
	            System.out.println("Montant minimal de transfer non atteint");
	            throw new TransactionException("Montant minimal de transfer non atteint");
	        } else if (transferDto.getMontant().intValue() > MONTANT_MAXIMAL) {
	            System.out.println("Montant maximal de transfer dépassé");
	            throw new TransactionException("Montant maximal de transfer dépassé");
	        }

	        if (transferDto.getMotif().length() < 0) {
	            System.out.println("Motif vide");
	            throw new TransactionException("Motif vide");
	        }

	        if (c1.getSolde().intValue() - transferDto.getMontant().intValue() < 0) {
	            LOGGER.error("Solde insuffisant pour l'utilisateur");
	        }



	        c1.setSolde(c1.getSolde().subtract(transferDto.getMontant()));
	        serviceCompte.save(c1);

	        f12
	                .setSolde(new BigDecimal(f12.getSolde().intValue() + transferDto.getMontant().intValue()));
	        serviceCompte.save(f12);

	        Transfer transfer = new Transfer();
	        transfer.setDateExecution(transferDto.getDate());
	        transfer.setCompteBeneficiaire(f12);
	        transfer.setCompteEmetteur(c1);
	        transfer.setMontantTransfer(transferDto.getMontant());

	        Transfer t=transferRep.save(transfer);

	        AuditTransfer auditTransfer = auditservice.auditTransfer("Transfer depuis " + transferDto.getNrCompteEmetteur() + " vers " + transferDto
	                        .getNrCompteBeneficiaire() + " d'un montant de " + transferDto.getMontant()
	                        .toString());
	        return t;
	    }

	  

}
