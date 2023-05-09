package br.com.fiap.Calendario2.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Collection;
import java.util.List;

import br.com.fiap.Calendario2.controllers.ContaController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conta implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank @Size(min = 3, max = 255)
    private String usuario; 

    @Email(message = "email inválido")
    private String email; 

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$", message = "a senha deve conter no mínimo 8 caracteres, 1 letra maiúscula, 1 minúscula e 1 dos caracteres especiais $, *, &, @ ou #") 
    private String senha;

    @JsonIgnore
    private boolean ativa = true; 

    public EntityModel<Conta> toEntityModel(){
        
        return EntityModel.of(
            this,
            linkTo(methodOn(ContaController.class).show(id)).withSelfRel(), 
            linkTo(methodOn(ContaController.class).delete(id)).withRel("delete"), 
            linkTo(methodOn(ContaController.class).index(null, Pageable.unpaged())).withRel("all") 
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()-> "ROLE_CONTA");
    }

    @Override
    public String getPassword() {
        return senha; 
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
