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

    /**
     * Retorna true se o grafo for completo e false caso contrário
     * 
     * @return TRUE se o grafo for completo, FALSE caso contrário
     */
    @Override
    public boolean completo() {
        return true;
    }

    /**
     * Converte para grafo mutável
     * 
     * @return Grafo mutável
     */
    public GrafoNãoDirecionado toGrafo() {
        GrafoNãoDirecionado g = new GrafoNãoDirecionado(this.nome);
        for (int i = 0; i < this.vertices.size(); i++)
            g.addVertice(i);
        for (int i = 0; i < this.vertices.size(); i++)
            for (int j = i + 1; j < this.vertices.size(); j++)
                g.addAresta(i, j, 0);
        return g;
    }

}
