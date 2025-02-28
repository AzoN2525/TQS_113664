import org.junit.jupiter.api.Test;

import com.example.ISimpleHttpClient;
import com.example.Product;
import com.example.ProductFinderService;
import com.example.TqsBasicHttpClient;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

class ProductFinderServiceIT {

    @Test
    void testFindProductDetails_RealAPI() throws IOException {
        // Criamos o serviço com o cliente HTTP real
        ISimpleHttpClient httpClient = new TqsBasicHttpClient();
        ProductFinderService service = new ProductFinderService(httpClient);

        // Chamamos a API real para um produto conhecido
        List<Product> result = service.findProductDetails(1);

        // Validamos se o resultado contém um produto
        assertNotNull(result);
        assertFalse(result.isEmpty());

        // Pegamos o primeiro produto retornado
        Product product = result.get(0);

        // O ID deve ser 1 (porque passamos ID=1 na chamada)
        assertEquals(1, product.getId());

        // Garantimos que o título e preço não estão vazios
        assertNotNull(product.getTitle());
        assertNotNull(product.getPrice());
    }


    @Test
    void testFetchRealDataFromAPI() throws IOException {
        String url = "https://fakestoreapi.com/products/1";
        TqsBasicHttpClient client = new TqsBasicHttpClient();
        
        String response = client.doHttpGet(url);
        System.out.println("🔹 Teste: Resposta recebida -> " + response);

        assertNotNull(response);
        assertTrue(response.contains("\"id\":1"));
    }

}
