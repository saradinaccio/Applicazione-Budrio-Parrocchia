package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "privilegio")
public class Privilegio implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "privilegi")
    private List<Ruolo> ruoli;

    @Override
    public String getAuthority() {
        return nome;
    }
}
