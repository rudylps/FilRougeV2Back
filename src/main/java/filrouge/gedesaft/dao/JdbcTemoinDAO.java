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
import filrouge.gedesaft.model.Temoin;




@Repository
public class JdbcTemoinDAO implements TemoinDAO{
	

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource datasource;

	@Autowired
	public JdbcTemoinDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Temoin> getListTemoins() throws Exception {
		Temoin temoin;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Temoin> aLlistOfTemoin = new ArrayList<Temoin>();
		try {
			sql = "SELECT * FROM temoins INNER JOIN personnes ON temoins.id = personnes.idPersonne";
			pstmt = datasource.getConnection().prepareStatement(sql);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temoin = getTemoinFromResultSet(rs);
				aLlistOfTemoin.add(temoin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return aLlistOfTemoin;
	}


	@Override
	public Temoin getTemoin(Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Temoin temoin = null;
		try {
			String sql = "SELECT * FROM temoins INNER JOIN personnes ON temoins.id = personnes.idPersonne WHERE id= ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				temoin = getTemoinFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return temoin;
	}

	@Override
	public Temoin insertTemoin(Temoin temoin) throws Exception {
		PreparedStatement pstmt = null;
		Temoin result = null;
		int i = 0;
		temoin.setId(new Long(0));
		try {
			String sql = "INSERT INTO personnes (idPersonne) VALUES (?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setLong(++i, temoin.getId());		
			logSQL(pstmt);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				temoin.setId(rs.getLong(1));		
				result = temoin;
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

	@Override
	public Temoin updateTemoin(Temoin temoin) throws Exception {
		Temoin result = null;
		PreparedStatement pstmt = null;
		int i = 0;				
		try {
			String sql = "UPDATE personnes SET nom= ?, prenom= ?, dateDeNaissance= ? WHERE idPersonne = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);		
			pstmt.setString(++i, temoin.getNom());
			pstmt.setString(++i, temoin.getPrenom());
			pstmt.setDate(++i, temoin.getDateNaissance());
			pstmt.setLong(++i, temoin.getIdPersonne());
			logSQL(pstmt);
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("temoin not found !");		
			result = temoin;
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
	public void deleteTemoin(Long id) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM temoins WHERE id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);		
			int result = pstmt.executeUpdate();
			if(result != 1)
				throw new Exception("Temoin not found !");		
			System.out.println("Result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
	}

	@Override
	public List<Affaire> getAffairesOfTemoin(Long id) throws Exception {
		Affaire affaire;;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Affaire> aLlistOfAffaire = new ArrayList<Affaire>();
		try {
			sql = "SELECT * FROM affaires"
				+ " JOIN temoinsimpliques ON affaires.id = temoinsImpliques.affaires_id"
				+ " JOIN temoins ON temoins_id = temoins.id"
				+ " WHERE temoins.id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				affaire = getAffaireFromResultSet(rs);
				aLlistOfAffaire.add(affaire);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return aLlistOfAffaire;
	}
	
	private Temoin getTemoinFromResultSet(ResultSet rs) throws Exception {
		Temoin temoin = new Temoin();
		temoin.setId(rs.getLong("id"));
		temoin.setIdPersonne(rs.getLong("idPersonne"));
		temoin.setNom(rs.getString("nom"));
		temoin.setPrenom(rs.getString("prenom"));
		temoin.setDateNaissance(rs.getDate("dateDeNaissance"));
		return temoin;
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