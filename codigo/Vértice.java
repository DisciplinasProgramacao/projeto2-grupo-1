/** 
 * MIT License
 *
 * Copyright(c) 2021-23 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/** Classe Vértice para um grafo */
public class Vértice {

    /** Atributo para armazenar as arestas do vértice */
    private ABB<Aresta> arestas;

    /** Atributo para armazenar o ID do vértice */
    private final int ID;

    /**
     * Construtor para criação de vértice identificado
     * 
     * @param ID Número/ID do vértice a ser criado (atributo final).
     */
    public Vértice(int ID) {
        this.ID = ID;
        this.arestas = new ABB<Aresta>();
    }

    /**
     * Retorna o ID do vértice, caso seja necessário para verificações próprias
     * 
     * @return Identificador do vértice (int)
     */
    public int getId() {
        return this.ID;
    }

    /**
     * Adiciona uma aresta ponderada neste vértice para um destino
     * 
     * @param peso Peso da aresta
     * @param dest Vértice de destino
     * @return TRUE se foi inserida, FALSE caso já existisse e não foi inserida.
     */
    public boolean addAresta(int destino, int peso) {
        return this.arestas.add(destino, new Aresta(peso, destino, true));
    }

    /**
     * Adiciona uma aresta ponderada neste vértice para um destino e indica se é
     * filho ou não
     * 
     * @param peso  Peso da aresta
     * @param dest  Vértice de destino
     * @param filho Indica se a aresta é filho ou não
     * @return TRUE se foi inserida, FALSE caso já existisse e não foi inserida.
     */
    public boolean addAresta(int destino, int peso, boolean filho) {
        return this.arestas.add(destino, new Aresta(peso, destino, filho));
    }

    /**
     * Retorna a aresta para o destino indicado. Retorna null caso não exista a
     * aresta.
     * 
     * @param destino Destino da aresta a ser retornada.
     * @return A aresta, ou null se não existir.
     */
    public Aresta existeAresta(int destino) {
        return this.arestas.contains(destino);
    }

    /**
     * Remove e retorna a aresta para o destino indicado. Retorna null caso não
     * exista a aresta.
     * 
     * @param destino Destino da aresta a ser removida.
     * @return A aresta removida, ou null se não existir.
     */
    public void removeAresta(int destino) {
        this.arestas.remove(destino);
    }

    /**
     * Retorna o grau do vértice (número de arestas)
     * 
     * @return Número de arestas do vértice
     */
    public int grau() {
        return this.arestas.size();
    }

    /**
     * Retorna arestas do vértice em array
     * 
     * @return Arestas do vértice
     */
    public Aresta[] getArestas() {
        return this.arestas.allElements(new Aresta[this.arestas.size()]);
    }

}
