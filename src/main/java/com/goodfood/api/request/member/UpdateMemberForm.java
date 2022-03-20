package com.goodfood.api.request.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Timestamp;

public class UpdateMemberForm {

    @NotBlank( message = "Un pseudo ne peut être vide" )
    private String    username;

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris" )
    @Past
    private Timestamp birthDate;

    public UpdateMemberForm() {

    }

    public UpdateMemberForm(String username, Timestamp birthDate ) {
        super();
        this.username = username;
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate( Timestamp birthDate ) {
        this.birthDate = birthDate;
    }

}
