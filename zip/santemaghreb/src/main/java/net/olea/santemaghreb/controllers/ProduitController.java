package net.olea.santemaghreb.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.olea.santemaghreb.entities.Produit;
import net.olea.santemaghreb.services.ProduitService;

@RestController
@RequestMapping(path="/api/")
public class ProduitController {

public ProduitService produitService;

public ProduitController(ProduitService produitService) {
	this.produitService = produitService;
}

@GetMapping(path="/produits")
public List<Produit> getProduits(){
	return produitService.getProduits();
}

@GetMapping(path="/produit/{id}")
public Optional<Produit> getProduit(@PathVariable Long id){
	return produitService.getProduit(id);
}

@PostMapping(path="/produit")
public Produit addProduit(@RequestBody Produit produit){
	return produitService.addProduit(produit);
}

@PutMapping(path="/produit/{id}")
public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produit){
	return produitService.updateProduit(id, produit);
}

@DeleteMapping(path="/produit/{id}")
public void deleteProduit(@PathVariable Long id){
	produitService.deleteProduit(id);
}

}
