package filrouge.gedesaft.model;

public class Victime extends Personne {
	
	private Long id;
	private boolean vivant;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isVivant() {
		return vivant;
	}
	
	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}
	
}
