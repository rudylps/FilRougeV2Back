package filrouge.gedesaft.model;

public class Arme {
	
	private Long id;
	private String type;
	private Long noSerie;
	private String modele;
	private String calibre;
	private Personne proprietaire;
	
	public Arme () {
		super();
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getNoSerie() {
		return noSerie;
	}

	public void setNoSerie(Long noSerie) {
		this.noSerie = noSerie;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getCalibre() {
		return calibre;
	}

	public void setCalibre(String calibre) {
		this.calibre = calibre;
	}

	public Personne getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Personne proprietaire) {
		this.proprietaire = proprietaire;
	}

}
