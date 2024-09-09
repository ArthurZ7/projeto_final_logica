/******************************************************************************
ArthurZ7

Trabalho Final da disciplina de Lógica de Programação
05/09/2024

Funções, Vetores e matrizes 
*******************************************************************************/
import java.util.*;
public class Projeto_Final_LP
{
	public static void main(String[] args) {
		Scanner s = new Scanner (System.in);
		
	    // Quantidade de clientes
		System.out.print("Informe a quantidade de clientes: ");
		int qntCli = s.nextInt();
		
		String nome [] = new String [qntCli];
		String tel [] = new String [qntCli];
		int tipo [] = new int [qntCli]; // (0, 1 ou 2)
		int min [] = new int [qntCli];
		double valorConta [] = new double [qntCli];
		
		double precos [][] = new double [3][2];
		
		// Tarefa 1 - Insere as informações de cada um dos clientes
		for (int i=0; i<qntCli; i++) {
		    System.out.printf("Dados do %do. cliente:\n", i+1);
		    System.out.print("Nome: ");
		    nome[i] = s.next();
		    s.nextLine();
		    System.out.print("Telefone: ");
		    tel[i] = s.next();
		    System.out.print("Tipo: ");
		    tipo[i] = s.nextInt();
		    System.out.print("Minutos: ");
		    min[i] = s.nextInt();
		    System.out.println();
		} 
		
		// Tarefa 2 - Faz a leitura da tabela de preços da companhia telefônica
		System.out.println();
		System.out.println("Informe o preco basico e excedente de cada tipo de conta: ");
		for (int i=0; i<3; i++){
		    System.out.printf("Tipo %d: ", i);
		    for (int j=0; j<2; j++){
		        precos[i][j] = s.nextDouble();
		    }
		} 
		
		// Tarefa 03 - Realiza o cálculo do valor da conta de cada cliente, de acordo com a tabela de preços
		for (int i=0; i<qntCli; i++) {
    		if (min[i] <= 90){
    		    valorConta[i] = precos[tipo[i]][0];
    		} else {
    		    valorConta[i] = precos[tipo[i]][0] + (min[i]- 90)*precos[tipo[i]][1];
    		}
		}
		
		// Tarefa 4 - Lista de opções de relatórios
		int opcao = 0;
		while (opcao != 7){
		  imprimeMenu();
		  opcao = s.nextInt();
		  System.out.print("\n > ");
		  if (opcao == 1){
		    // Gera um relatório com os dados de todos os clientes
		    relatorioClientes(qntCli, nome, tel, tipo, min, valorConta);
		  }
		  if (opcao == 2){
        	// Calcula a receita total da empresa telefônica (soma de todas as contas)
		    System.out.printf("A receita total = R$ %.2f", receitaTotal(qntCli, valorConta));
		  }
		  if (opcao == 3){
		  // Imprime o nome e telefone do cliente do qual a conta foi mais barata
		    contaMaisBarata(qntCli, nome, tel, valorConta);
		  }
		  if (opcao == 4){
		  // Média de pulsos (Minutos) consumidos por clientes de conta TIPO 1
		      mediaPulsos(qntCli, tipo, min, 1);
		  }
		  if (opcao == 5){
		  // Mostra a quantidade de clientes que consumiu acima de 120 pulsos
		    clientesPorPulsos (qntCli, min, 120);
		  }
		  if (opcao == 6){
		  // Mostra a porcentagem de de clientes tipo 2
		    System.out.printf("Porcentagem de clientes tipo 2: %.1f%%\n", porcentClientesTipo (qntCli, tipo, 2));
		  }
		}
		System.out.println("FIM DO PROGRAMA!");
		
		
	}
	
	// Função que imprime o menu de opções
	static void imprimeMenu() {
	    System.out.println("\n=========================================");
	    System.out.println("  - MENU DE OPÇÕES - ");
        System.out.println("  1) Relatorio de clientes ");
        System.out.println("  2) A receita total  ");
        System.out.println("  3) Conta foi mais barata ");
        System.out.println("  4) Consumo medio de clientes tipo 1 ");
        System.out.println("  5) Clientes que consumiram acima de 120 pulsos ");
        System.out.println("  6) A porcentagem de clientes tipo 2 ");
        System.out.println("  7) Sair ");
        System.out.print("=== - Informe uma opcao: ");
	}
	
	// Função que gera o Relatório de clientes
	static void relatorioClientes(int qntClientes, String nome[], String tel[], int tipo[], int min[], double valorConta[]) {
	    for (int i=0; i<qntClientes; i++) {
	        System.out.printf("%s, %s, Tipo %d, Minutos: %d, Conta = R$ %.2f\n", nome[i], tel[i], tipo[i], min[i], valorConta[i]);
	    }
	}
	
	// Calcula a receita total da empresa telefônica (soma de todas as contas)
	static double receitaTotal(int qntClientes, double valorConta[]) {
	    double total = 0;
	    
	    for (int i=0; i<qntClientes; i++) {
	        total += valorConta[i];
	    }
	    return total;
	}
	
	// Imprime o nome e telefone do cliente do qual a conta foi mais barata (supor não haver empates)
	static void contaMaisBarata(int qntClientes, String nome[], String tel[], double valorConta[]) {
	    
	    double menorConta = Integer.MAX_VALUE;
	    String mNome = "";
	    String mTelefone = "";
	    
	    for (int i=0; i<qntClientes; i++) {
	        if (valorConta[i] < menorConta){
	            menorConta = valorConta[i];
	            mNome = nome[i];
	            mTelefone = tel[i];
	        }
	    }
        System.out.printf("A conta mais barata pertence ao senhor(a): %s, telefone: %s\n", mNome, mTelefone);
	}
	
	// Média de pulsos (Minutos) consumidos por clientes de algum dos TIPOS de Conta
	static void mediaPulsos(int qntClientes, int tipo[], int pulsos[], int tipoConta) {
    
        double media = 0;
        int qntClientesTipo = 0;
        
        for (int i=0; i<qntClientes; i++) {
            if (tipo[i] == tipoConta){
                media += pulsos[i];
                qntClientesTipo++;
            }
        }
        
        media = media / qntClientesTipo;
        
        System.out.printf("A média dos pulsos consumidos por clientes do tipo %d foi de: %.2f pulsos\n", tipoConta, media);
	}
    
	// Calcula a quantidade de clientes que consumiu acima de uma quantidade especifica de pulsos
	static void clientesPorPulsos (int qntClientes, int pulsos[], int limitePulsos) {
    
        int qntClientesPulsos = 0;
        
        for (int i=0; i<qntClientes; i++) {
            if (pulsos[i] > limitePulsos){
                qntClientesPulsos++;
            }
        }
        
        System.out.printf("%d clientes consumiram acima de %d\n", qntClientesPulsos, limitePulsos);
	}
    
	// A porcentagem de clientes que possuem conta de algum tipo, em relação ao total de clientes
	static double porcentClientesTipo (int qntClientes, int tipo[], int tipoEscolhido) {
	    
	    double porcentagem = 0;
	    
	    for (int i=0; i<qntClientes; i++) {
	        if (tipo[i] == tipoEscolhido){
	            porcentagem++;
	        }
	    }
	    
	    porcentagem = (porcentagem*100) / qntClientes;
	    
	    return porcentagem;
	}
}

