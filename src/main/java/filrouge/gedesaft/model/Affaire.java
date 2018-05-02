package filrouge.gedesaft.model;

import java.sql.Date;
import java.util.List;

public class Affaire {
	
	private Long id;
	private Date dateOuverture;
	private String dossier;
	private String lieu;
//	private List<VehiculeImplique> listVehiculeImplique;
//	private List<ArmeImpliquee> listArmeImpliquee;
//	private List<Suspect> listSuspect;
//	private List<Agent> listAgentAffecte;
//	private List<Victime> listVictime;
//	private List<Temoin> listTemoin;
	
	public Affaire() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public String getDossier() {
		return dossier;
	}

	public void setDossier(String dossier) {
		this.dossier = dossier;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

//	public List<VehiculeImplique> getListVehiculeImplique() {
//		return listVehiculeImplique;
//	}
//
//	public void setListVehiculeImplique(List<VehiculeImplique> listVehiculeImplique) {
//		this.listVehiculeImplique = listVehiculeImplique;
//	}
//
//	public List<ArmeImpliquee> getListArmeImpliquee() {
//		return listArmeImpliquee;
//	}
//
//	public void setListArmeImpliquee(List<ArmeImpliquee> listArmeImpliquee) {
//		this.listArmeImpliquee = listArmeImpliquee;
//	}
//
//	public List<Suspect> getListSuspect() {
//		return listSuspect;
//	}
//
//	public void setListSuspect(List<Suspect> listSuspect) {
//		this.listSuspect = listSuspect;
//	}
//
//	public List<Agent> getListAgentAffecte() {
//		return listAgentAffecte;
//	}
//
//	public void setListAgentAffecte(List<Agent> listAgentAffecte) {
//		this.listAgentAffecte = listAgentAffecte;
//	}
//
//	public List<Victime> getListVictime() {
//		return listVictime;
//	}
//
//	public void setListVictime(List<Victime> listVictime) {
//		this.listVictime = listVictime;
//	}
//
//	public List<Temoin> getListTemoin() {
//		return listTemoin;
//	}
//
//	public void setListTemoin(List<Temoin> listTemoin) {
//		this.listTemoin = listTemoin;
//	}
	
}
