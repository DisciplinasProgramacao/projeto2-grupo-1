import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrafoDirecionado extends GrafoMutável {

    /**
     * Construtor do grafo direcionado
     * 
     * @param nome Nome do grafo
     */
    public GrafoDirecionado(String nome) {
        super(nome);
    }

    /**
     * Adiciona uma aresta ao grafo
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     * @param peso    Peso da aresta
     * @return TRUE se a aresta for adicionada, FALSE caso contrário
     */
    @Override
    public boolean addAresta(int origem, int destino, int peso) {
        boolean adicionou = false;
        Vértice saida = this.existeVertice(origem);
        Vértice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null)
            adicionou = (saida.addAresta(destino, peso, false) && chegada.addAresta(origem, peso, true));
        return adicionou;
    }

    /**
     * Cria um grafo a partir do input do usuário, funciona do mesmo modo que o
     * método carregar, para se o usuário pressionar enter vazio.
     * 
     */
    @Override
    public void criar() {
        System.out.println(
                " Formato: 0 1 10, em que 0 e 1 são os vértices e 10 o peso da aresta, pressione enter para finalizar");
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
                System.out.println(this.addVertice(origem) ? "Vértice " + origem + " adicionado."
                        : "Vértice " + origem + " não adicionado.");
            if (this.existeVertice(destino) == null)
                System.out.println(this.addVertice(destino) ? "Vértice " + destino + " adicionado."
                        : "Vértice " + destino + " não adicionado.");
            System.out.println(this.addAresta(origem, destino, peso)
                    ? "Aresta " + origem + " - " + destino + " adicionada." + " Peso: " + peso
                    : "Aresta " + origem + " - " + destino + " não adicionada, aresta já existente.");
        }
    }

    /**
     * Carrega um grafo a partir de um arquivo de texto.
     * Formato: 0 1 10
     * Em que 0 e 1 são os vértices e 10 é o peso da aresta entre eles.
     * 
     * @param nomeArquivo Nome do arquivo a ser carregado
     */
    @Override
    public void carregar(String nomeArquivo) {
        System.out.println("\n\n");
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(" ");
                int origem = Integer.parseInt(valores[0]),
                        destino = Integer.parseInt(valores[1]),
                        peso = Integer.parseInt(valores[2]);
                if (this.existeVertice(origem) == null)
                    System.out.println(this.addVertice(origem) ? "Vértice " + origem + " adicionado." + " Status: "
                            : "Vértice " + origem + " não adicionado.");
                if (this.existeVertice(destino) == null)
                    System.out.println(this.addVertice(destino) ? "Vértice " + destino + " adicionado."
                            : "Vértice " + destino + " não adicionado.");
                System.out.println(this.addAresta(origem, destino, peso)
                        ? "Aresta " + origem + " - " + destino + " adicionada." + " Peso: " + peso
                        : "Aresta " + origem + " - " + destino + " não adicionada, aresta já existente.");
            }
            br.close();
        } catch (IOException e) {
            System.out.println("\n Erro ao carregar o grafo de arquivo.");
        }
    }

    /**
     * Salva o grafo em um arquivo de texto.
     * Formato: 0 1 10
     * Em que 0 e 1 são os vértices e 10 é o peso da aresta entre eles.
     * 
     * @param nomeArquivo Nome do arquivo a ser salvo
     */
    public void salvar(String nomeArquivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
            for (Vértice v : this.vertices.allElements(new Vértice[this.vertices.size()]))
                for (Aresta a : v.getArestas())
                    if (!a.filho()) {
                        bw.write(v.getId() + " " + a.destino() + " " + a.peso());
                        bw.newLine();
                    }
            bw.close();
        } catch (IOException e) {
            System.out.println("\n Erro ao salvar o grafo em arquivo.");
        }
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
                if (!a.filho()) {
                    str.append("(" + v.getId() + ", " + a.destino() + ", " + a.peso() + ")");
                    if (j < arestas.length - 1 || i < vertices.length - 1)
                        str.append(", ");
                }
            }
        }
        str.append("}");
        return new String(str);
    }
}
