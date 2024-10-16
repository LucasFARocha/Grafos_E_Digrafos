import java.util.Arrays;

import javax.swing.JOptionPane;

public class Digrafo {
    private int qtdVertices;
    private int qtdArestas;
    private int digrafo[][];

    // // Função construtora que recebe um parâmetro para a quantidade de vértices
    // Digrafo(int num)
    // {
    //     qtdVertices = num;

    //     // Cria uma matriz quadrada com tamanho igual à quantidade de vértices
    //     digrafo = new int[qtdVertices][qtdVertices];

    //     // Zera todos os valores da matriz
    //     for(int i = 0; i < qtdVertices; i++)
    //     {
    //         for(int j = 0; j < qtdVertices; j++)
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
            for(int y = 0; y < qtdVertices; y++)
            {
                teste += " " + digrafo[x][y];
            }
            teste += "\n";
        }

        System.out.println(teste);
    }

    // Função usada para criar (e recriar) o dígrafo
    public void criaDigrafo()
    {
        qtdVertices = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de vértices"));

        // Cria uma matriz quadrada com tamanho igual à quantidade de vértices
        digrafo = new int[qtdVertices][qtdVertices];

        // Zera todos os valores da matriz
        for(int i = 0; i < qtdVertices; i++)
        {
            for(int j = 0; j < qtdVertices; j++)
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
        qtdArestas = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de arestas"));
        
        for(int i = 0; i < qtdArestas; i++)
        {
            JOptionPane.showMessageDialog(null, "Ligue os vértices");
            int v1 = Integer.parseInt(JOptionPane.showInputDialog("Informe o primeiro"));
            int v2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o segundo"));
        
            // Aumenta o número de ligações do vértice ("liga" os dois pontos)
            digrafo[v1-1][v2-1] += 1;

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
            for(int j = 0; j < qtdVertices; j++)
            {
                matriz += "   " + digrafo[i][j];
            }
            matriz += "\n";
        }
        
        // Mostra a matriz do Digrafo
        JOptionPane.showMessageDialog(null, "A matriz do dígrafo é: \n" + matriz);
    }

    // Função que as ligações de cada vértice
    public void contaVertice()
    {
        // Percorre cada vértice (linha da matriz)
        for(int i = 0; i < qtdVertices; i++)
        {
            int saida = 0;
            int entrada = 0;
            
            for(int j = 0; j < qtdVertices; j++)
            {
                // Caso seja um laço
                if(i == j)
                {
                    saida += digrafo[i][j];
                    entrada += digrafo[i][j];
                }
                else
                {
                    saida += digrafo[i][j];
                    entrada += digrafo[j][i];
                }
            }
            String msgVertices = "\nO vértice nº " + (i+1) + " possui grau de entrada " + entrada + " e saída " + saida;

            JOptionPane.showMessageDialog(null, msgVertices);
        }
    }

    // Função que determina se o Digrafo é simples (sem laços ou arestas paralelas)
    public boolean simples()
    {
        for(int i = 0; i < qtdVertices; i++)
        {
            for(int j = 0; j < qtdVertices; j++)
            {
                // Verifica se há arestas paralelas ou laços
                if(digrafo[i][j] > 1 || i == j && digrafo[i][j] > 0)
                {
                    return false;
                }
            }
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

        for(int j = 0; j < qtdVertices; j++)
        {
            // Caso não seja um laço
            if(vertice-1 != j)
            {
                // Procurando uma conexão na linha do vértice informado
                if(digrafo[vertice-1][j] > 0 && !existeVertice(vizinhos, j+1))
                {
                    vizinhos[indiceVizinhos] = j+1;
                    indiceVizinhos++;
                }
                if(digrafo[j][vertice-1] > 0 && !existeVertice(vizinhos, j+1))
                {
                    vizinhos[indiceVizinhos] = j+1;
                    indiceVizinhos++;
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

        // Determinando o tamanho pelo que for maior, assim evitando erros de procura de índice
        int tamanho = qtdArestas;
        if(qtdVertices > qtdArestas)
        {
            tamanho = qtdVertices;
        }

        int lacos[] = new int[tamanho];
        int paralelas[] = new int[tamanho];

        for(int i = 0; i < qtdVertices; i++)
        {
            for(int j = 0; j < qtdVertices; j++)
            {
                // Caso seja um laço e o valor no índice [i][j] seja maior do que zero
                if(i == j && digrafo[i][j] > 0)
                {
                    // Armazena o vértice
                    lacos[indiceLaco] = i+1;
                    qtdLacos += digrafo[i][j];

                    indiceLaco++;
                }
                // Caso seja uma aresta paralela
                if(i != j && digrafo[i][j] > 1)
                {   
                    // Verificando se o vértice a ser adicionado já existe
                    if(!existeVertice(paralelas, j+1))
                    {
                        paralelas[indiceParalela] = j+1;
                        indiceParalela++;
                    }
                    if(!existeVertice(paralelas, i+1))
                    {
                        paralelas[indiceParalela] = i+1;
                        indiceParalela++;
                    }

                    qtdParalelas += digrafo[i][j];
                }
            }
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
            JOptionPane.showMessageDialog(null, "Esse dígrafo não possui laços");
        }
        else
        {
            JOptionPane.showMessageDialog(null, 
            "Esse dígrafo possui " + qtdLacos + " laço(s)" + 
            "\n\nVértices envolvidos: " + msgLacos);
        }

        if(qtdParalelas == 0)
        {
            JOptionPane.showMessageDialog(null, "Esse dígrafo não possui arestas paralelas");;
        }
        else
        {
            JOptionPane.showMessageDialog(null,
            "\nEsse dígrafo possui " + qtdParalelas + " aresta(s) paralela(s)" +
            "\n\nVértices envolvidos: " + msgParalelas);   
        }      
    }
}