package ma.mundia.jpaap;

import ma.mundia.jpaap.entities.Product;
import ma.mundia.jpaap.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {

    @Autowired
   private  ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
for (int i=0;i<100;i++){
    productRepository.save(new Product(null,"Hp",12369.87,(int)(Math.random()*100)));
}
        /* productRepository.save(new Product(null,"Hp",12369.87,(int)(Math.random()*100)));
        productRepository.save(new Product(null,"Asus",198369.87,4));
        productRepository.save(new Product(null,"Mac",22369.87,5)); */

        Page <Product> products= (Page<Product>) productRepository.findAll(PageRequest.of(1,5));
        System.out.println("total pages:"+products.getTotalPages());
        System.out.println("total elements:"+products.getTotalElements());
        System.out.println(" num pages :"+products.getNumber());
        List<Product>content =products.getContent();
         List<Product> byPrix=productRepository.findProductByPrix(12369.87);
         List<Product> productList=productRepository.chercherProduits(12369.87,"%h%");


        Product newProduct = new Product(null, "TOSHIBA",   15000.00, 10);
        productRepository.save(newProduct);

        List<Product> productList1 = productRepository.chercherProduits(12369.87, "%h%");
        System.out.println("Products found:");
        productList1.forEach(product -> {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrix());
            System.out.println("Quantity: " + product.getQte());
        });


        newProduct.setName("TOSHIBA");
        newProduct.setPrix(20000.69);
        Product updatedProduct = productRepository.mettreAJourProduit(newProduct);

        System.out.println("Product updated to: " + updatedProduct.getName());

        updatedProduct = productRepository.findById(newProduct.getId()).orElse(null);
        if (updatedProduct != null) {
            System.out.println("Updated Product Details:");
            System.out.println("ID: " + updatedProduct.getId());
            System.out.println("Name: " + updatedProduct.getName());
            System.out.println("Price: " + updatedProduct.getPrix());
            System.out.println("Quantity: " + updatedProduct.getQte());
        }

       productRepository.deleteById(newProduct.getId());
        System.out.println("Product deleted: ID " + newProduct.getId());

        if (!productRepository.findById(newProduct.getId()).isPresent()) {
            System.out.println("Product successfully deleted.");
        }










        byPrix.forEach(e->{
            System.out.println("================");
            System.out.println(e.getId());
            System.out.println(e.getName());
            System.out.println(e.getPrix());
            System.out.println(e.getQte());

        });


        System.out.println("********************");
        Product product=productRepository.findById(1L).orElse(null);
        if (product!=null){
            System.out.println(product.getName());
            System.out.println(product.getPrix());
            System.out.println(product.getQte());
        }
        productRepository.deleteById(1L);
        productRepository.save(product);
    }
}
