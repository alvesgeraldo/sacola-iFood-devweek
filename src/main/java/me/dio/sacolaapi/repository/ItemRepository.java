package me.dio.sacolaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.sacolaapi.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
  
}
