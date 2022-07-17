package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.entities.Entreprise;

import java.util.List;

/**
 * @author LEONARD
 */
public interface ParticuliersDao {
	
	Entreprise getEntrepriseByNom(String pRaisonSociale);
	
	List<Entreprise> getEntrepriseByProfilId(Integer pId);
}
