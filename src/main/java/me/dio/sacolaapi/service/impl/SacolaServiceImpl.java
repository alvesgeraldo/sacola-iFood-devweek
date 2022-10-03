package me.dio.sacolaapi.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaapi.enumeration.FormaPagamento;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Sacola;
import me.dio.sacolaapi.repository.SacolaRepository;
import me.dio.sacolaapi.resource.dto.ItemDto;
import me.dio.sacolaapi.service.SacolaService;

@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

  private final SacolaRepository sacolaRepository;

  @Override
  public Sacola fecharSacola(Long id, int numFormaPagamento) {
    
    Sacola sacola = verSacola(id);

    if(sacola.getItens().isEmpty()){
      throw new RuntimeException("Inclua itens na sacola!");
    }

    FormaPagamento formaPagamento = numFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

    sacola.setFormaPagamento(formaPagamento);
    sacola.setFechada(true);

    return sacolaRepository.save(sacola);

  }

  @Override
  public Item incluirItemSacola(ItemDto itemDto) {
    return null;
  }

  @Override
  public Sacola verSacola(Long id) {
    
    return sacolaRepository.findById(id).orElseThrow(
      () -> {
        throw new RuntimeException("Essa sacola não existe!");
      }
    );

  }
  
}
