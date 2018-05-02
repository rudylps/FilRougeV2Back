package filrouge.gedesaft.model;

import java.util.Date;

public class AgentAffecte {
	
	private Long id;
	private Date dateAffectation;
	private Agent agent;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateAffectation() {
		return dateAffectation;
	}
	
	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
}
