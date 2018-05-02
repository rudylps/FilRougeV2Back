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

import filrouge.gedesaft.model.Arme;
import filrouge.gedesaft.model.Personne;

@Repository
public class JdbcArmeDAO implements IArmeDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource datasource;

	@Autowired
	public JdbcArmeDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Arme> getListArmes() throws Exception {
		Arme arme;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Arme> listeArmes = new ArrayList<Arme>();
		try {
			sql = "SELECT * FROM armes ";
			pstmt = datasource.getConnection().prepareStatement(sql);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arme = getArmeFromResultSet(rs);
				listeArmes.add(arme);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return listeArmes;
	}

	@Override
	public Arme getArme(Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Arme arme = null;
		try {
			String sql = "SELECT * FROM armes WHERE id= ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				arme = getArmeFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return arme;
	}

	private Personne getPersonne(Long id) throws Exception {
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
	public Arme insertArme(Arme arme) throws Exception {
		PreparedStatement pstmt = null;
		Arme result = null;
		int i = 0;
		arme.setId(new Long(0));
		try {
			String sql = "INSERT INTO armes (id, type, noSerie, modele, calibre) VALUES (?,?,?,?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setLong(++i, arme.getId());
			pstmt.setString(++i, arme.getType());
			pstmt.setLong(++i, arme.getNoSerie());
			pstmt.setString(++i, arme.getModele());
			pstmt.setString(++i, arme.getCalibre());
			logSQL(pstmt);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				arme.setId(rs.getLong(1));
				result = arme;
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
	public Arme saveArme(Arme arme) {
		Arme result = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			// Prepare the SQL query
			String sql = "UPDATE armes SET type= ?, noSerie= ?, modele= ?, calibre= ?"
//			+ ", personnes_idPersonne= ?"
			+ "WHERE id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			
			pstmt.setString(++i, arme.getType());
			pstmt.setLong(++i, arme.getNoSerie());
			pstmt.setString(++i, arme.getModele());
			pstmt.setString(++i, arme.getCalibre());
//			pstmt.setLong(++i, arme.getProprietaire().getIdPersonne());
			pstmt.setLong(++i, arme.getId());
			logSQL(pstmt);
			
			// Run the the update query
			int resultCount = pstmt.executeUpdate();
			if (resultCount != 1)

				log.error("Arme not found !");

			result = arme;

		} catch (SQLException e) {
			log.error("SQL Error !:", e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// nothing to do
			}
		}

		return result;
	}

	
	@Override
	public Arme updateArme(Arme arme) throws Exception {
		Arme result = null;
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			String sql = "UPDATE armes SET type= ?, noSerie= ?, modele= ?, calibre= ?, personnes_idPersonne= ? WHERE id = ?";
//			String sql = "UPDATE armes SET type= ?, noSerie= ?, modele= ?, calibre= ? WHERE id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, arme.getType());
			pstmt.setLong(++i, arme.getNoSerie());
			pstmt.setString(++i, arme.getModele());
			pstmt.setString(++i, arme.getCalibre());
			pstmt.setLong(++i, arme.getProprietaire().getIdPersonne());
			pstmt.setLong(++i, arme.getId());
			logSQL(pstmt);
			int resultCount = pstmt.executeUpdate();
			if (resultCount != 1)
				throw new Exception("arme introuvable !");
			result = arme;
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
	public void deleteArme(Long id) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM armes WHERE id = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			int result = pstmt.executeUpdate();
			if (result != 1)
				throw new Exception("arme introuvable !");
			System.out.println("Result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
	}

	private Arme getArmeFromResultSet(ResultSet rs) throws Exception {
		Arme arme = new Arme();
		arme.setId(rs.getLong("id"));
		arme.setType(rs.getString("type"));
		arme.setNoSerie(rs.getLong("noSerie"));
		arme.setModele(rs.getString("modele"));
		arme.setCalibre(rs.getString("calibre"));
		arme.setProprietaire(getPersonne(rs.getLong("personnes_idPersonne")));
		return arme;
	}

	private Personne getPersonneFromResultSet(ResultSet rs) throws SQLException {
		Personne personne = new Personne();
		personne.setIdPersonne(rs.getLong("idPersonne"));
		personne.setNom(rs.getString("nom"));
		personne.setPrenom(rs.getString("prenom"));
		personne.setDateNaissance(rs.getDate("DateNaissance"));
		return personne;
	}

	private void logSQL(PreparedStatement pstmt) {
		String sql;
		if (pstmt == null)
			return;
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}

}
