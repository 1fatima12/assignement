package ma.octo.assignement.web;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ma.octo.assignement.domain.AuditDeposit;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.DepositNotFoundException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.MoneyDepositMapper;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.MoneyDepositService;

@SpringBootApplication
@RestController
@RequiredArgsConstructor

public class MoneyDepositController {
	
	private final MoneyDepositService moneyDepositService;
	private final CompteRepository rep;
	
	private final MoneyDepositMapper moneyDepositMapper;
	
	@PostMapping("/saveDeposit")
	public ResponseEntity<MoneyDeposit> create(@RequestBody MoneyDepositDto moneyDepositDto){
		MoneyDeposit moneyDeposit = moneyDepositMapper.toMoneyDeposit(moneyDepositDto);
		moneyDeposit.setCompteBeneficiaire(rep.findByRib(moneyDepositDto.getRib()));
		moneyDepositService.save(moneyDeposit);
		return ResponseEntity.status(HttpStatus.CREATED).body(moneyDeposit);
				
	}
	@PostMapping("/depot")
    public void CreateDeposit(@RequestBody MoneyDepositDto moneyDepositDto) throws CompteNonExistantException, TransactionException {
        moneyDepositService.createDeposit(moneyDepositDto);
    	
    }
	@GetMapping("/deposits")
	public List<MoneyDeposit> getAll() {
		return moneyDepositService.getAllMoneyDeposit();
	}
	
	@PutMapping("/updateDeposit/{id}")
	public void updateDeposit(@RequestBody MoneyDepositDto m,@PathVariable Long id) throws DepositNotFoundException {
		MoneyDeposit moneyDeposit = moneyDepositMapper.toMoneyDeposit(m);

		moneyDepositService.updateDeposit(moneyDeposit,id);
	}
	@DeleteMapping("/deletDeposit/{id}")
	public void delete(@PathVariable("id") Long id) {
		moneyDepositService.deleteDdeposit(id);
	}


}
