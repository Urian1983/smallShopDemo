package com.example.eCommerceDemo.dataLoader;

import com.example.eCommerceDemo.model.Product;
import com.example.eCommerceDemo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {
    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() > 0) {
            return;
        }

        List<Product> products = List.of(

                createProduct("M001", "Paella de Pollo", "Mediterráneo Español",
                        "Paella tradicional con pollo y verduras frescas de temporada",
                        "Paella clásica mediterránea", "Arroces", 15, 17.90,
                        "/images/products/paella-con-pollo.jpg"),

                createProduct("M002", "Arroz con Verduras", "Mediterráneo Español",
                        "Arroz meloso con verduras de temporada y aceite de oliva virgen extra",
                        "Arroz vegetal saludable", "Arroces", 18, 14.50,
                        "/images/products/arroz-verduras.jpg"),

                createProduct("M003", "Risotto de Setas", "Cocina Italiana",
                        "Risotto cremoso de setas y aceite de oliva",
                        "Risotto suave y aromático", "Arroces", 14, 16.50,
                        "/images/products/risotto-setas.jpg"),

                createProduct("M004", "Cuscús de Verduras", "Cocina Marroquí",
                        "Cuscús tradicional con verduras especiadas y hierbas frescas",
                        "Cuscús aromático del Magreb", "Arroces", 16, 15.00,
                        "/images/products/Cuscus-Verduras.jpg"),

                createProduct("M005", "Ensalada Griega", "Cocina Helénica",
                        "Tomate, pepino, aceitunas y queso feta con aceite de oliva",
                        "Ensalada fresca mediterránea", "Ensaladas", 20, 11.00,
                        "/images/products/Ensalada-Griega.jpg"),

                createProduct("M006", "Ensalada Marroquí", "Cocina Marroquí",
                        "Tomate, pepino, cilantro y limón recién exprimido",
                        "Refrescante ensalada del Magreb", "Ensaladas", 18, 10.50,
                        "/images/products/Ensalada-Marroquí.jpg"),

                createProduct("M007", "Ensalada Caprese", "Cocina Italiana",
                        "Mozzarella fresca, tomate y albahaca con aceite de oliva",
                        "Clásica ensalada italiana", "Ensaladas", 15, 11.90,
                        "/images/products/Ensalada-Caprese.jpg"),

                createProduct("M008", "Pollo Asado al Limón", "Cocina Española",
                        "Pollo asado con limón, ajo y hierbas mediterráneas",
                        "Pollo jugoso y aromático", "Carnes", 12, 16.90,
                        "/images/products/pollo-asado-limon.jpg"),

                createProduct("M009", "Kebab de Cordero", "Cocina Griega",
                        "Cordero especiado a la parrilla con hierbas frescas",
                        "Kebab tradicional a la brasa", "Carnes", 10, 18.50,
                        "/images/products/kebab-cordero.jpg"),

                createProduct("M010", "Albóndigas en Salsa", "Cocina Española",
                        "Albóndigas de ternera en salsa de tomate casera",
                        "Albóndigas tradicionales", "Carnes", 16, 15.50,
                        "/images/products/albondigas-salsa.jpg"),

                createProduct("M011", "Dorada al Horno", "Mediterráneo",
                        "Dorada fresca al horno con limón y aceite de oliva",
                        "Dorada mediterránea clásica", "Pescados", 10, 21.90,
                        "/images/products/Dorada-horno.jpg"),

                createProduct("M012", "Salmón a la Plancha", "Cocina Mediterránea",
                        "Salmón fresco a la plancha con hierbas aromáticas",
                        "Salmón jugoso y saludable", "Pescados", 12, 20.90,
                        "/images/products/salmon-plancha.jpg"),

                createProduct("M013", "Bacalao al Horno", "Cocina Española",
                        "Lomo de bacalao al horno con tomate y pimientos",
                        "Bacalao tradicional al horno", "Pescados", 10, 19.90,
                        "/images/products/Bacalao-Horno.jpg"),

                createProduct("M014", "Pizza Margherita", "Cocina Italiana",
                        "Pizza napolitana con mozzarella y albahaca fresca",
                        "La pizza más clásica", "Pizzas", 20, 12.90,
                        "/images/products/Pizza-Margherita.jpg"),

                createProduct("M015", "Pizza de Verduras", "Cocina Italiana",
                        "Pizza con pimientos, cebolla, champiñones y aceitunas",
                        "Pizza vegetariana mediterránea", "Pizzas", 18, 13.90,
                        "/images/products/Pizza-Verduras.jpg"),

                createProduct("M016", "Lasaña de Verduras", "Cocina Italiana",
                        "Lasaña con verduras asadas y bechamel cremosa",
                        "Lasaña vegetal italiana", "Pastas", 14, 14.90,
                        "/images/products/Lasaña-Verduras.jpg"),

                createProduct("M017", "Pasta al Pomodoro", "Cocina Italiana",
                        "Espaguetis con salsa de tomate fresco y albahaca",
                        "Pasta sencilla y deliciosa", "Pastas", 20, 12.50,
                        "/images/products/Pasta-pomodoro.jpg"),

                createProduct("M018", "Ratatouille", "Cocina Francesa",
                        "Verduras guisadas lentamente con hierbas provenzales",
                        "Guiso vegetal mediterráneo", "Verduras", 16, 12.90,
                        "/images/products/Ratatouille.jpg")
        );

        productRepository.saveAll(products);
    }

    private Product createProduct(String sku, String name, String brand, String description,
                                  String shortDescription, String category, int stock,
                                  double price, String imageUrl) {
        Product product = new Product();
        product.setSku(sku);
        product.setName(name);
        product.setSlug(
                java.text.Normalizer.normalize(name, java.text.Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                        .toLowerCase()
                        .replaceAll("[^a-z0-9]+", "-")
                        .replaceAll("(^-|-$)", "")
        );
        product.setBrand(brand);
        product.setDescription(description);
        product.setShortDescription(shortDescription);
        product.setCategory(category);
        product.setStock(stock);
        product.setPrice(BigDecimal.valueOf(price));
        product.setImageUrl(imageUrl);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}

