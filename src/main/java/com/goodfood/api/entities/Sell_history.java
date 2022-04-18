package com.goodfood.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "sell_history")
public class Sell_history
{
    @Column(name = "sell_history_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sell_history_id;
}
