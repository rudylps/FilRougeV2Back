package filrouge.gedesaft.controller;

import java.util.List;

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

import filrouge.gedesaft.model.Affaire;
import filrouge.gedesaft.model.Temoin;
import filrouge.gedesaft.service.TemoinService;

//Classe gérant la liaison entre la base données, le serveur (cette application Spring Boot) et le client (application angular)
@RestController
@RequestMapping("/temoin")
@CrossOrigin(origins= "http://localhost:4200", maxAge= 36000)
public class TemoinController {
	
	// injection d'une instance de la classe TemoinService
		@Autowired
		private TemoinService temoinService;

		// Méthode permettant d'obtenir les données d'un temoin
		// Renvoie une erreur 404 en cas d'échec de la requête
		// Renvoie la réponse 200 en cas de succès de la requête
		/**
		 * @param id
		 * @return
		 */
		@GetMapping(value= "detail/{id}")
		public ResponseEntity<?> getTemoinDetails(@PathVariable Long id) {
			Temoin temoin = null;
			try {
				temoin = temoinService.getTemoinDetail(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(temoin);
		}
		
		@GetMapping(value= "/list")
		public ResponseEntity<?> getAllTemoin() {
			List<Temoin> listTemoins = null;
			try {
				listTemoins = temoinService.getAllTemoins();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(listTemoins);
		}
		
		@PostMapping(value ="/create")
		public ResponseEntity<?> addTemoin (@RequestBody Temoin temoin){
		
			try {
				temoinService.addTemoin(temoin);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
		
		
		@PutMapping(value = "/update/{id}")
		public ResponseEntity<?> updateTemoin(@RequestBody Temoin temoin,@PathVariable Long id) throws Exception {
			Temoin result = null;
			String nomTemoin = temoin.getNom();
			if((nomTemoin == null) || (nomTemoin.isEmpty()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nom du témoin n'est pas renseigné.");
			try {
				result = temoinService.updateTemoin(id, temoin);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		
		
		@DeleteMapping(value = "/delete/{id}")
		public ResponseEntity<?> deleteTemoin(@PathVariable Long id){
			try {
			temoinService.deleteTemoin(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
		@GetMapping(value= "/{id}/temoins")
		public ResponseEntity<?> getAllAffairesOfTemoin(@PathVariable Long id) {
			List<Affaire> listAffaires = null;
			try {
				listAffaires = temoinService.getAllAffairesOfTemoin(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(listAffaires);
		}

}
