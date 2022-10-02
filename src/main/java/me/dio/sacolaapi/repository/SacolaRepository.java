package me.dio.sacolaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.sacolaapi.model.Sacola;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> { 
  
}
