package filrouge.gedesaft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import filrouge.gedesaft.dao.IArmeDAO;
import filrouge.gedesaft.model.Arme;

@Service
public class ArmeService {

	@Autowired
	private IArmeDAO dao;
	
	public Arme getArme(Long id) throws Exception {
		return dao.getArme(id);
	}
	
	public List<Arme> getListArmes() throws Exception {
		return dao.getListArmes();
	}
	
	public Arme insertArme(Arme arme) throws Exception {
		return dao.insertArme(arme);
	}
	
//	public Arme updateArme(Long id, Arme arme) throws Exception {
//		return dao.updateArme(arme);
//	}
	public Arme updateArme(Arme arme) throws Exception {
		return dao.updateArme(arme);
	}
	
	public Arme saveArme(Arme arme) throws Exception {
		return dao.saveArme(arme);
	}
	
	public void deleteArme(Long id) throws Exception {
		dao.deleteArme(id);
	}
}
