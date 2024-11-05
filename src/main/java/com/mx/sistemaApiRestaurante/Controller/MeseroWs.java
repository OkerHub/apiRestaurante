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

import com.mx.sistemaApiRestaurante.model.Mesero;
import com.mx.sistemaApiRestaurante.service.MeseroImp;

@RestController
@RequestMapping(path = "MeseroWs")
@CrossOrigin
public class MeseroWs {
	@Autowired
	MeseroImp meseroImp;

	// http://localhost:9000/MeseroWs/listar
	@GetMapping(path = "listar")
	public List<Mesero> listar() {
		return meseroImp.listar();
	}

	// http://localhost:9000/MeseroWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesero mesero) {
		String response = meseroImp.guardar(mesero);

		if (response.equals("idExiste"))
			return new ResponseEntity<>("Ese id ya existe", HttpStatus.OK);
		else if (response.equals("Nombre Existe"))
			return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(mesero, HttpStatus.CREATED);

	}

	// http://localhost:9000/MeseroWs/buscar
	@PostMapping(path = "buscar")
	public Mesero buscar(@RequestBody Mesero mesero) {
		return meseroImp.buscar(mesero.getId());
	}

	// http://localhost:9000/MeseroWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Mesero mesero) {
		boolean response = meseroImp.editar(mesero);

		if (response == false)
			return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(mesero, HttpStatus.CREATED);
	}

	// http://localhost:9000/MeseroWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Mesero mesero) {
		boolean response = meseroImp.eliminar(mesero.getId());

		if (response == false)
			return new ResponseEntity<>("Ese registro se elimno", HttpStatus.OK);
		else
			return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
	}

}
