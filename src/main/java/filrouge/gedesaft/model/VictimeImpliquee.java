package filrouge.gedesaft.model;

import java.util.Date;

public class VictimeImpliquee {
	
	private Long id;
	private Date dateImplication;
	private String typeAgression;
	
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
	
	public String getTypeAgression() {
		return typeAgression;
	}
	
	public void setTypeAgression(String typeAgression) {
		this.typeAgression = typeAgression;
	}
	
}
