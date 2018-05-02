package filrouge.gedesaft.model;

import java.util.Date;

public class VehiculeImplique {

	private Long id;
	private Date dateImplication;
	private Vehicule vehicule;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateAffectation() {
		return dateImplication;
	}
	
	public void setDateAffectation(Date dateAffectation) {
		this.dateImplication = dateAffectation;
	}

	public Date getDateImplication() {
		return dateImplication;
	}

	public void setDateImplication(Date dateImplication) {
		this.dateImplication = dateImplication;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
}
