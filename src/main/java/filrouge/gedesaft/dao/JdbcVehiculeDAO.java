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
import filrouge.gedesaft.model.Personne;
import filrouge.gedesaft.model.Vehicule;

@Repository
public class JdbcVehiculeDAO implements VehiculeDAO {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource datasource;

	@Autowired
	public JdbcVehiculeDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	// Obtenir la liste des véhicules dans la base de données
	/* (non-Javadoc)
	 * @see filrouge.gedesaft.dao.VehiculeDAO#getListVehicules()
	 */
	@Override
	public List<Vehicule> getListVehicules() throws Exception {
		Connection con = datasource.getConnection();
		Vehicule vehicule;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Vehicule> aLlistOfVehicule = new ArrayList<Vehicule>();
		try {
			sql = "SELECT * FROM vehicules ";
			pstmt = con.prepareStatement(sql);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vehicule = getVehiculeFromResultSet(rs);
				aLlistOfVehicule.add(vehicule);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
			con.close();
		}
		return aLlistOfVehicule;
	}

	// Chercher un véhicule dans la base de données
	/* (non-Javadoc)
	 * @see filrouge.gedesaft.dao.VehiculeDAO#getVehicule(java.lang.Long)
	 */
	@Override
	public Vehicule getVehicule (Long id) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		Vehicule vehicule = null;
		try {
			String sql = "SELECT * FROM vehicules WHERE id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				vehicule = getVehiculeFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
			con.close();
		}
		return vehicule;
	}
	
	// Chercher une Personne - propriétaire d'un véhicule - dans la base de données
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private Personne getPersonne (Long id) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		Personne personne = null;
		if (id == null) {
			return personne;
		} else {
			try {
				String sql = "SELECT * FROM personnes WHERE idPersonne= ?";
				pstmt = con.prepareStatement(sql);
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
				con.close();
			}
			return personne;
		}
	}

	// Ajouter un véhicule dans la base de données
	/* (non-Javadoc)
	 * @see filrouge.gedesaft.dao.VehiculeDAO#insertVehicule(filrouge.gedesaft.model.Vehicule)
	 */
	@Override
	public Vehicule insertVehicule(Vehicule vehicule) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		Vehicule result = null;
		int i = 0;
		vehicule.setId(new Long(0));
		try {
			String sql = "INSERT INTO vehicules (id, type, marque, modele, immatriculation, couleur) VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setLong(++i, vehicule.getId());
			pstmt.setString(++i, vehicule.getType());
			pstmt.setString(++i, vehicule.getMarque());
			pstmt.setString(++i, vehicule.getModele());
			pstmt.setString(++i, vehicule.getImmatriculation());
			pstmt.setString(++i, vehicule.getCouleur());		
			logSQL(pstmt);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				vehicule.setId(rs.getLong(1));		
				result = vehicule;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
			con.close();
		}
		return result;
	}

	
	// Mettre à jour un véhicule dans la base de données
	/* (non-Javadoc)
	 * @see filrouge.gedesaft.dao.VehiculeDAO#updateVehicule(filrouge.gedesaft.model.Vehicule)
	 */
	@Override
	public Vehicule updateVehicule(Vehicule vehicule) throws Exception {
		Connection con = datasource.getConnection();
		Vehicule result = null;
		PreparedStatement pstmt = null;
		int i = 0;				
		try {
			String sql = "UPDATE vehicules SET type= ?, marque= ?, modele= ?, immatriculation= ?, couleur=?, personnes_idPersonne= ? WHERE id = ?";
			pstmt = con.prepareStatement(sql);		
			pstmt.setString(++i, vehicule.getType());
			pstmt.setString(++i, vehicule.getMarque());
			pstmt.setString(++i, vehicule.getModele());
			pstmt.setString(++i, vehicule.getImmatriculation());
			pstmt.setString(++i, vehicule.getCouleur());
			pstmt.setLong(++i, vehicule.getProprietaire().getIdPersonne());
			pstmt.setLong(++i, vehicule.getId());
			logSQL(pstmt);
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("vehicule not found !");		
			result = vehicule;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
			con.close();
		}
		return result;
	}

	// Supprimer un véhicule dans la base de données
	/* (non-Javadoc)
	 * @see filrouge.gedesaft.dao.VehiculeDAO#deleteVehicule(java.lang.Long)
	 */
	@Override
	public void deleteVehicule(Long id) throws Exception {
		Connection con = datasource.getConnection();
		con.setAutoCommit(false);
		PreparedStatement delVehicule = null;
		PreparedStatement delVehiculeImplique = null;
		try {
			String sql_vi = "DELETE FROM vehiculesImpliques WHERE vehicules_id = ?";
			String sql_v = "DELETE FROM vehicules WHERE id = ?";
			delVehiculeImplique = con.prepareStatement(sql_vi);
			delVehiculeImplique.setLong(1, id);
			delVehicule = con.prepareStatement(sql_v);
			delVehicule.setLong(1, id);
			logSQL(delVehiculeImplique);
			logSQL(delVehicule);		
			int result_vi = delVehiculeImplique.executeUpdate();			
			int result_v = delVehicule.executeUpdate();
			con.commit();
			if(result_vi != 1)
				throw new Exception("Vehicule Implique not found !");
			if(result_v != 1)
				throw new Exception("Vehicule not found !");		
		} catch (SQLException e) {
			e.printStackTrace();
			if (con != null) {
				try {
					System.err.print("transaction is being rolled back");
					con.rollback();
				} catch (SQLException excep){
					log.error("SQL transaction Error !:" + delVehiculeImplique.toString()
						+ " and " + delVehicule.toString(), e);
				}
			}
			throw e;
		} finally {
			if (delVehiculeImplique != null) {
				delVehiculeImplique.close();
			}
			if (delVehicule != null) {
				delVehicule.close();
			}
			con.setAutoCommit(true);
			con.close();
		}
	}
	
	// Chercher les affaires dans lesquelles un véhicule est impliqué dans la base de données
	/* (non-Javadoc)
	 * @see filrouge.gedesaft.dao.VehiculeDAO#getAffairesOfVehicule(java.lang.Long)
	 */
	@Override
	public List<Affaire> getAffairesOfVehicule(Long id) throws Exception {
		Connection con = datasource.getConnection();
		Affaire affaire;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Affaire> aLlistOfAffaire = new ArrayList<Affaire>();
		try {
			sql = "SELECT * FROM affaires"
				+ " JOIN vehiculesimpliques ON affaires.id = vehiculesImpliques.affaires_id"
				+ " JOIN vehicules ON vehicules_id = vehicules.id"
				+ " WHERE vehicules.id = ?";
			pstmt = con.prepareStatement(sql);
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
			con.close();
		}
		return aLlistOfAffaire;
	}

	// Instancier un Vehicule à partir d'un ResultSet
	/**
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	private Vehicule getVehiculeFromResultSet(ResultSet rs) throws Exception {
		Vehicule vehicule = new Vehicule();
		vehicule.setId(rs.getLong("id"));
		vehicule.setType(rs.getString("type"));
		vehicule.setMarque(rs.getString("marque"));
		vehicule.setModele(rs.getString("modele"));
		vehicule.setCouleur(rs.getString("couleur"));
		vehicule.setImmatriculation(rs.getString("immatriculation"));
		vehicule.setProprietaire(getPersonne(rs.getLong("personnes_idPersonne")));		
		return vehicule;
	}
	
	// Instancier une personne à partir d'un ResultSet	
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Personne getPersonneFromResultSet(ResultSet rs) throws SQLException {
		Personne personne = new Personne();
		personne.setIdPersonne(rs.getLong("idPersonne"));
		personne.setNom(rs.getString("nom"));
		personne.setPrenom(rs.getString("prenom"));
		personne.setDateNaissance(rs.getDate("DateNaissance"));
		return personne;
	}
	
	// Instancier une Afffaire à partir d'un ResultSet
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Affaire getAffaireFromResultSet(ResultSet rs) throws SQLException {
		Affaire affaire = new Affaire();
		affaire.setId(rs.getLong("id"));
		affaire.setDossier(rs.getString("dossier"));
		affaire.setLieu(rs.getString("lieu"));
		affaire.setDateOuverture(rs.getDate("DateOuverture"));
		return affaire;
	}

	// Inscription des requêtes dans le fichier log
	/**
	 * @param pstmt
	 */
	private void logSQL(PreparedStatement pstmt) {
		String sql;		
		if (pstmt == null)
			return;		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}

}
