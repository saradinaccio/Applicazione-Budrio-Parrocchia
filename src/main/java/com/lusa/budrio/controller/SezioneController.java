package com.lusa.budrio.controller;

import com.lusa.budrio.model.Risposta;
import com.lusa.budrio.model.Sezione;
import com.lusa.budrio.service.SezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SezioneController {

    @Autowired
    SezioneService sezioneService;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping("/{token}/sezione")
    public Risposta getAllSezioni(@PathVariable String token) {
        Risposta<List<Sezione>> risposta = new Risposta<>();
        List<Sezione> sezioni = sezioneService.getAllSezioni(token);

        if(sezioni != null) {
            risposta.setRisultato(true);
            risposta.setMessaggio("Sezione recuperate con successo!");
            risposta.setData(sezioni);
        }
        else {
            risposta.setRisultato(false);
            risposta.setMessaggio("Recupero sezioni fallito! Non risulti essere loggato.");
        }
        return risposta;
    }
}
