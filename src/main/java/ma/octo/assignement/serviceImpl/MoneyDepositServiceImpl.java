package ma.octo.assignement.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.octo.assignement.domain.AuditDeposit;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.DepositNotFoundException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.MoneyDepositRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.MoneyDepositService;

@Service
@AllArgsConstructor
@Transactional
public class MoneyDepositServiceImpl implements MoneyDepositService{
    public static final int MONTANT_MAXIMAL = 10000;

	
	
	private final MoneyDepositRepository moneyDepositRep;
	
	
	private final CompteService serviceCompte;

	private final CompteRepository repCompte;

    private final AuditService auditservice;
	
	@Override
	public MoneyDeposit save(MoneyDeposit moneydeposit) {
		// TODO Auto-generated method stub
		return moneyDepositRep.save(moneydeposit);
	}

	@Override
	public List<MoneyDeposit> getAllMoneyDeposit() {
		// TODO Auto-generated method stub
		return moneyDepositRep.findAll();
	}

	@Override
	public MoneyDeposit getDepositById(Long id) throws DepositNotFoundException {
		// TODO Auto-generated method stub
		return moneyDepositRep.findById(id).orElseThrow(()->new DepositNotFoundException("Ce deposit n'existe pas"));
	}

	@Override
	public MoneyDeposit updateDeposit(MoneyDeposit moneydeposit, Long id) throws DepositNotFoundException {
		// TODO Auto-generated method stub
		MoneyDeposit moneyDeposit = moneyDepositRep.findById(id).orElseThrow(()-> new DepositNotFoundException("Ce deposit n'existe pas"));
		moneyDeposit.setMontant(moneydeposit.getMontant());
		moneyDeposit.setDateExecution(moneydeposit.getDateExecution());
		moneyDeposit.setNom_prenom_emetteur(moneydeposit.getNom_prenom_emetteur());
		moneyDeposit.setMotifDeposit(moneydeposit.getMotifDeposit());
		moneyDepositRep.save(moneyDeposit);
 		return moneyDeposit;
	}

	@Override
	public void deleteDdeposit(Long id) {
        moneyDepositRep.deleteById(id);		
	}

	@Override
	public MoneyDeposit createDeposit(MoneyDepositDto moneydepositdto) throws CompteNonExistantException, TransactionException {
    	Compte c= serviceCompte.findByRib(moneydepositdto.getRib());
    	if (moneydepositdto.getMontant().equals(null)  ) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");

        } else if (moneydepositdto.getMontant().intValue() < 10) {
            System.out.println("Montant minimal de transfer non atteint");
            throw new TransactionException("Montant minimal de transfer non atteint");

        } else if (moneydepositdto.getMontant().intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de transfer dépassé");
            throw new TransactionException("Montant maximal de transfer dépassé");

        }

        if (moneydepositdto.getMotifDeposit().length() < 0) {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }

        c.setSolde(new BigDecimal(c.getSolde().intValue()+moneydepositdto.getMontant().intValue()));
        serviceCompte.save(c);
        

        MoneyDeposit deposit= new MoneyDeposit();
        deposit.setDateExecution(moneydepositdto.getDateExecution());
        deposit.setMontant(moneydepositdto.getMontant());
        deposit.setMotifDeposit(moneydepositdto.getMotifDeposit());
        deposit.setNom_prenom_emetteur(moneydepositdto.getNom_prenom_emetteur());
        deposit.setCompteBeneficiaire(c);
        MoneyDeposit m=moneyDepositRep.save(deposit);
        
  
		auditservice.auditDeposit("Depot depuis "+moneydepositdto.getNom_prenom_emetteur()+"vers"+c.getNrCompte()
		+"d'un monant"+moneydepositdto.getMontant().toString());
		return m;
		
	}

	

}
