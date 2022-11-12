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
public class Operation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy=compte)
	private String compte"
	private String type;
	private String montant;
	private String date_operation;
}
