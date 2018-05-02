package filrouge.gedesaft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import filrouge.gedesaft.dao.TemoinDAO;
import filrouge.gedesaft.model.Affaire;
import filrouge.gedesaft.model.Temoin;

@Service
public class TemoinService {
	
	@Autowired
	private TemoinDAO dao;
	
	public Temoin getTemoinDetail(Long id) throws Exception {
		return dao.getTemoin(id);
	}
	
	public List<Temoin> getAllTemoins() throws Exception {
		return dao.getListTemoins();
	}
	
	public Temoin addTemoin(Temoin temoin) throws Exception {
		return dao.insertTemoin(temoin);
	}
	
	public Temoin updateTemoin(Long id, Temoin temoin) throws Exception {
		return dao.updateTemoin(temoin);
	}
	
	public void deleteTemoin(Long id) throws Exception {
		dao.deleteTemoin(id);
	}
	
	public List<Affaire> getAllAffairesOfTemoin (Long id) throws Exception {
		return dao.getAffairesOfTemoin(id);
	}

}

