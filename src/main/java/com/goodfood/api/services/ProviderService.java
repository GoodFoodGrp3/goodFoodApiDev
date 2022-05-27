package com.goodfood.api.services;

import com.goodfood.api.entities.Provider;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
import com.goodfood.api.exceptions.providers.ProviderNotFoundException;

import java.util.List;

/**
 * <p>
 *  Class qui représente le service ProviderService.
 * </p>
 * @author Gaëtan T.
 */
public interface ProviderService
{

    /**
     * <p><b>Méthode</b> qui permet de get la liste de tous les fournisseurs.
     *
     * </p>
     * @exception ProviderNotFoundException si fournisseur non trouvé.
     */
    List<Provider> getAllProviders() throws ProviderNotFoundException;

    /**
     * <p><b>Méthode</b> qui permet de get un fournisseur par son id.
     *
     * </p>
     * @exception ProviderNotFoundException si fournisseur non trouvé.
     * @param id du fournisseur
     */
    Provider getProviderById (int id) throws ProviderNotFoundException;
    //void deleteProviderById( int id );

    /**
     * <p><b>Méthode</b> qui permet de mettre à jour un fournisseur.
     *
     * </p>
     * @param id du fournisseur.
     * @param provider_name du fournisseur.
     * @param addressline du fournisseur.
     * @param email du fournisseur.
     * @param phone du fournisseur.
     * @param country du fournisseur.
     * @param postal_code du fournisseur.
     * @param state du fournisseur.
     */
    Provider updateProvider( int id, String provider_name, String addressline, String email, String phone,
                             String country, String postal_code, String state );

    /**
     * <p><b>Méthode</b> qui permet de créer un fournisseur.
     *
     * </p>
     * @param id du fournisseur.
     * @param provider_name du fournisseur.
     * @param addressline du fournisseur.
     * @param email du fournisseur.
     * @param phone du fournisseur.
     * @param country du fournisseur.
     * @param postal_code du fournisseur.
     * @param state du fournisseur.
     */
    Provider createProviders(int id, String provider_name, String addressline, String email , String phone,
                             String country, String postal_code, String state );
}
