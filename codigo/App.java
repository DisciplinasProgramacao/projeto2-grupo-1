import java.util.LinkedList;

/**
 * Classe principal do programa
 * 
 * @author Igor Pinheiro
 */
public class App {

    /**
     * Construtor vazio
     */
    App() {
        System.out.println("\n Bem vindo ao programa de grafos");
    }

    /**
     * Metodo que assegura a leitura de um inteiro do console
     * 
     * @param mensagem mensagem a ser exibida ao usuario
     * @return um inteiro lido do console
     */
    private static int lerInt(String mensagem) {
        try {
            return Integer.parseInt(System.console().readLine(mensagem));
        } catch (NumberFormatException e) {
            return lerInt(" Digite um número inteiro\n ");
        }
    }

    /**
     * Imprime o menu e retorna a opção escolhida
     * 
     * @return açao escolhida na interface
     */
    private static int menuDirecionado() {
        return lerInt("\n 1 - Gerar grafico por input"
                + "\n 2 - Carregar grafo"
                + "\n 3 - Gerar subgrafo"
                + "\n 4 - Imprimir grafo"
                + "\n 5 - Salvar grafo"
                + "\n 6 - Verificar se é completo"
                + "\n 7 - Busca em largura"
                + "\n 8 - Busca em profundidade"
                + "\n 9 - Verificar tamanho, ordem e se o grafo é completo"
                + "\n 0 - Sair\n " //
        );
    }

    /**
     * Imprime o menu e retorna a opção escolhida
     * 
     * @return açao escolhida na interface
     */
    private static int menuNãoDirecionado() {
        return lerInt("\n 1 - Gerar grafico por input"
                + "\n 2 - Carregar grafo"
                + "\n 3 - Gerar grafo completo"
                + "\n 4 - Gerar subgrafo"
                + "\n 5 - Imprimir grafo"
                + "\n 6 - Salvar grafo"
                + "\n 7 - Verificar se é completo"
                + "\n 8 - Busca em largura"
                + "\n 9 - Busca em profundidade"
                + "\n 10 - Verificar tamanho, ordem e se o grafo é completo"
                + "\n 0 - Sair\n " //
        );
    }

    /**
     * Método principal
     * 
     * @param args Argumentos de linha de comando
     */
    public static void main(String[] args) {
        new App();
        String nome = System.console().readLine(" Digite o nome do grafo: ");
        nome = nome.isEmpty() ? "Meu_Grafo" : nome;
        if (System.console().readLine(" O grafo " + nome + " é direcionado? (s/n)").contains("s")) {
            GrafoDirecionado grafo = new GrafoDirecionado(nome);
            app: while (true)
                switch (menuDirecionado()) {
                    case 1:
                        grafo.criar();
                        break;
                    case 2:
                        grafo.carregar(nome + ".txt");
                        break;
                    case 3:
                        System.out.println("\n Escolha quais vértices deseja manter, digite -1 para parar");
                        LinkedList<Integer> vertices = new LinkedList<>();
                        while (true) {
                            int v = lerInt(" ");
                            if (v == -1)
                                break;
                            vertices.add(v);
                        }
                        System.out.println("\n Subgrafo: " + grafo.subGrafo(vertices));
                        break;
                    case 4:
                        System.out.println("\n" + grafo.toString());
                        break;
                    case 5:
                        grafo.salvar(nome + ".txt");
                        break;
                    case 6:
                        System.out.println(grafo.completo() ? "\n É completo" : "\n Não é completo");
                        break;
                    case 7:
                        grafo.buscaEmLargura(lerInt(" Digite o vértice de origem: "));
                        break;
                    case 8:
                        grafo.buscaEmProfundidade(lerInt(" Digite o vértice de origem: "));
                        break;
                    case 9:
                        System.out.println("\n Tamanho: " + grafo.tamanho() + "\n Ordem: " + grafo.ordem()
                                + (grafo.completo() ? "\n É completo" : "\n Não é completo"));
                        break;
                    case 0:
                        break app;
                }
            if (System.console().readLine("\n Salvar grafo? (s/n)").contains("s"))
                grafo.salvar(grafo.NOME + ".txt");
        } else {
            GrafoNãoDirecionado grafo = new GrafoNãoDirecionado(nome);
            app: while (true)
                switch (menuNãoDirecionado()) {
                    case 1:
                        grafo.criar();
                        break;
                    case 2:
                        grafo.carregar(nome + ".txt");
                        break;
                    case 3:
                        System.out.println(
                                new GrafoCompleto(grafo.NOME, lerInt("\n Digite o número de vértices: ")));
                        break;
                    case 4:
                        System.out.println("\n Escolha quais vértices deseja manter, digite -1 para parar");
                        LinkedList<Integer> vertices = new LinkedList<>();
                        while (true) {
                            int v = lerInt(" ");
                            if (v == -1)
                                break;
                            vertices.add(v);
                        }
                        System.out.println("\n Subgrafo: " + grafo.subGrafo(vertices));
                        break;
                    case 5:
                        System.out.println("\n" + grafo);
                        break;
                    case 6:
                        grafo.salvar(grafo.NOME + ".txt");
                        break;
                    case 7:
                        System.out.println(grafo.completo() ? "\n É completo" : "\n Não é completo");
                        break;
                    case 8:
                        grafo.buscaEmLargura(lerInt(" Digite o vértice de origem: "));
                        break;
                    case 9:
                        grafo.buscaEmProfundidade(lerInt(" Digite o vértice de origem: "));
                        break;
                    case 10:
                        System.out.println("\n Tamanho: " + grafo.tamanho() + "\n Ordem: " + grafo.ordem()
                                + (grafo.completo() ? "\n É completo" : "\n Não é completo"));
                        break;
                    case 0:
                        break app;
                }
            if (System.console().readLine("\n Salvar grafo? (s/n)").contains("s"))
                grafo.salvar(grafo.NOME + ".txt");
        }
    }

}
