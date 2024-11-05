package com.mx.sistemaApiRestaurante.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mx.sistemaApiRestaurante.model.Mesas;
import com.mx.sistemaApiRestaurante.service.MesasImp;

@RestController
@RequestMapping(path = "MesasWs")
@CrossOrigin

public class MesasWs {

	@Autowired
	MesasImp mesasImp;

	// http://localhost:9000/MesasWs/listar
	@GetMapping(path = "listar")
	public List<Mesas> listar() {
		return mesasImp.listar();
	}

	// http://localhost:9000/MesasWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas mesas) {
		String response = mesasImp.guardar(mesas);

		if (response.equals("idMesaNoExiste")) {
			return new ResponseEntity<>("No se guardo, ese id del Mesero", HttpStatus.OK);
		} else if (response.equals("idMesaExiste")) {
			return new ResponseEntity<>("No se guardó, Mesa ya Existe", HttpStatus.OK);
		} else if (response.equals("numMesaExiste")) {
			return new ResponseEntity<>("No se guardó, numMesa ya existe", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(mesas, HttpStatus.CREATED);
		}

	}

	//http://localhost:9000/MesasWs/buscar
	@PostMapping(path = "buscar")
	public Mesas buscar(@RequestBody Mesas mesas) {
		return mesasImp.buscar(mesas.getId());
	}
	
	//Validar que ir existe el modelo para editar 
	
	//http://localhost:9000/MesasWs/editar
	@PostMapping (path = "editar")
	public ResponseEntity<?> editar (@RequestBody Mesas mesas){
		boolean response = mesasImp.editar(mesas);
		
		if (response == false)
			return new ResponseEntity<>("Ese Registro no existe", HttpStatus.OK);
		else return new ResponseEntity<>(mesas, HttpStatus.OK);
			
	}
	
	//http://localhost:9000/MesasWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Mesas mesas){
		boolean response = mesasImp.eliminar(mesas.getId());
		
		if(response == false)
			return  new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
	}
	
	
}
