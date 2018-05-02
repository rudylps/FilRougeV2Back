package filrouge.gedesaft.dao;

import java.util.List;

import filrouge.gedesaft.model.Affaire;
import filrouge.gedesaft.model.Temoin;

public interface TemoinDAO {

	public List<Temoin> getListTemoins() throws Exception;

	public Temoin getTemoin(Long id) throws Exception;

	public Temoin insertTemoin(Temoin temoin) throws Exception;

	public Temoin updateTemoin(Temoin temoin) throws Exception;
	
	public void deleteTemoin(Long id) throws Exception;
	
	public List<Affaire> getAffairesOfTemoin(Long id) throws Exception;

}