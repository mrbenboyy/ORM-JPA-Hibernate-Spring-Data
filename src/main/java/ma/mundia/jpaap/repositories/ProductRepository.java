package ma.mundia.jpaap.repositories;

import jakarta.transaction.Transactional;
import ma.mundia.jpaap.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findProductByPrix(double pr);
    List<Product>findProductByPrixAndQteLessThan(double pr,int qt);

     //@Query("select p from Product p where  p.prix= :pr and p.name= :nom ")
    //List<Product> chercherProduits(@Param("pr") int pr,@Param("nom") String nom);
    @Query("select p from Product p where p.prix = :pr and p.name = :nom")
    List<Product> chercherProduits(@Param("pr") double pr, @Param("nom") String nom);
    @Transactional
    default Product mettreAJourProduit(Product produit) {
        return save(produit);
    }

    @Transactional
    default void supprimerProduit(Long id) {
        deleteById(id);
    }

}
