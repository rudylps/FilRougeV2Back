package filrouge.gedesaft.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import filrouge.gedesaft.dao.VehiculeDAO;
import filrouge.gedesaft.model.Affaire;
import filrouge.gedesaft.model.Vehicule;

@Service
public class VehiculeService {

	// Injection d'une instance de l'interface VehiculeDAO
	@Autowired
	private VehiculeDAO dao;
	
	// Obtenir un véhicule
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Vehicule getVehiculeDetail(Long id) throws Exception {
		return dao.getVehicule(id);
	}
	
	// Obtenir la liste des véhicules
	/**
	 * @return
	 * @throws Exception
	 */
	public List<Vehicule> getAllVehicules() throws Exception {
		return dao.getListVehicules();
	}
	
	// Créer un véhicule
	/**
	 * @param vehicule
	 * @return
	 * @throws Exception
	 */
	public Vehicule addVehicule(Vehicule vehicule) throws Exception {
		if (verifierImmatriculation(vehicule.getImmatriculation())) {
			return dao.updateVehicule(vehicule);
		};
		return null;
	}
	
	// Editer un véhicule
	/**
	 * @param id
	 * @param vehicule
	 * @return
	 * @throws Exception
	 */
	public Vehicule updateVehicule(Long id, Vehicule vehicule) throws Exception {
		if (verifierImmatriculation(vehicule.getImmatriculation())) {
			return dao.updateVehicule(vehicule);
		} else {
		return null;
		}
	}
	
	// Supprimer un véhicule
	/**
	 * @param id
	 * @throws Exception
	 */
	public void deleteVehicule(Long id) throws Exception {
		dao.deleteVehicule(id);
	}
	
	// Obtenir la liste des affaires dans lesquelles un véhicule est impliqué
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Affaire> getAllAffairesOfVehicule (Long id) throws Exception {
		return dao.getAffairesOfVehicule(id);
	}
	
	// Controler de la conformité de la plaque d'immatriculation
	/**
	 * @param immatriculation
	 * @return
	 */
	private boolean verifierImmatriculation(String immatriculation) {
		Pattern p = Pattern.compile("^[A-Z]{1,3}-[0-9]{1,3}-[A-Z]{1,3}$");
		Matcher m = p.matcher(immatriculation);
		return m.find();
	}
}
