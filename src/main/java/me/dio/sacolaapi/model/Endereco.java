package me.dio.sacolaapi.model;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@JsonIgnoreProperties({"hibernateLasyInitializer", "handler"})
@NoArgsConstructor

public class Endereco {
  
  private String cep;
  private String complemento;

}
