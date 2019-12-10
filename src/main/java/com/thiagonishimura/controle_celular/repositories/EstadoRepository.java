package com.thiagonishimura.controle_celular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagonishimura.controle_celular.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}