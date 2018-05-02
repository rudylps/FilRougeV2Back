package filrouge.gedesaft.model;

import java.util.Date;

public class TemoinImplique {
	
	private Long id;
	private Date dateDeposition;
	private String deposition;
	private Temoin temoin;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateDeposition() {
		return dateDeposition;
	}
	
	public void setDateDeposition(Date dateDeposition) {
		this.dateDeposition = dateDeposition;
	}
	
	public String getDeposition() {
		return deposition;
	}
	
	public void setDeposition(String deposition) {
		this.deposition = deposition;
	}
	
	public Temoin getTemoin() {
		return temoin;
	}
	
	public void setTemoin(Temoin temoin) {
		this.temoin = temoin;
	}

}
