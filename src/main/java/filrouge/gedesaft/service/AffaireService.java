package filrouge.gedesaft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import filrouge.gedesaft.dao.AffaireDAO;
import filrouge.gedesaft.model.Affaire;


@Service
public class AffaireService {
	
	@Autowired
	private AffaireDAO dao;
	
	public Affaire getAffaireDetail(Long id) throws Exception {
		return dao.getAffaire(id);
	}
	
	public List<Affaire> getAllAffaires() throws Exception {
		return dao.getListAffaires();
	}
	
	public Affaire addAffaire(Affaire affaire) throws Exception {
		return dao.createAffaire(affaire);
	}
	
	public Affaire updateAffaire(Long id, Affaire affaire) throws Exception {
		return dao.updateAffaire(affaire);
		
	}
	
	public void deleteAffaire(Long id) throws Exception {
		dao.deleteAffaire(id);
	}

}
