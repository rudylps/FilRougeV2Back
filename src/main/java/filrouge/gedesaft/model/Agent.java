package filrouge.gedesaft.model;

public class Agent extends Personne {

	private Long id;
	private String grade;
	
	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
