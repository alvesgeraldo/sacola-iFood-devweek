package me.dio.sacolaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.sacolaapi.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
  
}
