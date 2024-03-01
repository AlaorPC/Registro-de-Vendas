import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Venda {

	char ativo;
	String codVenda;
	String nomeCliente;
	String codProduto;
	String descrProduto;
	String dtVenda;
	int quantidade;
	float precoUnitario;
	float vlrImpostos;
	
	static String vetCodProdutos[] = { "WJW", "CSL", "CBL", "CCO", "GAN", "WCB", "WBL", "VCT", "VTM", "SLT" };
	static String vetDescProdutos[] = { "Whisky Johny Walker", "Cerveja Skol Lata", "Cerveja Brahma Lata",
			"Coca Cola 2lts", "Guarana Antartica 2lts", "Whisky Cavalo Branco", "Whisky Ballantines",
			"Vinho Concha Y Toro", "Vinho Tinto Miolo", "Suco Laranja Tropicana" };
	static String vetCategorias[] = { "destilado alcoolico importado", "fermentado alcoolico nacional",
			"fermentado alcoolico nacional", "sem alcool nacional", "sem alcool nacional",
			"destilado alcoolico importado", "destilado alcoolico importado", "alcoolico importado",
			"alcoolico nacional", "sem alcool importado" };
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public String getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(String codVenda) {
		this.codVenda = codVenda;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getDescrProduto() {
		return descrProduto;
	}

	public void setDescrProduto(String descrProduto) {
		this.descrProduto = descrProduto;
	}

	public String getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(String dtVenda) {
		this.dtVenda = dtVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public float getVlrImpostos() {
		return vlrImpostos;
	}

	public void setVlrImpostos(float vlrImpostos) {
		this.vlrImpostos = vlrImpostos;
	}
		public String validarCodProduto(String pCodProduto) {
			for (int x = 0; x < vetCodProdutos.length; x++) {
				if (vetCodProdutos[x].equals(pCodProduto)) {
					return vetDescProdutos[x];
				}
			}
			return "";
		}
		

	public long pesquisarVenda(String codVendaPesq) {
		long posicaoCursorArquivo = 0;
		try {
			RandomAccessFile arqVenda = new RandomAccessFile("ARQVENDAS.DAT", "rw");
			while (true) {
				posicaoCursorArquivo = arqVenda.getFilePointer(); // posicao do inicio do registro no arquivo
				ativo = arqVenda.readChar();
				codVenda = arqVenda.readUTF();
				nomeCliente = arqVenda.readUTF();
				codProduto = arqVenda.readUTF();
				descrProduto = arqVenda.readUTF();
				dtVenda = arqVenda.readUTF();
				quantidade = arqVenda.readInt();
				precoUnitario = arqVenda.readFloat();
				vlrImpostos = arqVenda.readFloat();
				if (codVendaPesq.equals(codVenda) && ativo == 'S') {
					arqVenda.close();
					return posicaoCursorArquivo;
				}
			}
		} catch (EOFException e) {
			return -1; // registro nao foi encontrado
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo. O programa sera finalizado!");
			System.exit(0);
			return -1;
		}
	}

	public void salvarVenda() {
		// metodo para incluir um novo registro no final do arquivo em disco
		try {
			RandomAccessFile arqVenda = new RandomAccessFile("ARQVENDAS.DAT", "rw");
			arqVenda.seek(arqVenda.length()); // posiciona o ponteiro no final do arquivo (EOF)
			arqVenda.writeChar(ativo);
			arqVenda.writeUTF(codVenda);
			arqVenda.writeUTF(nomeCliente);
			arqVenda.writeUTF(codProduto);
			arqVenda.writeUTF(descrProduto);
			arqVenda.writeUTF(dtVenda);
			arqVenda.writeInt(quantidade);
			arqVenda.writeFloat(precoUnitario);
			arqVenda.writeFloat(vlrImpostos);
			arqVenda.close();
			JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!", "ok", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo. O programa sera finalizado!", "Erro",
					JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}

	public void editarVenda() {
		// metodo para editar um registro
		try {
			RandomAccessFile arqVenda = new RandomAccessFile("ARQVENDAS.DAT", "rw");
			arqVenda.seek(arqVenda.length()); // posiciona o ponteiro no final do arquivo (EOF)
			arqVenda.writeChar(ativo);
			arqVenda.writeUTF(codVenda);
			arqVenda.writeUTF(nomeCliente);
			arqVenda.writeUTF(codProduto);
			arqVenda.writeUTF(descrProduto);
			arqVenda.writeUTF(dtVenda);
			arqVenda.writeInt(quantidade);
			arqVenda.writeFloat(precoUnitario);
			arqVenda.writeFloat(vlrImpostos);
			arqVenda.close();
			JOptionPane.showMessageDialog(null, "Dados Editados com sucesso!", "ok", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo. O programa sera finalizado!", "Erro",
					JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}

	
	public void desativarVenda(long posicao) {
		// metodo para alterar o valor do campo ATIVO para N, tornando assim o registro
		// excluido
		try {
			RandomAccessFile arqVenda = new RandomAccessFile("ARQVENDAS.DAT", "rw");
			arqVenda.seek(posicao);
			arqVenda.writeChar('N'); // desativar o registro antigo
			arqVenda.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo. O programa sera finalizado!", "Erro",
			JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}

	

	// *********************** SALVAR *****************************
	public void salvar(boolean incluir, long posicaoRegistro, String pCodVenda, String pNomeCliente, String pCodProduto,
			String pDescProduto, String pDtVenda, String pQuantidade, String pPrecoUnitario, String pVlrImpostos) {
			
		boolean msgErro = false;
		if (incluir) {
			posicaoRegistro = pesquisarVenda(pCodVenda);
			if (posicaoRegistro >= 0) {
				JOptionPane.showMessageDialog(null, "Codigo da venda ja cadastrado, digite outro valor.", "Erro",
				JOptionPane.PLAIN_MESSAGE);
				msgErro = true;
			}
		}

		if (!msgErro) {
			ativo = 'S';
			codVenda = pCodVenda;
			nomeCliente = pNomeCliente;
			codProduto = pCodProduto;
			descrProduto = pDescProduto;
			dtVenda = pDtVenda;
			quantidade = Integer.parseInt(pQuantidade);
			precoUnitario = Float.parseFloat(pPrecoUnitario);
			pVlrImpostos = String.valueOf(calcularImpostos(pCodProduto, Float.parseFloat(pPrecoUnitario)));
			vlrImpostos = Float.parseFloat(pVlrImpostos);
			if (incluir) {
				salvarVenda();
			} else {
				desativarVenda(posicaoRegistro);
				salvarVenda();
			}
			}	
		}
		// *********************** Editar *****************************
		public void editar(boolean incluir, long posicaoRegistro, String pCodVenda, String pNomeCliente, String pCodProduto,
			String pDescProduto, String pDtVenda, String pQuantidade, String pPrecoUnitario, String pVlrImpostos) {
			
		boolean msgErro = false;
		if (incluir) {
			posicaoRegistro = pesquisarVenda(pCodVenda);
			if (posicaoRegistro >= 0) {
				JOptionPane.showMessageDialog(null, "Codigo da venda ja cadastrado, digite outro valor.", "Erro",
				JOptionPane.PLAIN_MESSAGE);
				msgErro = true;
			}
		}

		if (!msgErro) {
			ativo = 'S';
			codVenda = pCodVenda;
			nomeCliente = pNomeCliente;
			codProduto = pCodProduto;
			descrProduto = pDescProduto;
			dtVenda = pDtVenda;
			quantidade = Integer.parseInt(pQuantidade);
			precoUnitario = Float.parseFloat(pPrecoUnitario);
			pVlrImpostos = String.valueOf(calcularImpostos(pCodProduto, Float.parseFloat(pPrecoUnitario)));
			vlrImpostos = Float.parseFloat(pVlrImpostos);
			if (incluir) {
				editarVenda();
			} else {
				desativarVenda(posicaoRegistro);
				editarVenda();
			}
		}

	}
	private float calcularImpostos(String codProduto, float precoUnitario) // metodo para calcular o valor final dos impostos
	{
		int x;
		String categoria = "";
		for (x = 0; x < vetCodProdutos.length; x++) {
				if (vetCodProdutos[x].equals(codProduto)) {
					categoria = vetCategorias[x];
				}
			}			
		if (x >= 0) {
			float percentualImpostos = obterPercentualImpostosPorCategoria(categoria);
			return precoUnitario * percentualImpostos;
		} else {
			JOptionPane.showMessageDialog(null, "Codigo do produto invalido!", "Erro", JOptionPane.PLAIN_MESSAGE);
			return 0f;
		}
	}
	
	private float obterPercentualImpostosPorCategoria(String categoria) // metodo para calcular a % por categoria
	{
		if (categoria.contains("destilado") && categoria.contains("alcoolico") && categoria.contains("importado")) {
			return 0.78f; 
		} else if (categoria.contains("fermentado") && categoria.contains("alcoolico") && categoria.contains("nacional")) {
			return 0.58f; 
		} else if (categoria.contains("sem alcool") && categoria.contains("nacional")) {
			return 0.25f; 
		} else if (categoria.contains("alcoolico") && categoria.contains("importado")) {
			return 0.55f; 
		} else if (categoria.contains("alcoolico") && categoria.contains("nacional")) {
			return 0.40f;
		} else if (categoria.contains("sem alcool") && categoria.contains("importado")) {
			return 0.40f; 
		} else {
			JOptionPane.showMessageDialog(null, "Categoria do produto invalida!", "Erro", JOptionPane.PLAIN_MESSAGE);
			return 0f;
		}
	}

	// ************************ CONSULTA *****************************
	public void consultar() {
		RandomAccessFile arqVenda;
		byte opcao;
		String codVendaChave;
		String mesAnoVenda;
		float totalFaturado = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		Scanner leia = new Scanner(System.in);

		do {
			do {
				System.out.println(" ***************  CONSULTA DE VENDAS  ***************** ");
				System.out.println(" [1] LISTA DE VENDAS MES/ANO INFORMADO ");
				System.out.println(" [2] LISTA DE VENDAS POR CLIENTES ");
				System.out.println(" [3] LISTA DE TODAS AS VENDAS ");
				System.out.println(" [0] SAIR");
				System.out.print("\nDigite a opcao desejada: ");
				opcao = leia.nextByte();
				if (opcao < 0 || opcao > 3) {
					System.out.println("Opcao invalida! Digite novamente.\n");
				}
			} while (opcao < 0 || opcao > 3);

			switch (opcao) {
			case 0:
				System.out.println("\n ************  CONSULTA FINALIZADA  ************** \n");
				break;

			case 1: // imprime a lista de vendas no mes e ano informado
				do {
					System.out.print("Digite o mes e o ano (MM/AAAA) para exibir as vendas na epoca desejada: ");
					mesAnoVenda = leia.next();
					if (mesAnoVenda.length() != 7 || mesAnoVenda.charAt(2) != '/') {
						System.out.println("Data invalida! Digite no formato MM/AAAA.");
					}
				} while (mesAnoVenda.length() != 7 || mesAnoVenda.charAt(2) != '/');

				try {
					arqVenda = new RandomAccessFile("ARQVENDAS.DAT", "rw");
					imprimirCabecalho();
					while (true) {
						ativo = arqVenda.readChar();
						codVenda = arqVenda.readUTF();
						nomeCliente = arqVenda.readUTF();
						codProduto = arqVenda.readUTF();
						descrProduto = arqVenda.readUTF();
						dtVenda = arqVenda.readUTF();
						quantidade = arqVenda.readInt();
						precoUnitario = arqVenda.readFloat();
						vlrImpostos = arqVenda.readFloat();
						if (mesAnoVenda.equals(dtVenda.substring(3)) && ativo == 'S') {
							imprimirVenda();
							totalFaturado += (quantidade * precoUnitario) + vlrImpostos;
						}
					}
				} catch (EOFException e) {
					System.out.println("Total Faturado: " + df.format(totalFaturado));
					totalFaturado = 0;
					System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
					leia.nextLine();
					codVendaChave = leia.nextLine();
				} catch (IOException e) {
					System.out.println("Erro na abertura do arquivo. O programa sera finalizado!");
					System.exit(0);
				}

				break;

			case 2:
			// lista de vendas por cliente
			leia.nextLine(); 
				try {
            arqVenda = new RandomAccessFile("ARQVENDAS.DAT", "rw");
			System.out.println("Digite o nome do cliente a ser pesquisado: ");
			codVendaChave = leia.nextLine();
			imprimirCabecalho();
			
            while (true) {
                ativo = arqVenda.readChar();
                codVenda = arqVenda.readUTF();
                nomeCliente = arqVenda.readUTF();
                codProduto = arqVenda.readUTF();
                descrProduto = arqVenda.readUTF();
                dtVenda = arqVenda.readUTF();
                quantidade = arqVenda.readInt();
                precoUnitario = arqVenda.readFloat();
                vlrImpostos = arqVenda.readFloat();
                if (nomeCliente.contains(codVendaChave) && ativo == 'S') {
					System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
					imprimirVenda();
					totalFaturado += (quantidade * precoUnitario) + vlrImpostos;
                }
            }
        } catch (EOFException e) {
			System.out.println("Total Faturado: " + df.format(totalFaturado));
			totalFaturado = 0;
            System.out.println("\n FIM DE RELATORIO\n");
        } catch (IOException e) {
            System.out.println("Erro na abertura do arquivo. O programa sera finalizado!");
            System.exit(0);
        }
			break;

			case 3: // imprime todas os vendas
				try {
					arqVenda = new RandomAccessFile("ARQVENDAS.DAT", "rw");
					imprimirCabecalho();
					while (true) {
						ativo = arqVenda.readChar();
						codVenda = arqVenda.readUTF();
						nomeCliente = arqVenda.readUTF();
						codProduto = arqVenda.readUTF();
						descrProduto = arqVenda.readUTF();
						dtVenda = arqVenda.readUTF();
						quantidade = arqVenda.readInt();
						precoUnitario = arqVenda.readFloat();
						vlrImpostos = arqVenda.readFloat();
						if (ativo == 'S') {
							imprimirVenda();
							totalFaturado += (quantidade * precoUnitario) + vlrImpostos;
						}
					}
				} catch (EOFException e) {
					System.out.println("Total Faturado: " + df.format(totalFaturado));
					System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
					totalFaturado = 0;
					leia.nextLine();
					codVendaChave = leia.nextLine();
				} catch (IOException e) {
					System.out.println("Erro na abertura do arquivo. O programa sera finalizado!");
					System.exit(0);
				}

			}

		} while (opcao != 0);
	}
	
	public void imprimirCabecalho() {
		System.out.println(
				"-COD.VENDA-  -------- CLIENTE ----------  --DESCR.PROD.--  -DT.VENDA-  -QUANT-  -PRC.UNIT.-  -IMPOSTO-  -VLR VENDA-");
	}

	public void imprimirVenda() {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(formatarString(codVenda, 11) + "  " + formatarString(nomeCliente, 30) + "  "
				+ formatarString(descrProduto, 15) + "  " + formatarString(dtVenda, 10) + "  "
				+ formatarString(String.valueOf(quantidade), 7) + "  "
				+ formatarString(String.valueOf(precoUnitario), 11) + "  "
				+ formatarString(String.valueOf(df.format(vlrImpostos)), 11) + "  "
				+ formatarString(String.valueOf(df.format((quantidade * precoUnitario) + vlrImpostos)), 11));
	}

	public static String formatarString(String texto, int tamanho) {
		// retorna uma string com o numero de caracteres passado como parametro em
		// TAMANHO
		if (texto.length() > tamanho) {
			texto = texto.substring(0, tamanho);
		} else {
			while (texto.length() < tamanho) {
				texto = texto + " ";
			}
		}
		return texto;
	}

	public static boolean validarData(String data) {
		if (data.length() != 10 || data.charAt(2) != '/' || data.charAt(5) != '/') {
			JOptionPane.showMessageDialog(null, "Data invalida, digite no formato DD/MM/AAAA", "Erro",
			JOptionPane.PLAIN_MESSAGE);
			return false;
		}

		int dia, mes, ano;

		try {
			dia = Integer.parseInt(data.substring(0, 2));
			mes = Integer.parseInt(data.substring(3, 5));
			ano = Integer.parseInt(data.substring(6));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Data invalida, digite o dia, mes e ano com valores numericos.", "Erro",
					JOptionPane.PLAIN_MESSAGE);
			return false;
		}

		if (dia < 1 || dia > 31) {
			JOptionPane.showMessageDialog(null, "Data invalida, digite o dia entre 1 e 31.", "Erro",
					JOptionPane.PLAIN_MESSAGE);
			return false;
		}

		if (mes < 1 || mes > 12) {
			JOptionPane.showMessageDialog(null, "Data invalida, digite o mes entre 1 e 12.", "Erro",
					JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		if (ano > 2020) {
			JOptionPane.showMessageDialog(null, "Data invalida, digite um ano menor ou igual a 2020.", "Erro",
					JOptionPane.PLAIN_MESSAGE);
			return false;
		}

		if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
			JOptionPane.showMessageDialog(null, "Data invalida, para os meses 4, 6, 9 ou 11, digite no maximo 30 dias.",
					"Erro", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		if (mes == 2) {
			if (dia > 28) {
				JOptionPane.showMessageDialog(null, "Data invalida, para o mes de fevereiro, digite no maximo 28 dias.",
						"Erro", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
		}
		return true;
	}

	public static String obterCategoria(String pCodProduto) {
		for (int i = 0; i <= vetCategorias.length; i++) {
			if (vetCodProdutos[i].equals(pCodProduto)) {
				return pCodProduto;
			}
		}
		return "";
	}

}