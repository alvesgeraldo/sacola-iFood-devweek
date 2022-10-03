package me.dio.sacolaapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaapi.enumeration.FormaPagamento;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Restaurante;
import me.dio.sacolaapi.model.Sacola;
import me.dio.sacolaapi.repository.ItemRepository;
import me.dio.sacolaapi.repository.ProdutoRepository;
import me.dio.sacolaapi.repository.SacolaRepository;
import me.dio.sacolaapi.resource.dto.ItemDto;
import me.dio.sacolaapi.service.SacolaService;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

  private final SacolaRepository sacolaRepository;
  private final ProdutoRepository produtoRepository;
  private final ItemRepository itemRepository;


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
    
    Sacola sacola = verSacola(itemDto.getIdSacola());

    if(sacola.isFechada()){
      throw new RuntimeException("A sacola está fechada!");
    }

    Item itemParaSerInserido = Item.builder()
        .qtde(itemDto.getQtde())
        .sacola(sacola)
        .produto(produtoRepository.findById(itemDto.getIdProduto()).orElseThrow(
          () -> {
            throw new RuntimeException("Esse produto não existe!");
          }
        ))
        .build();

    List<Item> itens = sacola.getItens();
    if(itens.isEmpty()){
      itens.add(itemParaSerInserido);
    } else {
      Restaurante restauranteAtual = itens.get(0).getProduto().getRestaurante();
      Restaurante restauranteNovoProduto = itemParaSerInserido.getProduto().getRestaurante();
      if(restauranteAtual.equals(restauranteNovoProduto)){
        itens.add(itemParaSerInserido);
      } else {
        throw new RuntimeException("Produtos de restaurante diferentes. Esvazie ou feche a sacola!");
      }
    }

    List<Double> valorDosItens = new ArrayList<>();
    
    for(Item item : itens){
      double valorTotalItem = item.getProduto().getValorUnitario() * item.getQtde();
      valorDosItens.add(valorTotalItem);
    }

    Double valorTotalSacola = 0.0;

    for(Double valorItem : valorDosItens){
      valorTotalSacola += valorItem;
    }

    sacola.setValorTotal(valorTotalSacola);
    sacolaRepository.save(sacola);
    return itemParaSerInserido;
    
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
