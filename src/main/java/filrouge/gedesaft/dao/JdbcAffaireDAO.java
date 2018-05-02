package filrouge.gedesaft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



import filrouge.gedesaft.model.Affaire;


@Repository
public class JdbcAffaireDAO implements AffaireDAO{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource datasource;

	
	@Autowired
	public JdbcAffaireDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	// Liste des affaires
	@Override
	public List<Affaire> getListAffaires() throws Exception {
		Affaire affaire;
		PreparedStatement pstmt = null;
		
		ResultSet rs;
		String sql;
		
		ArrayList<Affaire> listAffaire = new ArrayList<Affaire>();

		Connection connection = datasource.getConnection();
		
		try {
			// Prepare the SQL query
			sql = "SELECT * FROM affaires ";
			//pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt=connection.prepareStatement(sql);
			
			// Log info
			logSQL(pstmt);

			// Run the query
			rs = pstmt.executeQuery();

			// Handle the query results
			while (rs.next()) {
				affaire = getAffaireFromResultSet(rs);
				listAffaire.add(affaire);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
			connection.close();
		}

		return listAffaire;
	}

	// Retourne une affaire d'après son ID
	@Override
	public Affaire getAffaire(Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Affaire affaire = null;

		try {
			// Prepare the SQL query
			String sql = "SELECT * FROM affaires WHERE id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);

			// Log info
			logSQL(pstmt);

			// Run the query
			rs = pstmt.executeQuery();
			
			// Handle the query results
			if (rs.next())
				affaire = getAffaireFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return affaire;
	}

	
	// Mise à jour d'une affaire
	@Override
	public Affaire updateAffaire(Affaire affaire) throws Exception {
		Affaire result = null;
		PreparedStatement pstmt = null;
		int i = 0;
				
		try {
			// Prepare the SQL query
			String sql = "UPDATE affaires SET dossier = ?, lieu = ?, dateOuverture = ? WHERE id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, affaire.getDossier());
			pstmt.setString(++i, affaire.getLieu());
			pstmt.setDate(++i, affaire.getDateOuverture());
			pstmt.setLong(++i, affaire.getId());
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("Affaire non trouvée !");
			
			result = affaire;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return result;
	}
	
	@Override
	public void deleteAffaire(Long id) throws Exception {
		PreparedStatement pstmt = null;
		
		try {			
			String sql = "DELETE FROM affaires WHERE id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);		
			int result = pstmt.executeUpdate();
			if(result != 1)
				throw new Exception("Affaire inexistante !");		
			System.out.println("Result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
	}

	

	
	
	private Affaire getAffaireFromResultSet(ResultSet rs) throws SQLException {
		Affaire affaire = new Affaire();
		affaire.setId(rs.getLong("id"));
		affaire.setDossier(rs.getString("dossier"));
		affaire.setLieu(rs.getString("lieu"));
		affaire.setDateOuverture(rs.getDate("dateOuverture"));
		/*affaire.setListAgentAffecte((List<AgentAffecte>) rs.getArray("listAgentAffecte"));
		affaire.setListArmeImpliquee((List<ArmeImpliquee>) rs.getArray("listArmeImpliquee"));
		affaire.setListSuspectImplique((List<SuspectImplique>) rs.getArray("listSuspectImplique"));
		affaire.setListTemoinImplique((List<TemoinImplique>) rs.getArray("listTemoinImplique"));
		affaire.setListVehiculeImplique((List<VehiculeImplique>) rs.getArray("listVehiculeImplique"));
		affaire.setListVictimeImpliquee((List<VictimeImpliquee>) rs.getArray("listVictimeImpliquee"));*/
		return affaire;
	}

	
	// Log des requêtes SQL
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}

	// Création d'une affaire
	@Override
	public Affaire createAffaire(Affaire affaire) throws Exception {
		PreparedStatement pstmt = null;
		Affaire result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		affaire.setId(new Long(0));

		try {
			// Prepare the SQL query
			String sql = "INSERT INTO affaires (id, dossier, lieu, dateOuverture) VALUES (?,?,?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setLong(++i, affaire.getId());
			pstmt.setString(++i, affaire.getDossier());
			pstmt.setString(++i, affaire.getLieu());
			pstmt.setDate(++i, affaire.getDateOuverture());
			
			
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			pstmt.executeUpdate();

			// TODO 
			// recupération de l'id genere, et maj de l'acteur avec l'id et la date de modif
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				affaire.setId(rs.getLong(1));
				
				result = affaire;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return result;
	}
	
	

	
}
