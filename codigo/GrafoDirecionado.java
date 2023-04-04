import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Grafo direcionado
 */
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
        Vértice saida = this.getVertice(origem);
        Vértice chegada = this.getVertice(destino);
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
            if (this.getVertice(origem) == null)
                System.out.println(this.addVertice(origem) ? "Vértice " + origem + " adicionado."
                        : "Vértice " + origem + " não adicionado.");
            if (this.getVertice(destino) == null)
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
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(" ");
                int origem = Integer.parseInt(valores[0]),
                        destino = Integer.parseInt(valores[1]),
                        peso = Integer.parseInt(valores[2]);
                if (this.getVertice(origem) == null)
                    System.out.println(this.addVertice(origem) ? " Vértice " + origem + " adicionado."
                            : "");
                if (this.getVertice(destino) == null)
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
     * Formato: 0 1 10
     * Em que 0 e 1 são os vértices e 10 é o peso da aresta entre eles.
     * 
     * @param nomeArquivo Nome do arquivo a ser salvo
     */
    public void salvar(String nomeArquivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
            for (Vértice v : this.vértices.values().toArray(new Vértice[this.vértices.size()]))
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
        StringBuilder out = new StringBuilder().append("Grafo \"").append(this.NOME).append("\": {");
        for (Vértice vértice : this.vértices.values())
            for (Aresta aresta : vértice.getArestas())
                if (!aresta.filho())
                    out.append("(").append(vértice.getId()).append(", ").append(aresta.destino())
                            .append(aresta.peso() == -1 ? "" : ", " + aresta.peso()).append("), ");
        if (out.charAt(out.length() - 1) == ' ')
            out.delete(out.length() - 2, out.length());
        return out.append("}").toString();
    }

}
