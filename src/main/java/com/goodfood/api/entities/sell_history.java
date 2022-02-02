package com.goodfood.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "sell_history")
//Classe à terminer (vérifier type et relation)
public class sell_history
{
    @Column(name = "sell_history_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sell_history_id;


}
