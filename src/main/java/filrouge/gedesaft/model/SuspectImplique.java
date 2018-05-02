package filrouge.gedesaft.model;

import java.util.Date;

public class SuspectImplique {
	
	private Long id;
	private Date dateImplication;
	private Suspect suspect;
	
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

	public Suspect getSuspect() {
		return suspect;
	}

	public void setSuspect(Suspect suspect) {
		this.suspect = suspect;
	}

}
