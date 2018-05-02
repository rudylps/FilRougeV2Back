package filrouge.gedesaft.model;

import java.util.Date;

public class Suspect extends Personne {
	
	private Long id;
	private Date dateImplication;
	private String couleurCheveux;
	private String couleurPeau;
	private String signeParticulier;
	private int taille;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateImplication() {
		return dateImplication;
	}
	
	public void setDateImplication(Date dateImplication) {
		this.dateImplication = dateImplication;
	}
	
	public String getCouleurCheveux() {
		return couleurCheveux;
	}
	
	public void setCouleurCheveux(String couleurCheveux) {
		this.couleurCheveux = couleurCheveux;
	}
	
	public String getCouleurPeau() {
		return couleurPeau;
	}
	
	public void setCouleurPeau(String couleurPeau) {
		this.couleurPeau = couleurPeau;
	}
	
	public String getSigneParticulier() {
		return signeParticulier;
	}
	
	public void setSigneParticulier(String signeParticulier) {
		this.signeParticulier = signeParticulier;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}

}
