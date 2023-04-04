import java.util.TreeMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

/**
 * Classe básica para todos os grafos. Esta classe define os métodos básicos
 * para
 * a manipulação de grafos, como a inserção de vértices e arestas, e a
 * verificação de existência de vértices e arestas.
 */
abstract public class Grafo {

    /**
     * Armazena o nome do grafo
     */
    protected final String NOME;

    /**
     * Armazena os vértices do grafo
     */
    protected TreeMap<Integer, Vértice> vértices;

    /**
     * Construtor. Cria um grafo vazio com um NOME escolhido pelo usuário. Em caso
     * de NOME não informado (string vazia), recebe o NOME genérico "Grafo"
     * 
     * @param NOME Nome do grafo
     */
    public Grafo(String NOME) {
        this.NOME = NOME;
        this.vértices = new TreeMap<Integer, Vértice>();
    }

    /**
     * Adiciona um vértice ao grafo. Caso o vértice já exista, o comando é ignorado
     * e
     * retorna FALSE.
     * 
     * @param id O identificador do vértice a ser adicionado
     * @return TRUE se foi inserido, FALSE caso contrário
     */
    protected boolean addVertice(int id) {
        boolean result = false;
        if (!this.vértices.containsKey(id)) {
            this.vértices.put(id, new Vértice(id));
            result = true;
        }
        return result;
    }

    /**
     * Retorna um vértice do grafo, caso ele exista. Caso o vértice não exista, o
     * comando é ignorado e retorna NULL.
     * 
     * @param idVertice O identificador do vértice a ser retornado
     * @return O vértice, ou NULL caso contrário
     */
    protected Vértice getVertice(int idVertice) {
        return this.vértices.containsKey(idVertice) ? this.vértices.get(idVertice) : null;
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo, caso os dois vértices
     * existam no grafo.
     * Caso a aresta já exista, ou algum dos vértices não existir, o comando é
     * ignorado e retorna FALSE.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     * @param peso    Peso da aresta
     * @return TRUE se foi inserida, FALSE caso contrário
     */
    protected boolean addAresta(int origem, int destino, int peso) {
        boolean adicionou = false;
        Vértice saida = this.getVertice(origem);
        Vértice chegada = this.getVertice(destino);
        if (saida != null && chegada != null)
            adicionou = (saida.addAresta(destino, peso) && chegada.addAresta(origem, peso));
        return adicionou;
    }

    /**
     * Retorna uma aresta entre dois vértices do grafo, caso os dois vértices
     * existam no grafo.
     * Caso a aresta não exista, ou algum dos vértices não existir, o comando é
     * ignorado e retorna NULL.
     * 
     * @param verticeA Vértice de origem
     * @param verticeB Vértice de destino
     * @return A aresta, ou NULL caso contrário
     */
    protected Aresta existeAresta(int verticeA, int verticeB) {
        Aresta aresta = null;
        Vértice saida = this.getVertice(verticeA);
        Vértice chegada = this.getVertice(verticeB);
        if (saida != null && chegada != null) {
            aresta = saida.getAresta(verticeB);
        }
        return aresta;
    }

    /**
     * Retorna o tamanho do grafo (número de arestas)
     * 
     * @return O tamanho do grafo
     */
    protected int tamanho() {
        int tamanho = 0;
        for (Vértice v : this.vértices.values()) {
            tamanho += v.grau();
        }
        return tamanho / 2;
    }

    /**
     * Retorna a ordem do grafo (número de vértices)
     * 
     * @return A ordem do grafo
     */
    protected int ordem() {
        return this.vértices.size();
    }

    /**
     * Retorna true se o grafo for completo e false caso contrário
     * 
     * @return TRUE se o grafo for completo, FALSE caso contrário
     */
    protected boolean completo() {
        int n = this.ordem();
        return this.tamanho() == (n * (n - 1)) / 2;
    }

    /**
     * Verifica se o grafo está vazio
     * 
     * @return TRUE se o grafo estiver vazio, FALSE caso contrário
     */
    protected boolean vazio() {
        return this.vértices.isEmpty();
    }

    /**
     * Retorna uma representação em String do grafo
     * { {id1, id2, peso1}, {id3, id4, peso2}, ... }
     * 
     * @return A representação em String do grafo
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Grafo \"").append(this.NOME).append("\": {");
        for (Vértice vértice : this.vértices.values())
            for (Aresta aresta : vértice.getArestas())
                if (aresta.destino() > vértice.getId())
                    out.append("{").append(vértice.getId()).append(", ").append(aresta.destino())
                            .append(aresta.peso() == -1 ? "" : ", " + aresta.peso()).append("}, ");
        if (out.charAt(out.length() - 1) == ' ')
            out.delete(out.length() - 2, out.length());
        return out.append("}").toString();
    }

    /**
     * Busca em largura no grafo
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice a ser encontrado
     * @return O vértice encontrado, ou NULL caso contrário
     */
    protected Vértice buscaEmLargura(int origem, int destino) {
        Vértice v = this.getVertice(origem);
        if (v == null)
            return null;
        Vértice resultado = null;
        Queue<Vértice> fila = new LinkedList<Vértice>();
        Set<Vértice> visitados = new HashSet<Vértice>();
        fila.add(v);
        visitados.add(v);
        System.out.print("\n Vértices visitados: ");
        while (!fila.isEmpty()) {
            Vértice vértice = fila.remove();
            System.out.print(vértice.getId() + " ");
            if (vértice.getId() == destino) {
                resultado = vértice;
                break;
            }
            for (Aresta a : vértice.getArestas()) {
                Vértice destino_ = this.getVertice(a.destino());
                if (!visitados.contains(destino_)) {
                    fila.add(destino_);
                    visitados.add(destino_);
                }
            }
        }
        return resultado;
    }

    /**
     * Busca em profundidade no grafo
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice a ser encontrado
     * @return O vértice encontrado, ou NULL caso contrário
     */
    protected Vértice buscaEmProfundidade(int origem, int destino) {
        Vértice v = this.getVertice(origem);
        if (v == null)
            return null;
        Vértice resultado = null;
        Stack<Vértice> pilha = new Stack<Vértice>();
        Set<Vértice> visitados = new HashSet<Vértice>();
        pilha.push(v);
        visitados.add(v);
        System.out.print("\n Vértices visitados: ");
        while (!pilha.isEmpty()) {
            Vértice vértice = pilha.pop();
            System.out.print(vértice.getId() + " ");
            if (vértice.getId() == destino) {
                resultado = vértice;
                break;
            }
            for (Aresta a : vértice.getArestas()) {
                Vértice destino_ = this.getVertice(a.destino());
                if (!visitados.contains(destino_)) {
                    pilha.push(destino_);
                    visitados.add(destino_);
                }
            }
        }
        return resultado;
    }

}
