import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {

        // ---------------- Grafo ----------------
        JOptionPane.showMessageDialog(null, "Criação do grafo");
        // Instancia o objeto
        Grafo grafo = new Grafo();
        grafo.criaGrafo();

        // ---------------- Dígrafo ----------------
        JOptionPane.showMessageDialog(null, "Criação do dígrafo");
        // Instancia o objeto
        Digrafo digrafo = new Digrafo();
        digrafo.criaDigrafo();

        // Cria uma variável para a opção do menu
        int opcao;

        do
        {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                "Menu da Matriz de Incidência"
                + "\n1 - Menu do grafo"
                + "\n2 - Menu do dígrafo"
                + "\n0 - Sair"));

            switch(opcao)
            {
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                break;
                case 1:
                    menuGrafo(grafo);
                break;
                case 2:
                    menuDigrafo(digrafo);
                break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        }while(opcao != 0);
    }

    public static void menuGrafo(Grafo grafo)
    {
        int opcao;

        opcao = Integer.parseInt(JOptionPane.showInputDialog(
                "Menu do Grafo"
                + "\n1 - Mostrar a matriz do grafo"
                + "\n2 - Mostrar grau dos vértices"
                + "\n3 - Verificar se o grafo é simples"
                + "\n4 - Mostrar vizinhos de um vértice"
                + "\n5 - Mostrar irregularidades do grafo"
                + "\n9 - Recriar Grafo"
                + "\n0 - Voltar"));

        switch(opcao)
        {
            case 0:
                JOptionPane.showMessageDialog(null, "Voltando ao menu principal");
            break;
            case 1:
                // Chama função para mostrar a matriz na tela
                grafo.mostraMatriz();
                menuGrafo(grafo);
            break;
            case 2:
                // Chama função para contar os vértices
                grafo.contaVertice();
                menuGrafo(grafo);
            break;
            case 3:
                // Chama função para verificar se o grafo é simples (sem laços ou arestas paralelas)
                if(grafo.simples())
                {
                    JOptionPane.showMessageDialog(null, "O grafo é simples");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "O grafo não é simples");
                }
                menuGrafo(grafo);
            break;
            case 4:
                // Chama função para mostrar os vértices vizinhos ao informado (vértices conectados)
                grafo.vizinhos();
                menuGrafo(grafo);
            break;
            case 5:
                // Caso seja um grafo simples, não chamar a função
                if(grafo.simples())
                {
                    JOptionPane.showMessageDialog(null, 
                    "O grafo não possui laços, nem arestas paralelas");
                }
                else
                {
                    // Chama função para mostrar arestas paralelas, laços e os vértices envolvidos
                    grafo.irregularidades();
                }
                menuGrafo(grafo);
            break;
            case 9:
                // Chama função para recriar o grafo
                grafo.criaGrafo();
            break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
        }
    }

    public static void menuDigrafo(Digrafo digrafo)
    {
        int opcao;

        opcao = Integer.parseInt(JOptionPane.showInputDialog(
                "Menu do Dígrafo"
                + "\n1 - Mostrar a matriz do dígrafo"
                + "\n2 - Mostrar grau dos vértices"
                + "\n3 - Verificar se o dígrafo é simples"
                + "\n4 - Mostrar vizinhos de um vértice"
                + "\n5 - Mostrar irregularidades do dígrafo"
                + "\n9 - Recriar dígrafo"
                + "\n0 - Voltar"));

        switch(opcao)
        {
            case 0:
                JOptionPane.showMessageDialog(null, "Voltando ao menu principal");
            break;
            case 1:
                // Chama função para mostrar a matriz na tela
                digrafo.mostraMatriz();
                menuDigrafo(digrafo);
            break;
            case 2:
                // Chama função para contar os vértices
                digrafo.contaVertice();
                menuDigrafo(digrafo);
            break;
            case 3:
                // Chama função para verificar se o dígrafo é simples (sem laços ou arestas paralelas)
                if(digrafo.simples())
                {
                    JOptionPane.showMessageDialog(null, "O dígrafo é simples");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "O dígrafo não é simples");
                }
                menuDigrafo(digrafo);
            break;
            case 4:
                // Chama função para mostrar os vértices vizinhos ao informado (vértices conectados)
                digrafo.vizinhos();
                menuDigrafo(digrafo);
            break;
            case 5:
                // Caso seja um dígrafo simples, não chamar a função
                if(digrafo.simples())
                {
                    JOptionPane.showMessageDialog(null, 
                    "O dígrafo não possui laços, nem arestas paralelas");
                }
                else
                {
                    // Chama função para mostrar arestas paralelas, laços e os vértices envolvidos
                    digrafo.irregularidades();
                }
                menuDigrafo(digrafo);
            break;
            case 9:
                // Chama função para recriar o dígrafo
                digrafo.criaDigrafo();
            break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
        }
    }
}