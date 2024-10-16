import java.util.Arrays;

import javax.swing.JOptionPane;

public class Digrafo {
    private int qtdVertices;
    private int qtdArestas;
    private int digrafo[][];

    // // Função construtora que recebe um parâmetro para a quantidade de vértices
    // Digrafo(int numVertices, int numArestas)
    // {
    //     qtdVertices = numVertices;
    //     qtdArestas = numArestas;

    //     // Cria uma matriz quadrada com tamanho igual à quantidade de vértices
    //     digrafo = new int[qtdVertices][qtdArestas];

    //     // Zera todos os valores da matriz
    //     for(int i = 0; i < qtdVertices; i++)
    //     {
    //         for(int j = 0; j < qtdArestas; j++)
    //         {
    //             digrafo[i][j] = 0;
    //         }
    //     }
    // }

    // Função usada para mostrar a matriz no console
    // Serve primariamente para testes
    public void console()
    {
        String teste = "";

        for(int x = 0; x < qtdVertices; x++)
        {
            for(int y = 0; y < qtdArestas; y++)
            {
                teste += "   " + digrafo[x][y];
            }
            teste += "\n";
        }

        System.out.println(teste);
    }

    // Função usada para criar (e recriar) o dígrafo
    public void criaDigrafo()
    {
        qtdVertices = Integer.parseInt(JOptionPane.showInputDialog(
            "Informe a quantidade de vértices do dígrafo"));
        qtdArestas = Integer.parseInt(JOptionPane.showInputDialog(
            "Informe a quantidade de arestas do dígrafo"));;

        // Cria uma matriz quadrada com tamanho igual à quantidade de vértices
        digrafo = new int[qtdVertices][qtdArestas];

        // Zera todos os valores da matriz
        for(int i = 0; i < qtdVertices; i++)
        {
            for(int j = 0; j < qtdArestas; j++)
            {
                digrafo[i][j] = 0;
            }
        }

        console();
        montaDigrafo();
    }

    // Função usada para montar a matriz
    public void montaDigrafo()
    {
        for(int i = 0; i < qtdArestas; i++)
        {
            JOptionPane.showMessageDialog(null, "Ligue os vértices do dígrafo");
            int v1 = Integer.parseInt(JOptionPane.showInputDialog("Informe o primeiro"));
            int v2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o segundo"));
        
            // Aumenta o número de ligações do vértice ("liga" os dois pontos)
            if(v1 == v2)
            {
                digrafo[v1-1][i] += 2;
            }
            else
            {
                digrafo[v1-1][i] += 1;
                digrafo[v2-1][i] -= 1;
            }

            // teste
            console();
        }
    }

    private boolean existeVertice(int vetor[], int vertice)
    {
        for(int k = 0; k < vetor.length; k++)
        {
            if(vetor[k] == vertice)
            {
                return true;
            }
        }
        return false;
    }

    // Polimorfismo ou gambiarra?
    // Nota do meu eu do futuro: É realmente polimorfismo, mais especificamente sobrecarga
    private boolean existeVertice(String vetor[], String vertice)
    {
        for(int k = 0; k < vetor.length; k++)
        {
            if(vetor[k].equals(vertice))
            {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------------------
    // ---------------------- Área das funções ---------------------------

    // Função que monta a matriz em uma String e a exibe na tela
    public void mostraMatriz()
    {
        // Monta a matriz em uma string
        String matriz = "";
            
        for(int i = 0; i < qtdVertices; i++)
        {
            for(int j = 0; j < qtdArestas; j++)
            {
                matriz += "   " + digrafo[i][j];
            }
            matriz += "\n";
        }
        
        // Mostra a matriz do Digrafo
        JOptionPane.showMessageDialog(null, "A matriz do Digrafo é: \n" + matriz);
    }

    // Função que as ligações de cada vértice
    public void contaVertice()
    {
        // Percorre cada vértice (linha da matriz)
        for(int i = 0; i < qtdVertices; i++)
        {
            int saida = 0;
            int entrada = 0;
            
            for(int j = 0; j < qtdArestas; j++)
            {
                // Caso seja um laço
                if(digrafo[i][j] > 1)
                {
                    // Como os laços estão armazenados com o valor 2, é necessário dividir
                    saida += digrafo[i][j] / 2;
                    entrada += digrafo[i][j] / 2;
                }
                else if(digrafo[i][j] == 1)
                {
                    saida += digrafo[i][j];
                }
                else
                {
                    // Math.abs retorna o valor absoluto, ou seja, o módulo
                    entrada += Math.abs(digrafo[i][j]);
                }
            }

            String msgVertices = "\nO vértice nº " + (i+1) + " possui grau de entrada " + entrada + " e saída " + saida;
            JOptionPane.showMessageDialog(null, msgVertices);
        }
    }

    // Função que determina se o Digrafo é simples (sem laços ou arestas paralelas)
    public boolean simples()
    {
        // String para armazenar o "código" do vértice
        String vertice = "";
        // Vetor para armazenar os "códigos"
        String conexoes[] = new String[qtdArestas];

        // Diferente do grafo, o vetor conexoes é uma String, sendo impossível realizar a
        // verificação de existência (método equals()) sem inicializar os valores
        for(int i = 0; i < conexoes.length; i++)
        {
            conexoes[i] = "";
        }
        
        // Um for invertido para procurar todos os vértices de uma aresta
        for(int i = 0; i < qtdArestas; i++)
        {
            for(int j = 0; j < qtdVertices; j++)
            {
                // Verifica se há um laço
                if(digrafo[j][i] == 2)
                {
                    return false;
                }
                // Se houver uma conexão, armazena o vértice na String
                if(digrafo[j][i] == 1)
                {
                    // Caso já haja um vértice na String, seu novo valor será vértice anterior + atual
                    // Como se trata de uma String, eles serão concatenados: 1 + 3 = 13
                    // Isso cria um "código" único para cada aresta
                    vertice += j+1;
                }
                if(digrafo[j][i] == -1)
                {
                    vertice+= "-" + (j+1);
                }
            }

            // Teste
            // System.out.println("Conexões: " + vertice);

            // Se o código da aresta atual já estiver no vetor conexoes,
            // significa que outra aresta já possui ele
            if(existeVertice(conexoes, vertice))
            {
                // System.out.println("Existe");
                return false;
            }
            // Se não houver um código igual, adicione no vetor
            conexoes[i] = vertice;

            // Reinicia a String para a próxima aresta
            vertice = "";
        }
        // Caso não encontre nenhuma irregularidade, retorna true
        return true;
    }

    // Função que encontra os vizinhos do vértice informado
    public void vizinhos()
    {
        int vertice = Integer.parseInt(JOptionPane.showInputDialog("Informe um vértice"));
        String msgVizinhos = "O vértice " + vertice + " é vizinho do(s) vértice(s): \n";

        int vizinhos[] = new int[qtdVertices];
        int indiceVizinhos = 0;

        // Indo de aresta em aresta
        for(int i = 0; i < qtdArestas; i++)
        {
            // Verifica se o vértice informado possui uma conexão nessa aresta
            if(digrafo[vertice-1][i] == 1 || digrafo[vertice-1][i] == -1)
            {
                // Nessa aresta, procure de vértice em vértice
                for(int j = 0; j < qtdVertices; j++)
                {
                    // digrafo[j][i] == 1 || digrafo[j][i] == -1 - Caso haja uma conexão
                    // vertice-1 != j - Caso seja um vértice diferente do informado
                    // !existeVertice(vizinhos, j+1) - Caso o vértice já não esteja no vetor
                    // ...Então adicione o vértice no vetor
                    if((digrafo[j][i] == 1 || digrafo[j][i] == -1) && vertice-1 != j && !existeVertice(vizinhos, j+1))
                    {
                        vizinhos[indiceVizinhos] = j+1;
                        indiceVizinhos++;      
                    }
                }
            }
        }

        for(int i = 0; i < vizinhos.length; i++)
        {
            if(vizinhos[i] > 0)
            {
                msgVizinhos += "\n" + vizinhos[i];
            }
        }

        // Se a primeira posição do vetor não possui valor, então nenhuma outra possui
        if(vizinhos[0] == 0)
        {
            msgVizinhos = "O vértice informado não possui vizinhos";
        }

        JOptionPane.showMessageDialog(null, msgVizinhos);
    }

    // Função que mostra as arestas paralelas, laços e os vértices envolvidos
    public void irregularidades()
    {
        int qtdLacos = 0;
        int qtdParalelas = 0;

        // Controle do índice dos vetores
        int indiceLaco = 0;
        int indiceParalela = 0;
        int lacos[] = new int[qtdArestas];
        int paralelas[] = new int[qtdArestas];

        // Vetor que armazena os vértices conectados de uma aresta
        int vertices[] = new int[2];
        int indiceVertices = 0;
        // String que armazena os vértices conectados de uma aresta em forma de "código"
        String codigo = "";
        // Vetor que armazena os códigos
        String conexoes[] = new String[qtdArestas];

        // Inicializando todos os índices como "" para não quebrar o equals()
        for(int i = 0; i < conexoes.length; i++)
        {
            conexoes[i] = "";
        }

        for(int i = 0; i < qtdArestas; i++)
        {
            for(int j = 0; j < qtdVertices; j++)
            {
                // Caso seja um laço
                if(digrafo[j][i] == 2)
                {
                    // Armazena o vértice
                    lacos[indiceLaco] = j+1;
                    indiceLaco++;
                    qtdLacos++;
                }
                // Caso o vértice possua uma saída (também considera laços)
                if(digrafo[j][i] > 0)
                {
                    // Armazena o código
                    codigo += j+1;
                    // System.out.println("Código: " + codigo);

                    // Adiciona o vértice no vetor
                    // *esse vetor possui tamanho 2 e é reciclado a cada aresta
                    // System.out.println("Vértice " + (j+1) + " adicionado ao vetor");
                    vertices[indiceVertices] = j+1;
                    indiceVertices++;
                }
                // Caso o vértice possua uma entrada
                if(digrafo[j][i] == -1)
                {
                    codigo += "-" + (j+1);
                    // System.out.println("Código: " + codigo);

                    // System.out.println("Vértice " + (j+1) + " adicionado ao vetor");
                    vertices[indiceVertices] = j+1;
                    indiceVertices++;
                }
            }

            if(existeVertice(conexoes, codigo))
            {
                // System.out.println("Essa aresta é paralela");
                for(int k = 0; k < vertices.length; k++)
                {
                    if(!existeVertice(paralelas, vertices[k]))
                    {
                        // System.out.println("Vértice envolvido: " + vertices[k]);
                        paralelas[indiceParalela] = vertices[k];
                        indiceParalela++;
                        qtdParalelas++;
                    }
                }
            }
            else
            {
                // System.out.println("Essa aresta é nova, adicionada no vetor; código: " + codigo);
                conexoes[i] = codigo;
            }

            codigo = "";
            indiceVertices = 0;
        }
        // // --------- Teste ---------
        // for(int i = 0; i < paralelas.length; i++)
        // {
        //     System.out.println(paralelas[i]);
        // }

        // Ordenando o vetor em ordem crescente
        Arrays.sort(paralelas);

        // Variáveis de mensagem
        String msgLacos = "";
        for(int i = 0; i < lacos.length; i++)
        {
            if(lacos[i] > 0)
            {
                msgLacos += "\n" + lacos[i];
            }
        }

        String msgParalelas = "";
        for(int i = 0; i < paralelas.length; i++)
        {
            if(paralelas[i] > 0)
            {
                msgParalelas += "\n" + paralelas[i];
            }
        }

        // Exibição das mensagens
        if(qtdLacos == 0)
        {
            JOptionPane.showMessageDialog(null, "Esse grafo não possui laços");
        }
        else
        {
            JOptionPane.showMessageDialog(null, 
            "Esse dígrafo possui " + qtdLacos + " laço(s)" + 
            "\n\nVértices envolvidos: " + msgLacos);
        }

        if(qtdParalelas == 0)
        {
            JOptionPane.showMessageDialog(null, "Esse grafo não possui arestas paralelas");;
        }
        else
        {
            JOptionPane.showMessageDialog(null,
            "\nEsse dígrafo possui " + qtdParalelas + " aresta(s) paralela(s)" +
            "\n\nVértices envolvidos: " + msgParalelas);   
        }   
    }

}