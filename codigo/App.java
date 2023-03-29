public class App {
    /**
     * Metodo que assegura a leitura de um inteiro do console
     * 
     * @return um inteiro lido do console
     */
    private static int lerInt() {
        try {
            return Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número inteiro");
            return lerInt();
        }
    }

    /**
     * Imprime o menu e retorna a opção escolhida
     * 
     * @return açao escolhida na interface
     */
    private static int menuDirecionado() {
        System.out.println(
                "\n\n 1 - Gerar grafico por input\n 2 - Carregar grafo\n 3 - Gerar subgrafo\n 4 - Imprimir grafo\n 5 - Salvar grafo\n 0 - Sair");
        return lerInt();
    }

    /**
     * Imprime o menu e retorna a opção escolhida
     * 
     * @return açao escolhida na interface
     */
    private static int menuNãoDirecionado() {
        System.out.println(
                "\n\n 1 - Gerar grafico por input\n 2 - Carregar grafo\n 3 - Gerar grafo completo\n 4 - Gerar subgrafo\n 5 - Imprimir grafo\n 6 - Salvar grafo\n 0 - Sair");
        return lerInt();
    }

    public static void main(String[] args) {
        GrafoNãoDirecionado grafon = null;
        GrafoDirecionado grafo = null;
        String nome = System.console().readLine("\n Digite o nome do grafo: ");
        // pergunta se o grafo é direcionado ou não direcionado
        boolean direcionado = System.console().readLine("O grafo é direcionado? (s/n)").equals("s");
        if (direcionado) {
            grafo = new GrafoDirecionado(nome);
            app: while (true)
                switch (menuDirecionado()) {
                    case 1:
                        grafo.criar();
                        break;
                    case 2:
                        grafo.carregar(nome + ".txt");
                        break;
                    case 3:
                        System.out.println("Escolha quais vértices deseja manter, digite -1 para parar");
                        Lista<Integer> vertices = new Lista<>();
                        while (true) {
                            int v = lerInt();
                            if (v == -1)
                                break;
                            vertices.add(v);
                        }
                        grafo = (GrafoDirecionado) grafo.subGrafo(vertices);
                        break;
                    case 4:
                        System.out.println("\n" + grafo.toString());
                        break;
                    case 5:
                        grafo.salvar(nome + ".txt");
                        break;
                    case 0:
                        break app;
                }
        } else {
            grafon = new GrafoNãoDirecionado(nome);
            app: while (true)
                switch (menuNãoDirecionado()) {
                    case 1:
                        grafon.criar();
                        break;
                    case 2:
                        grafon.carregar(nome + ".txt");
                        break;
                    case 3:
                        System.out.println("Digite o número de vértices: ");
                        grafon = new GrafoCompleto(grafon.nome, lerInt()).toGrafo(); // cria um grafo completo e
                                                                                     // converte para grafo
                                                                                     // mutável para impressão
                        break;
                    case 4:
                        System.out.println("Escolha quais vértices deseja manter, digite -1 para parar");
                        Lista<Integer> vertices = new Lista<>();
                        while (true) {
                            int v = lerInt();
                            if (v == -1)
                                break;
                            vertices.add(v);
                        }
                        grafon = (GrafoNãoDirecionado) grafon.subGrafo(vertices);
                        break;
                    case 5:
                        System.out.println("\n" + grafon.toString());
                        break;
                    case 6:
                        grafon.salvar(grafon.nome + ".txt");
                        break;
                    case 0:
                        break app;
                }
        }
        System.out.println("Salvar grafo? (s/n)");
        if (direcionado && System.console().readLine().equals("s"))
            grafo.salvar(grafo.nome + ".txt");
        else if (System.console().readLine().equals("s"))
            grafon.salvar(grafon.nome + ".txt");
    }

}
