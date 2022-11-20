package ma.octo.assignement.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.service.CompteService;

@Service
@Transactional
public class CompteServiceImpl implements CompteService{
  
	@Autowired
	private  CompteRepository repositoryCompte;

	@Override
	public Compte save(Compte compte) {
		// TODO Auto-generated method stub
		return repositoryCompte.save(compte);
	}


	@Override
	public Compte findByNrCompte(String nrCompte) throws CompteNonExistantException {
		// TODO Auto-generated method stub
		Compte c= repositoryCompte.findByNrCompte(nrCompte);
		if(c==null) {
			throw new CompteNonExistantException("le compte n'existe pas ");
		}
		return c;
	}

	@Override
	public Compte findByRib(String rib) throws CompteNonExistantException {
		// TODO Auto-generated method stub
		Compte c= repositoryCompte.findByRib(rib);
		if(c==null) {
			throw new CompteNonExistantException("le compte n'existe pas");
		}
		return c;
	}


}
