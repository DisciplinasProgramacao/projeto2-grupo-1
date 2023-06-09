import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Grafo mutável
 */
public class GrafoMutável extends Grafo {

    /**
     * Construtor do grafo mutável
     * 
     * @param nome Nome do grafo
     */
    public GrafoMutável(String nome) {
        super(nome);
    }

    /**
     * Remove um vértice com o id especificado. Ignora a ação e retorna NULL se não
     * existir um vértice com este id
     * 
     * @param id O identificador do vértice a ser removido
     * @return O vértice removido, ou NULL se não existia vértice com este id
     */
    protected Vértice removeVertice(int id) {
        return vértices.remove(id);
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo, caso os dois vértices
     * existam no grafo.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     */
    protected void removeAresta(int origem, int destino) {
        Vértice saida = this.existeVertice(origem);
        Vértice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null) {
            saida.removeAresta(destino);
            chegada.removeAresta(origem);
        }
    }

    /**
     * Cria um grafo a partir do input do usuário, funciona do mesmo modo que o
     * método carregar, para se o usuário pressionar enter vazio.
     * 
     */
    protected void criar() {
        System.out.println(
                " Formato: 0 1 2, em que 0 e 1 são os vértices e 2 o peso da aresta,"
                        + " para representar uma aresta sem peso, use -1."
                        + "\n Para sair, pressione enter vazio." //
        );
        while (true) {
            String[] input = System.console().readLine().split(" ");
            if (input.length == 1)
                break;
            if (input.length < 3) {
                System.out.println(" Input inválido, tente novamente.");
                continue;
            }
            int origem = Integer.parseInt(input[0]),
                    destino = Integer.parseInt(input[1]),
                    peso = Integer.parseInt(input[2]);
            if (this.existeVertice(origem) == null)
                System.out.println(this.addVertice(origem) ? " Vértice " + origem + " adicionado."
                        : " Vértice " + origem + " não adicionado.");
            if (this.existeVertice(destino) == null)
                System.out.println(this.addVertice(destino) ? " Vértice " + destino + " adicionado."
                        : " Vértice " + destino + " não adicionado.");
            System.out.println(this.addAresta(origem, destino, peso)
                    ? " Aresta " + origem + " - " + destino + " adicionada." + " Peso: " + peso
                    : " Aresta " + origem + " - " + destino + " não adicionada, aresta já existente.");
        }
    }

    /**
     * Gera um subgrafo do grafo atual, contendo apenas os vértices e arestas
     * que estão na lista de vértices passada como parâmetro
     * 
     * @param lista_v Lista de vértices a serem incluídos no subgrafo
     * @return O subgrafo gerado
     */
    protected GrafoMutável subGrafo(LinkedList<Integer> lista_v) {
        GrafoMutável subgrafo = new GrafoMutável("Subgrafo de " + this.NOME);
        for (int id : lista_v.toArray(new Integer[lista_v.size()]))
            if (this.existeVertice(id) != null)
                subgrafo.addVertice(id);
        for (int id : lista_v.toArray(new Integer[lista_v.size()]))
            for (Aresta a : this.existeVertice(id).getArestas())
                if (subgrafo.existeVertice(a.destino()) != null)
                    subgrafo.addAresta(subgrafo.existeVertice(id).getId(), a.destino(), a.peso());
        return subgrafo;
    }

    /**
     * Carrega um grafo a partir de um arquivo de texto.
     * Formato: 0 1 2
     * Em que 0 e 1 são os vértices e 2 é o peso da aresta entre eles.
     * 
     * @param nomeArquivo Nome do arquivo a ser carregado
     */
    protected void carregar(String nomeArquivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(" ");
                int origem = Integer.parseInt(valores[0]),
                        destino = Integer.parseInt(valores[1]),
                        peso = Integer.parseInt(valores[2]);
                if (this.existeVertice(origem) == null)
                    System.out.println(this.addVertice(origem) ? " Vértice " + origem + " adicionado."
                            : "");
                if (this.existeVertice(destino) == null)
                    System.out.println(this.addVertice(destino) ? " Vértice " + destino + " adicionado."
                            : "");
                System.out.println(this.addAresta(origem, destino, peso)
                        ? " Aresta " + origem + " - " + destino + " adicionada." + (peso == -1 ? "" : " Peso: " + peso)
                        : " Aresta " + origem + " - " + destino + " não adicionada, aresta já existente.");
            }
            br.close();
        } catch (IOException e) {
            System.out.println("\n Erro ao carregar o grafo de arquivo.");
        }
    }

    /**
     * Salva o grafo em um arquivo de texto.
     * Formato: 0 1 2
     * Em que 0 e 1 são os vértices e 2 é o peso da aresta entre eles.
     * 
     * @param nomeArquivo Nome do arquivo a ser salvo
     */
    protected void salvar(String nomeArquivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
            for (Vértice v : this.vértices.values().toArray(new Vértice[this.vértices.size()]))
                for (Aresta a : v.getArestas())
                    if (a.destino() < v.getId()) {
                        bw.write(v.getId() + " " + a.destino() + " " + a.peso());
                        bw.newLine();
                    }
            bw.close();
        } catch (IOException e) {
            System.out.println("\n Erro ao salvar o grafo em arquivo.");
        }
    }

}
