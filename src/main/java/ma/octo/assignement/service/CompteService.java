package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.exceptions.CompteNonExistantException;

public interface CompteService {
	public Compte findByRib(String rib) throws CompteNonExistantException;
	public Compte save(Compte compte);
	public Compte findByNrCompte(String nrCompte) throws CompteNonExistantException;

}
