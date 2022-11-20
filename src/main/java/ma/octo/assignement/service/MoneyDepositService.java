package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.DepositNotFoundException;
import ma.octo.assignement.exceptions.TransactionException;

public interface MoneyDepositService {
	public MoneyDeposit save(MoneyDeposit moneydeposit);
	public List<MoneyDeposit> getAllMoneyDeposit();
	public MoneyDeposit getDepositById(Long id) throws DepositNotFoundException;
	public MoneyDeposit updateDeposit(MoneyDeposit moneydeposit,Long id) throws DepositNotFoundException;
	public void deleteDdeposit(Long id);
    public MoneyDeposit createDeposit(MoneyDepositDto moneydepositdto) throws CompteNonExistantException, TransactionException;

}
