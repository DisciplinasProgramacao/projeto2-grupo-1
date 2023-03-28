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
 * Classe básica para um Grafo simples não direcionado.
 */
public class Grafo {
    protected final String nome;
    protected ABB<Vértice> vertices;

    /**
     * Construtor. Cria um grafo vazio com um nome escolhido pelo usuário. Em caso
     * de nome não informado
     * (string vazia), recebe o nome genérico "Grafo"
     */
    public Grafo(String nome) {
        if (nome == null || nome.isEmpty())
            this.nome = "Grafo";
        else
            this.nome = nome;
        this.vertices = new ABB<>();
    }

    /**
     * Adiciona um vértice com o id especificado. Ignora a ação e retorna false se
     * já existir um vértice com este id
     * 
     * @param id O identificador do vértice a ser criado/adicionado
     * @return TRUE se houve a inclusão do vértice, FALSE se já existia vértice com
     *         este id
     */
    protected boolean addVertice(int id) {
        Vértice novo = new Vértice(id);
        return this.vertices.add(id, novo);
    }

    /**
     * Retorna o vértice com o id especificado. Retorna NULL se não existir um
     * vértice com este id
     * 
     * @param id O identificador do vértice a ser retornado
     * @return O vértice com o id especificado, ou NULL se não existia vértice com
     *         este id
     */
    protected Vértice existeVertice(int idVertice) {
        return vertices.contains(idVertice);
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
        Vértice saida = this.existeVertice(origem);
        Vértice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null)
            adicionou = (saida.addAresta(destino, peso) && chegada.addAresta(origem, peso));
        return adicionou;
    }

    /**
     * Retorna a aresta entre dois vértices do grafo, caso os dois vértices existam
     * no grafo.
     * Caso a aresta não exista, ou algum dos vértices não existir, o comando é
     * ignorado e retorna NULL.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     * @return A aresta, ou NULL caso contrário
     */
    protected Aresta existeAresta(int verticeA, int verticeB) {
        Aresta aresta = null;
        Vértice saida = this.existeVertice(verticeA);
        Vértice chegada = this.existeVertice(verticeB);
        if (saida != null && chegada != null) {
            aresta = saida.existeAresta(verticeB);
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
        for (int i = 0; i < this.vertices.size(); i++) {
            Vértice v = this.vertices.contains(i);
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
        return this.vertices.size();
    }

    /**
     * Retorna true se o grafo for completo e false caso contrário
     * 
     * @return TRUE se o grafo for completo, FALSE caso contrário
     */
    protected boolean completo() {
        return this.ordem() == this.tamanho();
    }

    /**
     * Verifica se o grafo está vazio
     * 
     * @return TRUE se o grafo estiver vazio, FALSE caso contrário
     */
    protected boolean vazio() {
        return this.vertices.isEmpty();
    }

    /**
     * Retorna uma representação em String do grafo
     * { {id1, id2, peso1}, {id3, id4, peso2}, ... }
     * 
     * @return A representação em String do grafo
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Grafo " + this.nome + ": {");
        Vértice[] vertices = this.vertices.allElements(new Vértice[this.vertices.size()]);
        for (int i = 0; i < vertices.length; i++) {
            Vértice v = vertices[i];
            Aresta[] arestas = v.getArestas();
            for (int j = 0; j < arestas.length; j++) {
                Aresta a = arestas[j];
                if (a.destino() > v.getId()) {
                    str.append("{" + v.getId() + ", " + a.destino() + ", " + a.peso() + "}");
                    if (j < arestas.length - 1 || i < vertices.length - 1)
                        str.append(", ");
                }
            }
        }
        str.append("}");
        return new String(str);
    }

}
