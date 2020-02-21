package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "ruolo")
public class Ruolo implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "ruoli")
    private List<Utente> utenti;

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

