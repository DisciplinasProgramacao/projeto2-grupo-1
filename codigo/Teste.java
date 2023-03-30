import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class Teste {

    /**
     * Grafo mutável não direcionado para testes
     */
    private GrafoMutável grafo;

    /**
     * Cria um grafo com 3 vértices e 3 arestas
     * {{0, 1} {0, 2} {1, 2}
     */
    @BeforeEach
    void setUp() {
        grafo = new GrafoMutável("");
        for (int i = 0; i < 3; i++)
            grafo.addVertice(i);
        grafo.addAresta(0, 1, -1);
        grafo.addAresta(0, 2, -1);
        grafo.addAresta(1, 2, -1);
    }

    /**
     * Testa se o grafo foi criado corretamente
     */
    @Test
    void testAddArestaEAddVertice() {
        int tst[][] = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
        for (int[] i : tst)
            assertNotNull(grafo.existeAresta(i[0], i[1]));
    }

    /**
     * Tenta salvar e carregar um grafo em um arquivo
     */
    @Test
    void testSalvarECarregar() {
        grafo.salvar("");
        grafo.carregar("");
        int tst[][] = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
        for (int[] i : tst)
            assertNotNull(grafo.existeAresta(i[0], i[1]));
    }

    /**
     * Testa o toString do grafo não direcionado
     */
    @Test
    void testToString() {
        assertEquals("Grafo Grafo: {{0, 1}, {0, 2}, {1, 2}}", grafo.toString());
    }

    /**
     * Testa o toString de grafo direcionado
     */
    @Test
    void testToStringDirecionado() {
        GrafoDirecionado grafo = new GrafoDirecionado("");
        grafo.addVertice(0);
        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addAresta(0, 1, -1);
        grafo.addAresta(2, 0, -1);
        grafo.addAresta(1, 2, -1);
        assertEquals("Grafo Grafo: {(0, 1), (1, 2), (2, 0)}", grafo.toString());
    }

    /**
     * Testa o tamanho do grafo
     */
    @Test
    void testTamanho() {
        assertEquals(grafo.tamanho(), 3);
    }

    /*
     * Teste de ordem do grafo
     */
    @Test
    void testOrdem() {
        assertEquals(grafo.ordem(), 3);
    }

    /**
     * Testa se o grafo é completo
     */
    @Test
    void testCompletoGrafo() {
        assertTrue(grafo.completo());
    }

    /**
     * Testa se o grafo é vazio
     */
    @Test
    void testVazio() {
        grafo = new GrafoMutável("");
        assertTrue(grafo.vazio());
    }

    /**
     * Teste de remoção de aresta
     */
    @Test
    void testRemoverAresta() {
        Vértice vertice = new Vértice(0);
        vertice.addAresta(1, 10);
        vertice.removeAresta(1);
        assertNull(vertice.existeAresta(1));
    }

    /**
     * Teste se é uma aresta é ou não direcionada
     */
    @Test
    void testDirecionada() {
        Aresta aresta = new Aresta(10, 1, true);
        assertTrue(aresta.filho());
    }

}
