package com.lusa.budrio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class Password implements Serializable {

    private String vecchiaPassword;

    private String nuovaPassword;
}
