package ma.octo.assignement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;

@DataJpaTest
public class CompteRepositoryTest {
	@Autowired
	private CompteRepository compteRepository;
	
	
	String rib="RIB4";
    String num="000000A000001000";
	@Test
	void itShouldCheckIfExistByRib() {
		//given
	
		
		Compte compte4 = new Compte();
		compte4.setNrCompte("000000A000001000");
		compte4.setRib(rib);
		compte4.setSolde(BigDecimal.valueOf(20000L));
		compteRepository.save(compte4);
		
		Compte c=compteRepository.findByRib(rib);
        assertNotNull(c);
		
	}
	
    @Test
    void itShouldChecksIfExistByNrCompte() {
    	Compte compte4 = new Compte();
		compte4.setNrCompte(num);
		compte4.setRib(rib);
		compte4.setSolde(BigDecimal.valueOf(20000L));
		compteRepository.save(compte4);
		Compte c=compteRepository.findByNrCompte(num);
    	assertNotNull(c);
    }
}
