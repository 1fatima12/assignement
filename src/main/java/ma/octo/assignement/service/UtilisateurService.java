package ma.octo.assignement.service;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.exceptions.UtilisateurNotFoundException;

public interface UtilisateurService {
	public void save(Utilisateur user);
    public Utilisateur findById(Long id) throws UtilisateurNotFoundException ;
    public void update(Long id,Utilisateur utili) throws UtilisateurNotFoundException ;
    public java.util.List<Utilisateur> findAll();
    public void deleteById(Long id);
    public Utilisateur findUByUserName(String username);
}
