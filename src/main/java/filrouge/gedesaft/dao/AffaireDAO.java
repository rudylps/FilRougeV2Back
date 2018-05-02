package filrouge.gedesaft.dao;

import java.util.List;

import filrouge.gedesaft.model.Affaire;

public interface AffaireDAO {
	
	public List<Affaire> getListAffaires() throws Exception;
	
	public Affaire getAffaire(Long id) throws Exception;
	
	public Affaire createAffaire(Affaire affaire) throws Exception;
	
	public Affaire updateAffaire(Affaire affaire) throws Exception;
	
	public void deleteAffaire(Long id) throws Exception;

}
