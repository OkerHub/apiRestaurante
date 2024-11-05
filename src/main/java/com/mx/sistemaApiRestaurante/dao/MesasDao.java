package com.mx.sistemaApiRestaurante.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.sistemaApiRestaurante.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long> {

}
