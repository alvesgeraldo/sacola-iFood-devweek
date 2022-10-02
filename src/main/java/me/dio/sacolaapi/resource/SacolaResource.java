package me.dio.sacolaapi.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Sacola;
import me.dio.sacolaapi.resource.dto.ItemDto;
import me.dio.sacolaapi.service.SacolaService;

@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaResource {
  
  private final SacolaService sacolaService;

  @PostMapping
  public Item incluirItemSacola(@RequestBody ItemDto itemDto){
    return sacolaService.incluirItemSacola(itemDto);
  }

  @GetMapping("/{id}")
  public Sacola verrSacola(@PathVariable("id") Long id){
    return sacolaService.verSacola(id);
  }

  @PatchMapping("/fecharSacola/{sacolaId}")
  public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId, @RequestParam("formaPagamento") int formaPagamento){
    return sacolaService.fecharSacola(sacolaId, formaPagamento);
  }
}
