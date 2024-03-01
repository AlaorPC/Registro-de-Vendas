import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

    static long posicaoRegistro = -1;
    static boolean incluir = true;
    static boolean buscouVenda = false;

    public static void main(String[] args) {
        Venda venda = new Venda();

        JFrame janela = new JFrame("CASA RIO NEGRO");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 350);
        janela.setLocationRelativeTo(null);

        janela.setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());

        JLabel labelTitulo = new JLabel("REGISTRO DE VENDAS");
        labelTitulo.setFont(new Font("Verdana", Font.PLAIN, 16));
        panelTitulo.add(labelTitulo);

        JPanel panelDados = new JPanel();
        panelDados.setLayout(new FlowLayout());

        JLabel labelCodVenda = new JLabel("Codigo da Venda     ");
        labelCodVenda.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textCodVenda = new JTextField(30);
        panelDados.add(labelCodVenda);
        panelDados.add(textCodVenda);

        JLabel labelNomeCliente = new JLabel("Nome do Cliente     ");
        labelNomeCliente.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textNomeCliente = new JTextField(30);
        panelDados.add(labelNomeCliente);
        panelDados.add(textNomeCliente);

        JLabel labelCodProduto = new JLabel("Codigo do Produto   ");
        labelCodProduto.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textCodProduto = new JTextField(30);
        panelDados.add(labelCodProduto);
        panelDados.add(textCodProduto);

        JLabel labelDescrProduto = new JLabel("Descricao do Produto");
        labelDescrProduto.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textDescrProduto = new JTextField(30);
        panelDados.add(labelDescrProduto);
        panelDados.add(textDescrProduto).setEnabled(false);

        JLabel labelDtVenda = new JLabel("Data da Venda       ");
        labelDtVenda.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textDtVenda = new JTextField(30);
        panelDados.add(labelDtVenda);
        panelDados.add(textDtVenda);

        JLabel labelQuantidade = new JLabel("Quantidade          ");
        labelQuantidade.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textQuantidade = new JTextField(30);
        panelDados.add(labelQuantidade);
        panelDados.add(textQuantidade);

        JLabel labelprecoUnitario = new JLabel("Preco Unitario      ");
        labelprecoUnitario.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textprecoUnitario = new JTextField(30);
        panelDados.add(labelprecoUnitario);
        panelDados.add(textprecoUnitario);

        JLabel labelvlrImpostos = new JLabel("Valor de Impostos   ");
        labelvlrImpostos.setFont(new Font("Consolas", Font.PLAIN, 14));
        JTextField textvlrImpostos = new JTextField(30);
        panelDados.add(labelvlrImpostos);
        panelDados.add(textvlrImpostos).setEnabled(false);
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout());

        JButton botaoNovo = new JButton("Novo");
        JButton botaoBuscar = new JButton("Buscar");
        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoConsultar = new JButton("Consultas");
        JButton botaoExcluir = new JButton("Excluir");
        JButton botaoEditar = new JButton("Editar");
        JButton botaoFim = new JButton("Fim");

        panelBotoes.add(botaoNovo);
        panelBotoes.add(botaoBuscar);
        panelBotoes.add(botaoSalvar);
        panelBotoes.add(botaoConsultar);
        panelBotoes.add(botaoExcluir);
        panelBotoes.add(botaoEditar);
        panelBotoes.add(botaoFim);

        janela.add(panelTitulo, BorderLayout.NORTH);
        janela.add(panelDados, BorderLayout.CENTER);
        janela.add(panelBotoes, BorderLayout.SOUTH);
        janela.setVisible(true);

		textCodProduto.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			venda.validarCodProduto(textCodProduto.getText());
		}});
        botaoNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textCodVenda.setText("");
                textNomeCliente.setText("");
                textCodProduto.setText("");
                textDescrProduto.setText("");
                textDtVenda.setText("");
                textQuantidade.setText("");
                textprecoUnitario.setText("");
                textvlrImpostos.setText("");
                textCodVenda.setEnabled(true);
                textCodVenda.requestFocus();
                incluir = true;
                buscouVenda = false;
            }
        });

        botaoBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textCodVenda.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Codigo da venda nao digitado!", "Erro", JOptionPane.PLAIN_MESSAGE);
				} else {
					posicaoRegistro = venda.pesquisarVenda(textCodVenda.getText());
		
					if (posicaoRegistro == -1) {
						JOptionPane.showMessageDialog(null, "Venda nao cadastrada no arquivo, digite outro valor.", "Erro", JOptionPane.PLAIN_MESSAGE);
					} else {
						textNomeCliente.setText(venda.getNomeCliente());
                        textCodProduto.setText(venda.getCodProduto());
						textDescrProduto.setText(venda.getDescrProduto());
						textDtVenda.setText(venda.getDtVenda());
						textQuantidade.setText(String.valueOf(venda.getQuantidade()));
						textprecoUnitario.setText(String.valueOf(venda.getPrecoUnitario()));
						textvlrImpostos.setText(String.valueOf(venda.getVlrImpostos()));
						textCodVenda.setEnabled(false);
						incluir = false;
						buscouVenda = true;
					}
				}
			}
		});

        botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String descrProduto = "";
                try{
				if (textCodVenda.getText().isBlank()) 
                {
					JOptionPane.showMessageDialog(null, "Digite o codigo da venda!", "Erro", JOptionPane.PLAIN_MESSAGE);
                }
                    else if(textNomeCliente.getText().isBlank())
                    {
                    JOptionPane.showMessageDialog(null, "Digite o nome do cliente!", "Erro", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if(textCodProduto.getText().isBlank())
                    {
                    JOptionPane.showMessageDialog(null, "Digite o codigo do produto!", "Erro", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if(venda.validarCodProduto(textCodProduto.getText()).equals(""))
                    {
                        descrProduto = venda.validarCodProduto(textCodProduto.getText());
                        JOptionPane.showMessageDialog(null, "Codigo do produto invalido!", "Erro", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if(textDtVenda.getText().isBlank())
                    {
                    JOptionPane.showMessageDialog(null, "Digite a data da venda!", "Erro", JOptionPane.PLAIN_MESSAGE);
                    }
                    else if(!venda.validarData(textDtVenda.getText()))
                    {

                    }
                    else if(textQuantidade.getText().isBlank())
                    {
                        System.out.println("OI");
                        JOptionPane.showMessageDialog(null, "Digite a quantidade", "Erro", JOptionPane.PLAIN_MESSAGE); 
                    }
                    else if(Float.parseFloat(textQuantidade.getText()) <= 0 )
                    {
                        JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que 0!", "Erro", JOptionPane.PLAIN_MESSAGE); 
                    }
                    else if(textprecoUnitario.getText().isBlank())
                    {
                        JOptionPane.showMessageDialog(null, "Digite o valor!", "Erro", JOptionPane.PLAIN_MESSAGE);
				    }
                    else if(Float.parseFloat(textprecoUnitario.getText()) <= 0 )
                    {
                        JOptionPane.showMessageDialog(null, "Valor deve ser maior que 0!", "Erro", JOptionPane.PLAIN_MESSAGE);
                    }
                else 
                {
					if (!buscouVenda) {
						posicaoRegistro = -1;
					}
					venda.salvar(incluir, posicaoRegistro, textCodVenda.getText(), textNomeCliente.getText(),
					textCodProduto.getText(), descrProduto, textDtVenda.getText(), textQuantidade.getText(),
					textprecoUnitario.getText(), textvlrImpostos.getText());
					textCodVenda.setText("");
					textNomeCliente.setText("");
					textCodProduto.setText("");
					textDescrProduto.setText("");
					textDtVenda.setText("");
					textQuantidade.setText("");
					textprecoUnitario.setText("");
					textvlrImpostos.setText("");
					textCodVenda.setEnabled(true);
					textCodVenda.requestFocus();
					incluir = true;
					buscouVenda = false;
				}
            }
            catch(Exception erro)
            {

            }
            }
		});
        botaoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textCodVenda.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Digite o codigo da venda!", "Erro", JOptionPane.PLAIN_MESSAGE);
				}
                else if(textNomeCliente.getText().isBlank())
                {
                    JOptionPane.showMessageDialog(null, "Digite o nome do cliente!", "Erro", JOptionPane.PLAIN_MESSAGE);
                }
                else if(textCodProduto.getText().isBlank())
                {
                    JOptionPane.showMessageDialog(null, "Digite o codigo do produto!", "Erro", JOptionPane.PLAIN_MESSAGE);
                }
                else if(Float.parseFloat(textQuantidade.getText()) <= 0)
                {
                   JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que 0!", "Erro", JOptionPane.PLAIN_MESSAGE); 
                }
                else if(Float.parseFloat(textprecoUnitario.getText()) <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Valor deve ser maior que 0!", "Erro", JOptionPane.PLAIN_MESSAGE);
                }
                else if(!Venda.validarData(textDtVenda.getText())){

                 }else {
					if (!buscouVenda) {
						posicaoRegistro = -1;
					}
                    String descrProduto = venda.validarCodProduto(textCodProduto.getText());
					venda.editar(incluir, posicaoRegistro, textCodVenda.getText(), textNomeCliente.getText(),
					textCodProduto.getText(), descrProduto, textDtVenda.getText(), textQuantidade.getText(),
					textprecoUnitario.getText(), textvlrImpostos.getText());
					textCodVenda.setText("");
					textNomeCliente.setText("");
					textCodProduto.setText("");
					textDescrProduto.setText("");
					textDtVenda.setText("");
					textQuantidade.setText("");
					textprecoUnitario.setText("");
					textvlrImpostos.setText("");
					textCodVenda.setEnabled(true);
					textCodVenda.requestFocus();
					incluir = true;
					buscouVenda = false;
				}
			}
		});
		

        botaoConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                venda.consultar();
            }
        });

        botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textCodVenda.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Codigo da venda nao digitado!", "Erro",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    if (!buscouVenda) {
                        JOptionPane.showMessageDialog(null, "Buscar a venda antes de clicar em excluir!", "Erro",
                                JOptionPane.PLAIN_MESSAGE);
                    } else {
                        venda.desativarVenda(posicaoRegistro);
                        JOptionPane.showMessageDialog(null, "Venda excluida!", "", JOptionPane.PLAIN_MESSAGE);

                        textCodVenda.setText("");
                        textNomeCliente.setText("");
                        textCodProduto.setText("");
                        textDescrProduto.setText("");
                        textDtVenda.setText("");
                        textQuantidade.setText("");
                        textprecoUnitario.setText("");
                        textvlrImpostos.setText("");
                        textCodVenda.setEnabled(true);
                        textCodVenda.requestFocus();
                        buscouVenda = false;
                    }
                }
            }
        });


        botaoFim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Programa encerrado!!", "FIM", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
        });
    }
}