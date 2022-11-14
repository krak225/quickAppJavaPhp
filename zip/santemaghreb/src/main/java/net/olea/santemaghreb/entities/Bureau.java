package net.olea.santemaghreb.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bureau {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String nom;
	private String numero_rcs;
	private String zone;
	private String logo;
	private String telephone;
	private String fax;
	private String email;
	private String adresse_postale;
	private String situation_geographique;
	private String whatsapp;
	@OneToMany(mappedBy="devise")
	private String devise;
	private String code_postal;
	private String mentions_legales;
	private String ville;
	@OneToMany(mappedBy="pays")
	private String pays;
	private Date date_creation;
	private Date date_modification;

	@ColumnDefault(value = "\'ACTIVE'")
	@Column(columnDefinition = "ENUM('ACTIVE', 'DESACTIVE', 'SUPPRIME')")
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIVE;
}
