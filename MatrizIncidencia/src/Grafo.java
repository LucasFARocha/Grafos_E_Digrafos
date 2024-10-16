import java.util.Arrays;

import javax.swing.JOptionPane;

public class Grafo {
    private int qtdVertices;
    private int qtdArestas;
    private int grafo[][];

    // // Função construtora
    // Grafo()
    // {
    //     qtdVertices = Integer.parseInt(JOptionPane.showInputDialog(
    //         "Informe a quantidade de vértices do grafo"));
    //     qtdArestas = Integer.parseInt(JOptionPane.showInputDialog(
    //         "Informe a quantidade de arestas do grafo"));

    //     // Cria uma matriz quadrada com tamanho igual à quantidade de vértices
    //     grafo = new int[qtdVertices][qtdArestas];

    //     // Zera todos os valores da matriz
    //     for(int i = 0; i < qtdVertices; i++)
    //     {
    //         for(int j = 0; j < qtdArestas; j++)
    //         {
    //             grafo[i][j] = 0;
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
                teste += " " + grafo[x][y];
            }
            teste += "\n";
        }

        System.out.println(teste);
    }

    // Função usada para criar (e recriar) o grafo
    public void criaGrafo()
    {
        qtdVertices = Integer.parseInt(JOptionPane.showInputDialog(
            "Informe a quantidade de vértices do grafo"));
        qtdArestas = Integer.parseInt(JOptionPane.showInputDialog(
            "Informe a quantidade de arestas do grafo"));

        // Cria uma matriz quadrada com tamanho igual à quantidade de vértices
        grafo = new int[qtdVertices][qtdArestas];

        // Zera todos os valores da matriz
        for(int i = 0; i < qtdVertices; i++)
        {
            for(int j = 0; j < qtdArestas; j++)
            {
                grafo[i][j] = 0;
            }
        }

        console();
        montaGrafo();
    }

    // Função usada para montar a matriz
    public void montaGrafo()
    {
        for(int i = 0; i < qtdArestas; i++)
        {
            JOptionPane.showMessageDialog(null, "Ligue os vértices do grafo");
            int v1 = Integer.parseInt(JOptionPane.showInputDialog("Informe o primeiro"));
            int v2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o segundo"));
        
            // Aumenta o número de ligações do vértice ("liga" os dois pontos)
            if(v1 == v2)
            {
                grafo[v1-1][i] += 2;
            }
            else
            {
                grafo[v1-1][i] += 1;
                grafo[v2-1][i] += 1;
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
                matriz += "   " + grafo[i][j];
            }
            matriz += "\n";
        }
        
        // Mostra a matriz do grafo
        JOptionPane.showMessageDialog(null, "A matriz do grafo é: \n" + matriz);
    }

    // Função que conta as ligações de cada vértice
    public void contaVertice()
    {
        // Percorre cada vértice (linha da matriz)
        for(int i = 0; i < qtdVertices; i++)
        {
            String msgVertices = "\nO vértice nº " + (i+1) + " possui grau ";
            int contaVertice = 0;
            
            for(int j = 0; j < qtdArestas; j++)
            {
                contaVertice += grafo[i][j];
            }

            JOptionPane.showMessageDialog(null, msgVertices + contaVertice);
        }
    }

    // Função que determina se o grafo é simples (sem laços ou arestas paralelas)
    public boolean simples()
    {
        // String para armazenar o "código" do vértice
        String codigo = "";
        // Vetor para armazenar os "códigos"
        int conexoes[] = new int[qtdArestas];
        
        // Um for invertido para procurar todos os vértices de uma aresta
        for(int i = 0; i < qtdArestas; i++)
        {
            for(int j = 0; j < qtdVertices; j++)
            {
                // Verifica se há um laço
                if(grafo[j][i] == 2)
                {
                    return false;
                }
                // Se houver uma conexão, armazena o vértice na String
                if(grafo[j][i] == 1)
                {
                    // Caso já haja um vértice na String, seu novo valor será vértice anterior + atual
                    // Como se trata de uma String, eles serão concatenados: 1 + 3 = 13
                    // Isso cria um "código" único para cada aresta
                    codigo += j+1;
                }
            }

            // Teste
            // System.out.println("Conexões: " + codigo);

            // Se o código da aresta atual já estiver no vetor conexoes,
            // significa que outra aresta já possui ele
            if(existeVertice(conexoes, Integer.parseInt(codigo)))
            {
                return false;
            }
            // Se não houver um código igual, adicione no vetor
            conexoes[i] = Integer.parseInt(codigo);

            // Reinicia a String para a próxima aresta
            codigo = "";
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
            // Verifica se o vértice informado possui uma (e apenas uma) conexão nessa aresta
            if(grafo[vertice-1][i] == 1)
            {
                // Nessa aresta, procure de vértice em vértice
                for(int j = 0; j < qtdVertices; j++)
                {
                    // grafo[j][i] == 1 - Caso haja uma conexão
                    // vertice-1 != j - Caso seja um vértice diferente do informado
                    // !existeVertice(vizinhos, j+1) - Caso o vértice já não esteja no vetor
                    // ...Então adicione o vértice no vetor
                    if(grafo[j][i] == 1 && vertice-1 != j && !existeVertice(vizinhos, j+1))
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
        int conexoes[] = new int[qtdArestas];

        for(int i = 0; i < qtdArestas; i++)
        {
            for(int j = 0; j < qtdVertices; j++)
            {
                // Caso seja um laço
                if(grafo[j][i] == 2)
                {
                    // Armazena o vértice
                    lacos[indiceLaco] = j+1;
                    indiceLaco++;
                    qtdLacos++;
                }
                // Caso o vértice possua uma conexão (mesmo sendo um laço)
                if(grafo[j][i] > 0)
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
            }

            // Se já houver um codigo igual no vetor conexoes
            if(existeVertice(conexoes, Integer.parseInt(codigo)))
            {
                // System.out.println("Essa aresta é paralela");

                // Percorre o vetor vertices
                for(int k = 0; k < vertices.length; k++)
                {
                    // Verifica se o vértice já está no vetor paralelas, evitando duplicatas
                    if(!existeVertice(paralelas, vertices[k]))
                    {
                        // System.out.println("Vértice envolvido: " + vertices[k]);

                        // Adiciona o vértice novo no vetor paralelas
                        paralelas[indiceParalela] = vertices[k];
                        indiceParalela++;
                        qtdParalelas++;
                    }
                }
            }
            else
            {
                // System.out.println("Essa aresta é nova, adicionada no vetor; código: " + codigo);
                conexoes[i] = Integer.parseInt(codigo);
            }

            // Reiniciando o codigo e o indice do vetor vertices
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
            "Esse grafo possui " + qtdLacos + " laço(s)" + 
            "\n\nVértices envolvidos: " + msgLacos);
        }

        if(qtdParalelas == 0)
        {
            JOptionPane.showMessageDialog(null, "Esse grafo não possui arestas paralelas");;
        }
        else
        {
            JOptionPane.showMessageDialog(null,
            "\nEsse grafo possui " + qtdParalelas + " aresta(s) paralela(s)" +
            "\n\nVértices envolvidos: " + msgParalelas);   
        }
    }

}