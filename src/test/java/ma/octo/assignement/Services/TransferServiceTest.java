package ma.octo.assignement.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.beans.factory.annotation.Autowired;

import ma.octo.assignement.domain.AuditTransfer;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.serviceImpl.MoneyDepositServiceImpl;
import ma.octo.assignement.serviceImpl.transfertServiceImpl;

public class TransferServiceTest {
	@Mock
	private TransferRepository transferRep;
	@Mock
	private CompteService compteService; 
	@Mock
	private AuditService auditservice;
	@InjectMocks
	private transfertServiceImpl transfertServiceimpl;
	@Autowired
	CompteRepository compteRepository;
	Compte compte4 = new Compte();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		compte4.setNrCompte("000000A000001000");
		compte4.setRib("Rib4");
		compte4.setSolde(BigDecimal.valueOf(20000L));
	}
	AuditTransfer auditTransfer;
	
	@Test
	public void getTransfer() {
		when(transferRep.findAll()).thenReturn(Stream.of(new Transfer()).collect(Collectors.toList()) );
		assertEquals(1, transfertServiceimpl.getAllTransfer().size());
	}
	
	@Test
    public void deleteTransfer() {
       
		Transfer t =new Transfer();
		t.setCompteBeneficiaire(compte4);
		transfertServiceimpl.deleteTransfer(t.getId());
    	verify(transferRep).deleteById(t.getId());
    }
	
	@Test
	public void testCreateTransfer() throws CompteNonExistantException, TransactionException {
		Compte compte5=new Compte();
		compte5.setNrCompte("000000A000001000");
		compte5.setRib("Rib5");
		compte5.setSolde(BigDecimal.valueOf(20000L));
		TransferDto transferDto=new TransferDto();
		transferDto.setMontant(BigDecimal.TEN);
		transferDto.setMotif("bi");
		transferDto.setNrCompteBeneficiaire(compte4.getNrCompte());
		transferDto.setNrCompteEmetteur(compte5.getNrCompte());
		when(compteService.findByNrCompte(anyString())).thenReturn(compte4);
		when(compteService.findByNrCompte(anyString())).thenReturn(compte5);
		transfertServiceimpl.createTransfer(transferDto);
       
	}
}
