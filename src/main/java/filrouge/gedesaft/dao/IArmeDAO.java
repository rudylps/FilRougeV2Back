package filrouge.gedesaft.dao;

import java.util.List;

import filrouge.gedesaft.model.Arme;

public interface IArmeDAO {
	
	public List<Arme> getListArmes() throws Exception;

	public Arme getArme(Long id) throws Exception;

	public Arme insertArme(Arme arme) throws Exception;

	public Arme updateArme(Arme arme) throws Exception;
	
	public void deleteArme(Long id) throws Exception;

	Arme saveArme(Arme arme);

}
