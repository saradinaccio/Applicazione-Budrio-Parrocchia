package com.lusa.budrio.service.impl;

import com.lusa.budrio.model.Sessione;
import com.lusa.budrio.model.Sezione;
import com.lusa.budrio.repository.SessioneRepository;
import com.lusa.budrio.repository.SezioneRepository;
import com.lusa.budrio.service.SezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SezioneServiceImpl implements SezioneService {

    @Autowired
    SezioneRepository sezioneRepository;

    @Autowired
    SessioneRepository sessioneRepository;

    @Override
    public List<Sezione> getAllSezioni(String token) {
        Sessione sessione = sessioneRepository.findByToken(token);

        if(sessione != null) {
            return sezioneRepository.findAll(new Sort("nome"));
        }
        return null;
    }
}
