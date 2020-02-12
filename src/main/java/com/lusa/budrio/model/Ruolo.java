package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "ruolo")
public class Ruolo implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "ruolo_privilegio",
            joinColumns = @JoinColumn(
                    name = "ruoloId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilegioId", referencedColumnName = "id"))
    private Set<Privilegio> privilegi;

    @Override
    public String getAuthority() {
        return nome;
    }
}
