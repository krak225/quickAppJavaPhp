package net.krak.quickapp.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Transient
	@Column(nullable=false)
	private Instant date_creation = Instant.now();

	@Transient
	@Column(nullable=false)
	private Instant date_modification = Instant.now();

	private String nom;

	private int salaire;


	@ColumnDefault(value = "\'DESACTIVE'")
	@Column(columnDefinition = "ENUM('ACTIVE', 'DESACTIVE', 'SUPPRIME')")
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIVE;

	private String telephone;

}
