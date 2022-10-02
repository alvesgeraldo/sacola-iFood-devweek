package me.dio.sacolaapi.service;

import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Sacola;
import me.dio.sacolaapi.resource.dto.ItemDto;

public interface SacolaService {
  
  Item incluirItemSacola(ItemDto itemDto); 
  Sacola verSacola(Long id);
  Sacola fecharSacola(Long id, int formaPagamento);
  
}
