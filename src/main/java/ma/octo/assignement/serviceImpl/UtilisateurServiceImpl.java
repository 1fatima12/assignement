package ma.octo.assignement.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.exceptions.UtilisateurNotFoundException;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.UtilisateurService;

@Service
@Transactional

public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
    
	@Override
	public void save(Utilisateur user) {

		utilisateurRepository.save(user);
	}

	@Override
	public Utilisateur findById(Long id) throws UtilisateurNotFoundException {
		// TODO Auto-generated method stub
		return utilisateurRepository.findById(id).orElseThrow(()->new UtilisateurNotFoundException("cette utilisateur n'existe pas"));
	}

	@Override
	public void update(Long id, Utilisateur utili) throws UtilisateurNotFoundException {
		// TODO Auto-generated method stub
		Utilisateur user=utilisateurRepository.findById(id).orElseThrow(()->new UtilisateurNotFoundException("cette utilisateur n'existe pas"));
		user.setBirthdate(utili.getBirthdate());
		user.setFirstName(utili.getFirstName());
		user.setGender(utili.getGender());
		user.setUsername(utili.getUsername());
		
	}

	@Override
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
       utilisateurRepository.deleteById(id);		
	}
   

	@Override
	public Utilisateur findUByUserName(String username) {
		Utilisateur u= utilisateurRepository.findByUsername(username);
    	System.out.println(u);
		return u;
	}

	

}
