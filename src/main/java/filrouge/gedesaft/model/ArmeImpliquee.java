package filrouge.gedesaft.model;

import java.util.Date;

public class ArmeImpliquee {
	
	private Long id;
	private Date dateImplication;
	private Arme arme;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateImplication() {
		return dateImplication;
	}
	
	public void setDateimplication(Date dateImplication) {
		this.dateImplication = dateImplication;
	}

	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}

	public void setDateImplication(Date dateImplication) {
		this.dateImplication = dateImplication;
	}

}
