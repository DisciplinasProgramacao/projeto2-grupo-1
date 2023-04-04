import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

/**
 * Classe de testes
 */
class Testes {

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
        grafo = new GrafoMutável("GrafoTeste");
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
        grafo.salvar("GrafoTeste.txt");
        grafo.carregar("GrafoTeste.txt");
        int tst[][] = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
        for (int[] i : tst)
            assertNotNull(grafo.existeAresta(i[0], i[1]));
    }

    /**
     * Testa o toString do grafo não direcionado
     */
    @Test
    void testToString() {
        assertEquals("Grafo \"GrafoTeste\": {{0, 1}, {0, 2}, {1, 2}}", grafo.toString());
    }

    /**
     * Testa o toString de grafo direcionado
     */
    @Test
    void testToStringDirecionado() {
        GrafoDirecionado grafo = new GrafoDirecionado("GrafoTeste");
        grafo.addVertice(3);
        grafo.addVertice(4);
        grafo.addVertice(5);
        grafo.addAresta(3, 4, -1);
        grafo.addAresta(5, 3, -1);
        grafo.addAresta(4, 5, -1);
        assertEquals("Grafo \"GrafoTeste\": {(3, 4), (4, 5), (5, 3)}", grafo.toString());
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
        Vértice vertice = new Vértice(10);
        vertice.addAresta(11, -1);
        vertice.removeAresta(11);
        assertNull(vertice.getAresta(11));
    }

    /**
     * Teste se é uma aresta é ou não direcionada
     */
    @Test
    void testDirecionada() {
        Aresta aresta = new Aresta(10, 1, true);
        assertTrue(aresta.filho());
    }

    /**
     * Teste de remoção de vértice
     */
    @Test
    void testRemoverVertice() {
        grafo.removeVertice(0);
        assertNull(grafo.getVertice(0));
    }

    /**
     * Teste de remoção de vértice com arestas
     */
    @Test
    void testRemoverVerticeComArestas() {
        grafo.removeVertice(0);
        assertNull(grafo.getVertice(0));
        assertNull(grafo.existeAresta(0, 1));
        assertNull(grafo.existeAresta(0, 2));
    }

    /**
     * Teste da buscaEmLargura
     */
    @Test
    void testBuscaEmLargura() {
        assertEquals(grafo.buscaEmLargura(0, 2).getId(), 2);
    }

    /**
     * Teste da buscaEmProfundidade
     */
    @Test
    void testBuscaEmProfundidade() {
        assertEquals(grafo.buscaEmProfundidade(0, 2).getId(), 2);
    }
}
