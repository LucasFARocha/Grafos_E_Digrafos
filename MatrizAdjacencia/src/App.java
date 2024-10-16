import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        // Instancia o objeto
        Digrafo digrafo = new Digrafo();
        digrafo.criaDigrafo();

        // Cria uma variável para a opção do menu
        int opcao = 0;

        do
        {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                "Menu do Dígrafo"
                + "\n1 - Mostrar a matriz"
                + "\n2 - Mostrar grau dos vértices"
                + "\n3 - Verificar se o dígrafo é simples"
                + "\n4 - Mostrar vizinhos de um vértice"
                + "\n5 - Mostrar irregularidades do dígrafo"
                + "\n9 - Recriar dígrafo"
                + "\n0 - Sair"));

            switch(opcao)
            {
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                break;
                case 1:
                    // Chama função para mostrar a matriz na tela
                    digrafo.mostraMatriz();
                break;
                case 2:
                    // Chama função para contar os vértices
                    digrafo.contaVertice();
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
                break;
                case 4:
                    // Chama função para mostrar os vértices vizinhos ao informado (vértices conectados)
                    digrafo.vizinhos();
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
                break;
                case 9:
                    // Chama função para recriar o dígrafo
                    digrafo.criaDigrafo();
                break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        }while(opcao != 0);
    }

    
}