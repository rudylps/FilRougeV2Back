package filrouge.gedesaft.model;

// classe "représentant" un objet d'une table pour l'affichage, sans stocké toutes les données dudit objet
public class Representation {
	
	Long idField;
	String FieldName;
	
	public Long getIdField() {
		return idField;
	}
	
	public void setIdField(Long idField) {
		this.idField = idField;
	}
	
	public String getFieldName() {
		return FieldName;
	}
	
	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	
}
