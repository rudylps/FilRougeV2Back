package filrouge.gedesaft.dao;

import java.util.List;

import filrouge.gedesaft.model.Affaire;
import filrouge.gedesaft.model.Vehicule;
import filrouge.gedesaft.model.Victime;

public interface VictimeDAO {

	public List<Victime> getListVictimes() throws Exception;

	public Victime getVictime(Long id) throws Exception;

	public Victime insertVictime(Vehicule vehicule) throws Exception;

	public Victime updateVictime(Vehicule vehicule) throws Exception;
	
	public void deleteVictime(Long id) throws Exception;
	
	public List<Affaire> getAffairesOfVictime(Long id) throws Exception;
}
