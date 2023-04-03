/**
 * Grafo completo
 */
public class GrafoCompleto extends Grafo {

    /**
     * Construtor. Cria um grafo completo com um nome escolhido pelo usuário. Em
     * caso de nome não informado, o grafo é criado com o nome "Grafo Completo"
     * 
     * @param nome  O nome do grafo
     * @param ordem A ordem do grafo
     */
    public GrafoCompleto(String nome, int ordem) {
        super(nome + " Completo");
        for (int i = 0; i < ordem; i++)
            this.addVertice(i);
        for (int i = 0; i < ordem; i++)
            for (int j = i + 1; j < ordem; j++)
                this.addAresta(i, j, 0);
    }

}
