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
public class Type_remouvellement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String designation;
	private Date date_creation;
	private Date date_modification;

	@ColumnDefault(value = "\'ACTIVE'")
	@Column(columnDefinition = "ENUM('ACTIVE', 'DESACTIVE', 'SUPPRIME')")
	@Enumerated(EnumType.STRING)
	private Statut statut = Statut.ACTIVE;
}
