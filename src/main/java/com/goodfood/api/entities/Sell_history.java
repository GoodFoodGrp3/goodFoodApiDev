package com.goodfood.api.entities;

import javax.persistence.*;

/**
 * <p>
 *  Class qui permet de définir l'entité Sell_history par rapport à la base de données.
 * </p>
 * <p><b>@CrossOrigin</b> pour choisir quel adresse url peux contacter l'api. (ici http://localhost:4200)</p>
 * <p><b>@Entity</b> permet de spécifier que la classe Sell_history est une entité</p>
 * <p><b>@Table</b> permet de nommer la classe comme dans la base de donnée pour faire une liaison.</p>
 * @author Gaëtan T.
 */
@Entity
@Table(name = "sell_history")
public class Sell_history
{
    @Column(name = "sell_history_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sell_history_id;
}
