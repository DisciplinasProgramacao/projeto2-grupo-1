/**
 * MIT License
 *
 * Copyright(c) 2021 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Classe para arestas do grafo
 */
public class Aresta {

    /**
     * Define se a aresta aponta ou não para seu vizinho
     */
    private boolean filho;

    /**
     * Define o peso e o destino da aresta
     */
    private int peso;

    /**
     * Define o vértice de destino da aresta
     */
    private int destino;

    /**
     * Construtor para arestas com peso. A aresta é criada como não-visitada.
     * 
     * @param peso    Peso da aresta
     * @param destino Vértice de destino
     * @param filho   Indica se a aresta é filho ou não
     */
    public Aresta(int peso, int destino, boolean filho) {
        this.peso = peso;
        this.destino = destino;
        this.filho = filho;
    }

    /**
     * Método de acesso para o peso da aresta
     * 
     * @return Peso da aresta
     */
    public int peso() {
        return this.peso;
    }

    /**
     * Método de acesso para o destino da aresta
     * 
     * @return Id do vértice de destino
     */
    public int destino() {
        return this.destino;
    }

    /**
     * Método de acesso para o destino da aresta
     * 
     * @return Id do vértice de destino
     */
    public boolean filho() {
        return this.filho;
    }

}
