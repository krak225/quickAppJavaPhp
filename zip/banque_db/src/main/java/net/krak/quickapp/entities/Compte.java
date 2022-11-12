package net.krak.quickapp.entities;

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenoms;
	private String sexe;
	private String telephone;
	private String photo;
	private String date_naissance;
	private String solde;
	private String numero_compte;
	private String date_creation;
}
