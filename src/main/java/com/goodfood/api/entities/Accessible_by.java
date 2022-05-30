package com.goodfood.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

//@Entity
//Classe à terminer
/**
 * <p>
 *
 * </p>
 * @author Gaëtan T.
 */
public class Accessible_by
{

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Timestamp date;
}
