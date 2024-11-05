package com.mx.sistemaApiRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaApiRestaurante.dao.MeseroDao;
import com.mx.sistemaApiRestaurante.model.Mesero;



@Service
public class MeseroImp {

	@Autowired 
	MeseroDao meseroDao;
	
	@Transactional(readOnly = true)
	public List<Mesero> listar(){
		return meseroDao.findAll();
	}
	
	//Al guardar validar que el id y nombre no se repitan 
		@Transactional
		public String guardar(Mesero mesero) {
			//ciclo 
			//condicicion
			//bandera
			
			String respuesta = "";
			boolean bandera = false;
			
			for (Mesero m : meseroDao.findAll()) {
				if (m.getId().equals(mesero.getId())) {
					bandera = true;
					respuesta = "idExiste";
					break;
				} else if (m.getNombre().equals(mesero.getNombre())) {
					bandera = true;
					respuesta = "nombreExiste";
					break;
				}
				
				
			}
			
			if (bandera == false) {
				meseroDao.save(mesero);
				respuesta = "guardado";
			}

			return respuesta;
			
			
		}
	
	//Validar que id no exista para editar
		@Transactional(readOnly = true)
		public Mesero buscar(Long id) {
			return meseroDao.findById(id).orElse(null);
		}
	
		//Validar que el idExistente para editar
		@Transactional
		public boolean editar(Mesero mesero) {
			
			boolean bandera = false;
			
			for(Mesero m: meseroDao.findAll()) {
				if(m.getId().equals(mesero.getId())) {
					meseroDao.save(mesero);
					bandera = true;
					break;
				}
			}
			return bandera;
		}
		
		//Validar que el id exista para eliminar
		@Transactional
		public boolean eliminar(Long id) {
			boolean bandera = false;
			for (Mesero m : meseroDao.findAll()) {
				if(m.getId().equals(id)) {
					meseroDao.deleteById(id);
					
					bandera = true;
					break;
				}
			}
			return bandera;
		}
	
		
}
