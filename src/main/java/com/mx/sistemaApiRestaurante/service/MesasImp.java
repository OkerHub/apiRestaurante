package com.mx.sistemaApiRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaApiRestaurante.dao.MesasDao;
import com.mx.sistemaApiRestaurante.dao.MeseroDao;
import com.mx.sistemaApiRestaurante.model.Mesas;

@Service

public class MesasImp {

	@Autowired
	MeseroDao meserosDao;

	@Autowired
	MesasDao mesasDao;

	@Transactional(readOnly = true)
	public List<Mesas> listar() {
		return mesasDao.findAll();
	}

	// Validaciones
	@Transactional
	public String guardar(Mesas mesas) {
		String respuesta = "";
		boolean banderaMar = false;
		boolean banderaMod = false;

		// Validar que el mesero asignado a la mesa exista
		if (meserosDao.existsById(mesas.getMesero().getId())) {
			banderaMar = true;
		}

		// Validar que no exista otra mesa con el mismo ID o número de mesa
		for (Mesas mod : mesasDao.findAll()) {
			if (mod.getId().equals(mesas.getId())) {
				respuesta = "idMesaExiste";
				banderaMod = true;
				break;
			} else if (mod.getNum_Mesa().equals(mesas.getNum_Mesa())) { // Ahora puedes usar equals() para Integer
				respuesta = "numMeseroExiste";
				banderaMod = true;
				break;
			}
		}

		// idMesero no existe
		if (!banderaMar) {
			respuesta = "idMeseroNoExiste";
		} else if (!banderaMod) {
			mesasDao.save(mesas);
			respuesta = "guardado";
		}
		return respuesta;
	}

	@Transactional(readOnly = true)
	public Mesas buscar(Long id) {
		return mesasDao.findById(id).orElse(null);
	}
	
	//Validar que id existe modelo para editar
	@Transactional
	public boolean editar(Mesas mesas) {
		
		boolean bandera = false;
		
		for(Mesas m: mesasDao.findAll()) {
			if(m.getId().equals(mesas.getId())) {
				mesasDao.save(mesas);
				bandera = true;
				break;
			}
		}
		
		return bandera;
	}
	
	//Que el id exista
	@Transactional
	public boolean eliminar(Long id) {
		boolean bandera = false;
		for(Mesas m: mesasDao.findAll()) {
			if(m.getId().equals(id)) {
				mesasDao.deleteById(id);
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
}
