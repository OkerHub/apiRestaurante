package com.mx.sistemaApiRestaurante.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * CREATE TABLE MESAS(
ID NUMBER PRIMARY KEY,
NUM_MESA NUMBER NOT NULL,
NUM_SILLAS NUMBER NOT NULL,
ID_MESERO NUMBER NOT NULL,
FOREIGN KEY(ID_MESERO) REFERENCES MESERO(ID)
);

INSERT INTO MESAS VALUES (1,45,6,1);
INSERT INTO MESAS VALUES (2,46,6,1);
COMMIT;

 */

@Entity
@Table(name = "MESAS")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Mesas {

	@Id
	private Long id;
	private Integer num_Mesa;
	private Integer num_Sillas;
		
	//Cardinalidad 
	//Muchos modelos pertenecen a una marca
	//fetch  Indicamos como debe ser cargada la entidad
	//FetchType Indicamos que la relacion va ser cargada al momento
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_MESERO")
	Mesero mesero;
	
}
