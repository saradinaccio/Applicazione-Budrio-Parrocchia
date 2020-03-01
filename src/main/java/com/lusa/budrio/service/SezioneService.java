package com.lusa.budrio.service;

import com.lusa.budrio.model.Sezione;

import java.util.List;

public interface SezioneService {

    List<Sezione> getAllSezioni(String token);
}
