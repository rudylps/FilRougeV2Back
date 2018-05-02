package filrouge.gedesaft.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import filrouge.gedesaft.model.Arme;
import filrouge.gedesaft.service.ArmeService;

@CrossOrigin
// Classe gérant la liaison entre la base données, le serveur (cette application
// Spring Boot) et le client (application angular)
@RestController
@RequestMapping("/arme")
public class ArmeController {

	// injection d'une instance de la classe VehiculeService
	@Autowired
	private ArmeService armeService;

	// Méthode permettant d'obtenir les données d'une arme
	// Renvoie une erreur 404 en cas d'échec de la requête
	// Renvoie la réponse 200 en cas de succès de la requête
	/**
	 * @param id
	 * @return une arme
	 * @throws Exception
	 */
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<?> getArme(@PathVariable Long id) {
		Arme arme = null;
		try {
			arme = armeService.getArme(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(arme);
	}

	/**
	 * @param id
	 * @return liste d'armes
	 * @throws Exception
	 */
	@GetMapping(value = "/list")
	public ResponseEntity<?> getListArmes() {
		List<Arme> listArmes = null;
		try {
			listArmes = armeService.getListArmes();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listArmes);
	}

	/**
	 * @return ajouter une arme
	 * @throws Exception
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<?> insertArme(@RequestBody Arme arme) {
		String typeArme = arme.getType();
		if ((typeArme == null) || (typeArme.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type d'arme n'est pas défini!");
		try {
			armeService.insertArme(arme);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	/**
	 * @param id
	 * @return modifier une arme
	 * @throws Exception
	 */
//	@PutMapping(value = "/update/{id}")
//	public ResponseEntity<?> updateArme(@RequestBody Arme arme, @PathVariable Long id) throws Exception {
//		Arme armeToUpdate = armeService.getArme(id);
//		Arme result = null;
//		// le type d'arme DOIT être renseigné
//		String type = arme.getType();
//		if ((type == null) || (type.isEmpty()))
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le type d'arme n'est pas renseigné!");
//		try {
//			System.out.println("avant");
//			result = armeService.updateArme(armeToUpdate.getId(), arme);
//			System.out.println("apres");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Arme> updateArme(@PathVariable(value = "id") long id,
			@Valid @RequestBody Arme arme) throws Exception {
		System.out.println("id : " + id);
		Arme armeToUpdate = armeService.getArme(id);
		System.out.println("arme récupérée :" + armeToUpdate);
		if (armeToUpdate == null) { 
			System.out.println("arme nulle"); 			
			return ResponseEntity.notFound().build();
	}
		// Update all other not null attributes

		if (arme.getType() != null)
			armeToUpdate.setType(arme.getType());
		if (arme.getNoSerie() != null)
			armeToUpdate.setNoSerie(arme.getNoSerie());
		if (arme.getModele() != null)
			armeToUpdate.setModele(arme.getModele());
		if (arme.getCalibre() != null)
			armeToUpdate.setCalibre(arme.getCalibre());
		if (arme.getProprietaire() != null)
			armeToUpdate.setProprietaire(arme.getProprietaire());
		System.out.println("avant updateArme()");
		Arme updatedArme = armeService.saveArme(armeToUpdate);
		System.out.println("apres updateArme()");
		return ResponseEntity.ok(updatedArme);
	}

	/**
	 * @param id
	 * @return supprimer une arme
	 * @throws Exception
	 */
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteArme(@PathVariable Long id) {
		try {
			armeService.deleteArme(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
