package filrouge.gedesaft.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import filrouge.gedesaft.model.Affaire;
import filrouge.gedesaft.service.AffaireService;

	
	// Classe gérant la liaison entre la base données, le serveur (cette application Spring Boot) et le client (application angular)
	@RestController
	@RequestMapping("/affaire")
	@CrossOrigin(origins= "http://localhost:4200", maxAge= 36000)
	public class AffaireController {
		
		// injection d'une instance de la classe AffaireService
		@Autowired
		private AffaireService affaireService;

		//Retourne Une affaire selon l'Id
		@GetMapping(value="/detail/{id}")
		public ResponseEntity<?> getAffaireDetail(@PathVariable Long id) {
			Affaire affaire = null;
			try {
				affaire = affaireService.getAffaireDetail(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(affaire);
		}
		
		//Liste des affaires
		@GetMapping(value= "/list")
		public ResponseEntity<?> getAllAffaires() {
			List<Affaire> listAffaires = null;
			try {
				listAffaires = affaireService.getAllAffaires();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(listAffaires);
		}
		
		//Création d'une affaire
		@PostMapping(value ="/create")
		public ResponseEntity<?> addVehicule (@RequestBody Affaire affaire){
			String dossier = affaire.getDossier();
			if((dossier == null) || (dossier.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du dossier n'est pas défini!");		
			try {
			affaireService.addAffaire(affaire);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}
		
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateAffaire(@RequestBody Affaire affaire,@PathVariable Long id) throws Exception {
			Affaire result = null;
			String dossier = affaire.getDossier();
			if((dossier == null) || (dossier.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le dossier n'est pas spécifié !");
			
			String lieu = affaire.getLieu();
			if((lieu == null) || (lieu.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le lieu n'est pas spécifié !");
			
			Date dateOuverture = affaire.getDateOuverture();
			if((dateOuverture == null))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la date n'est pas spécifié !");
			
			try {
				result = affaireService.updateAffaire(id,affaire);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		
				//Supprime Une affaire selon l'Id
				@DeleteMapping(value="/delete/{id}")
				public void deleteAffaire(@PathVariable Long id) throws Exception {	
					affaireService.deleteAffaire(id);
				}

}
