package filrouge.gedesaft.dao;

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
import filrouge.gedesaft.model.Personne;
import filrouge.gedesaft.model.Vehicule;
import filrouge.gedesaft.model.Victime;

@Repository
public class JdbcVictimeDAO implements VictimeDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource datasource;

	@Autowired
	public JdbcVictimeDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}
	
	@Override
	public List<Victime> getListVictimes() throws Exception {
		Victime victime;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Victime> aLlistOfVictime = new ArrayList<Victime>();
		try {
			sql = "SELECT * FROM victimes";
			pstmt = datasource.getConnection().prepareStatement(sql);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				victime = getVictimeFromResultSet(rs);
				aLlistOfVictime.add(victime);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return aLlistOfVictime;
	}

	@Override
	public Victime getVictime(Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Victime victime = null;
		try {
			String sql = "SELECT * FROM victimes WHERE id= ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				victime = getVictimeFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return victime;
	}
	
	private Personne getPersonne (Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Personne personne = null;
		try {
			String sql = "SELECT * FROM personnes WHERE idPersonne= ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				personne = getPersonneFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return personne;
	}

	@Override
	public Victime insertVictime(Vehicule vehicule) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Victime updateVictime(Vehicule vehicule) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVictime(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Affaire> getAffairesOfVictime(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Victime getVictimeFromResultSet(ResultSet rs) throws Exception {
		Victime victime = new Victime();
		victime.setId(rs.getLong("id"));
		victime.setVivant(rs.getBoolean("vivant"));
		victime.setNom(getPersonne(rs.getLong("personnes_idPersonne")).getNom());
		victime.setPrenom(getPersonne(rs.getLong("personnes_idPersonne")).getPrenom());
		victime.setDateNaissance(getPersonne(rs.getLong("personnes_idPersonne")).getDateNaissance());
		return victime;
	}
	
	
	
	private Personne getPersonneFromResultSet(ResultSet rs) throws SQLException {
		Personne personne = new Personne();
		personne.setIdPersonne(rs.getLong("idPersonne"));
		personne.setNom(rs.getString("nom"));
		personne.setPrenom(rs.getString("prenom"));
		personne.setDateNaissance(rs.getDate("DateNaissance"));
		return personne;
	}
	
	private Affaire getAffaireFromResultSet(ResultSet rs) throws SQLException {
		Affaire affaire = new Affaire();
		affaire.setId(rs.getLong("id"));
		affaire.setDossier(rs.getString("dossier"));
		affaire.setLieu(rs.getString("lieu"));
		affaire.setDateOuverture(rs.getDate("DateOuverture"));
		return affaire;
	}

	private void logSQL(PreparedStatement pstmt) {
		String sql;		
		if (pstmt == null)
			return;		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}

}
