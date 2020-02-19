package com.lusa.budrio.service;

import com.lusa.budrio.model.Sessione;

public interface SessioneService {

    Sessione login(String email, String password);

    void logout(String token);
}
