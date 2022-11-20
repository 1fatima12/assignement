package ma.octo.assignement.Services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

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
import ma.octo.assignement.serviceImpl.MoneyDepositServiceImpl;

public class MoneyDepositServiceTest {
	
	@InjectMocks
	private MoneyDepositServiceImpl moneyDepositServImpl;
	@Mock
	private MoneyDepositRepository moneyDepositRep;
	@Mock
	private CompteService compteService; 
	@Mock
	private AuditService auditservice;
	
	@Mock
	private CompteRepository compteRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getDeposits() {
		when(moneyDepositRep.findAll()).thenReturn(Stream.of(new MoneyDeposit()).collect(Collectors.toList()) );
		assertEquals(1, moneyDepositServImpl.getAllMoneyDeposit().size());
	}
	@Test
    public void deleteDeposit() {
        MoneyDepositDto moneyDepositDto = new MoneyDepositDto(1L, BigDecimal.valueOf(2000L), new Date(), "mardi", "Rib4", "depo");
    	MoneyDeposit deposit=new MoneyDeposit();
    	BeanUtils.copyProperties( moneyDepositDto,deposit);
    	moneyDepositServImpl.deleteDdeposit(deposit.getId());
    	verify(moneyDepositRep).deleteById(deposit.getId());
    }
    
	
	
    @Test
    void testCreateDeposit() throws CompteNonExistantException, TransactionException {
    	Compte compte4 = new Compte();
		compte4.setNrCompte("000000A0000001000");
		compte4.setRib("Rib4");
		compte4.setSolde(BigDecimal.valueOf(20000L));
    	MoneyDepositDto moneyDepositDto = new MoneyDepositDto(1L, BigDecimal.valueOf(2000L), new Date(), "mardi", "Rib4", "depo");
    	
    	MoneyDeposit deposit=new MoneyDeposit();
    	BeanUtils.copyProperties( moneyDepositDto,deposit);
        when(compteService.findByRib(anyString())).thenReturn(compte4);
        moneyDepositServImpl.createDeposit(moneyDepositDto);
        verify(compteService).save(compte4);
    	
    	
    }

}
