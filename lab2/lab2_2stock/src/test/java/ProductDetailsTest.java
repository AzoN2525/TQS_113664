import com.example.ISimpleHttpClient;
import com.example.Product;
import com.example.ProductFinderService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductFinderServiceTest {

    @Mock
    private ISimpleHttpClient httpClient; // Mock do cliente HTTP

    @InjectMocks
    private ProductFinderService service; // Injeta o mock no serviço

    @Test
    void testFindProductDetails_ValidId() {
        // Simula uma resposta JSON da API
        String fakeJsonResponse = """
        {
            "id": 3,
            "title": "Mens Cotton Jacket",
            "price": 55.99,
            "description": "A great jacket for all weather.",
            "category": "men's clothing",
            "image": "https://example.com/image.jpg"
        }
        """;

        // Configura o mock para retornar essa resposta quando chamado
        when(httpClient.doHttpGet("https://fakestoreapi.com/products/3")).thenReturn(fakeJsonResponse);

        // Executa o método a ser testado
        List<Product> result = service.findProductDetails(3);

        // Verifica se o resultado não é nulo e tem 1 produto
        assertNotNull(result);
        assertEquals(1, result.size());

        // Verifica os valores do produto retornado
        Product product = result.get(0);
        assertEquals(3, product.getId());
        assertEquals("Mens Cotton Jacket", product.getTitle());
        assertEquals(55.99, product.getPrice());
        assertEquals("A great jacket for all weather.", product.getDescription());
        assertEquals("men's clothing", product.getCategory());
        assertEquals("https://example.com/image.jpg", product.getImage());

        // Verifica se o mock foi chamado corretamente
        verify(httpClient, times(1)).doHttpGet("https://fakestoreapi.com/products/3");
    }

    @Test
    void testFindProductDetails_InvalidId() {
        // Simula que a API retorna uma string vazia para um ID inexistente
        when(httpClient.doHttpGet("https://fakestoreapi.com/products/300")).thenReturn("");

        // Executa o método para um ID inválido
        List<Product> result = service.findProductDetails(300);

        // Esperamos uma lista vazia como retorno
        assertNotNull(result);
        assertEquals(0, result.size());

        // Verifica se o mock foi chamado corretamente
        verify(httpClient, times(1)).doHttpGet("https://fakestoreapi.com/products/300");
    }
}
