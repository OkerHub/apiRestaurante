package com.mx.sistemaApiRestaurante.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.sistemaApiRestaurante.model.Mesero;

public interface MeseroDao extends JpaRepository<Mesero, Long> {

}
