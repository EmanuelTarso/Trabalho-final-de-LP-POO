package GUI;

import classes.Carrinho;
import classes.ItensPedido;
import classes.Pagamento;
import classes.Produto;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class TelaPagamento extends JPanel {

    private MainFrame mainFrame;
    private Carrinho carrinho;
    private JLabel labelTotal;
    private JComboBox<String> comboBoxFormaPagamento;
    private JFormattedTextField textFieldNumeroCartao; // Alterado para JFormattedTextField
    private JFormattedTextField textFieldValidade;
    private JFormattedTextField textFieldCVV; // Alterado para JFormattedTextField
    private JPanel panelDadosCartao;

    public TelaPagamento(MainFrame mainFrame, Carrinho carrinho) {
        this.mainFrame = mainFrame;
        this.carrinho = carrinho;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Título da tela
        JLabel labelTitulo = new JLabel("Tela de Pagamento");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Painel para exibir os itens do carrinho
        JPanel panelItens = new JPanel();
        panelItens.setLayout(new BoxLayout(panelItens, BoxLayout.Y_AXIS)); // Layout vertical
        panelItens.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno

        // Exibindo itens do carrinho
        List<ItensPedido> itens = carrinho.getItens();
        for (ItensPedido item : itens) {
            JPanel panelItem = criarPanelItem(item);
            panelItens.add(panelItem);
            panelItens.add(Box.createVerticalStrut(10)); // Espaçamento entre os itens
        }

        // Total do carrinho
        labelTotal = new JLabel("Total: R$" + String.format("%.2f", carrinho.calcularValorTotal()));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18));
        labelTotal.setHorizontalAlignment(SwingConstants.CENTER);

        // Forma de pagamento
        JPanel panelFormaPagamento = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Painel para centralizar o combobox
        JLabel labelFormaPagamento = new JLabel("Forma de Pagamento:");
        comboBoxFormaPagamento = new JComboBox<>(new String[]{"Pix", "Cartão de Crédito", "Cartão de Débito", "Boleto"});
        comboBoxFormaPagamento.setSelectedIndex(0); // Seleciona o Pix como padrão
        panelFormaPagamento.add(labelFormaPagamento);
        panelFormaPagamento.add(comboBoxFormaPagamento);

        // Painel para os dados do cartão
        panelDadosCartao = new JPanel(new GridLayout(3, 2, 10, 10));
        panelDadosCartao.setBorder(BorderFactory.createTitledBorder("Dados do Cartão"));

        JLabel labelNumeroCartao = new JLabel("Número do Cartão:");
        try {
            MaskFormatter mascaraCartao = new MaskFormatter("#### #### #### ####");
            mascaraCartao.setPlaceholderCharacter('_'); // Caractere para preenchimento automático
            textFieldNumeroCartao = new JFormattedTextField(mascaraCartao);
        } catch (ParseException e) {
            e.printStackTrace();
            textFieldNumeroCartao = new JFormattedTextField();
        }

        JLabel labelValidade = new JLabel("Validade (MM/AA):");
        try {
            MaskFormatter mascaraValidade = new MaskFormatter("##/##");
            mascaraValidade.setPlaceholderCharacter('_'); // Caractere para preenchimento automático
            textFieldValidade = new JFormattedTextField(mascaraValidade);
        } catch (ParseException e) {
            e.printStackTrace();
            textFieldValidade = new JFormattedTextField();
        }

        JLabel labelCVV = new JLabel("CVV:");
        try {
            MaskFormatter mascaraCVV = new MaskFormatter("###"); // Máscara para 3 dígitos
            mascaraCVV.setPlaceholderCharacter('_'); // Caractere para preenchimento automático
            textFieldCVV = new JFormattedTextField(mascaraCVV);
        } catch (ParseException e) {
            e.printStackTrace();
            textFieldCVV = new JFormattedTextField();
        }

        panelDadosCartao.add(labelNumeroCartao);
        panelDadosCartao.add(textFieldNumeroCartao);
        panelDadosCartao.add(labelValidade);
        panelDadosCartao.add(textFieldValidade);
        panelDadosCartao.add(labelCVV);
        panelDadosCartao.add(textFieldCVV);

        // Esconder campos de dados do cartão inicialmente
        panelDadosCartao.setVisible(false);

        // Listener para selecionar a forma de pagamento
        comboBoxFormaPagamento.addActionListener(e -> {
            String formaSelecionada = (String) comboBoxFormaPagamento.getSelectedItem();
            if (formaSelecionada.equals("Cartão de Crédito") || formaSelecionada.equals("Cartão de Débito")) {
                panelDadosCartao.setVisible(true);
            } else {
                panelDadosCartao.setVisible(false);
            }
            revalidate();
            repaint();
        });

        // Botão para finalizar pagamento
        JButton botaoFinalizarPagamento = new JButton("Finalizar Pagamento");
        botaoFinalizarPagamento.addActionListener(e -> {
            String formaPagamento = (String) comboBoxFormaPagamento.getSelectedItem();
            if (formaPagamento.equals("Cartão de Crédito") || formaPagamento.equals("Cartão de Débito")) {
                String numeroCartao = textFieldNumeroCartao.getText().replaceAll("\\s", ""); // Remover espaços em branco
                String validade = textFieldValidade.getText();
                String cvv = textFieldCVV.getText();
                
                // Verificar se todos os campos do cartão foram preenchidos
                if (numeroCartao.trim().isEmpty() || validade.trim().isEmpty() || cvv.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os dados do cartão para continuar.");
                    return; // Impede o fechamento do diálogo de pagamento
                }
                
                Pagamento pagamento = new Pagamento(numeroCartao, validade, cvv);
                // Aqui você pode realizar a lógica para efetuar o pagamento com os dados do cartão
            }
            
            JOptionPane.showMessageDialog(this, "Pagamento finalizado com " + formaPagamento + " no valor de R$ " +
                    String.format("%.2f", carrinho.calcularValorTotal()));
            mainFrame.mostrarTelaCarrinho(); // Exemplo de navegação para a tela de carrinho após o pagamento
            
            // Fechar a aplicação
            System.exit(0);
        });

        // Botão para voltar para o carrinho
        JButton botaoVoltarCarrinho = new JButton("Voltar para Carrinho");
        botaoVoltarCarrinho.addActionListener(e -> mainFrame.mostrarTelaCarrinho());

        // Painel para os botões de ação
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
        panelBotoes.add(botaoVoltarCarrinho);
        panelBotoes.add(botaoFinalizarPagamento);

        // Painel para o valor total e forma de pagamento
        JPanel panelPagamento = new JPanel(new GridLayout(3, 1, 10, 10));
        panelPagamento.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento em torno do painel
        panelPagamento.add(labelTotal);
        panelPagamento.add(panelFormaPagamento); // Adiciona o painel de forma de pagamento centralizado
        panelPagamento.add(panelDadosCartao); // Adiciona o painel de dados do cartão

        // Painel central para centralizar os elementos
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(panelItens, BorderLayout.CENTER);
        panelCentral.add(panelPagamento, BorderLayout.CENTER); // Adicionando à direita para centralizar

        // Adicionar componentes à tela
        add(labelTitulo, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
    }

    private JPanel criarPanelItem(ItensPedido item) {
        JPanel panelItem = new JPanel(new BorderLayout());
        panelItem.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Nome do produto
        JLabel labelNome = new JLabel(item.getProduto().getNome());
        labelNome.setFont(new Font("Arial", Font.BOLD, 16));
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);

        // Preço e quantidade do produto
        JLabel labelDetalhes = new JLabel("R$" + String.format("%.2f", item.getProduto().getPreco()) + " x " + item.getQuantidade());
        labelDetalhes.setHorizontalAlignment(SwingConstants.CENTER);


        // Adicionar componentes ao painel do item
        panelItem.add(labelNome, BorderLayout.CENTER);
        panelItem.add(labelDetalhes, BorderLayout.EAST);

        return panelItem;
    }

    public void atualizarTelaPagamento() {
        labelTotal.setText("Total: R$" + String.format("%.2f", carrinho.calcularValorTotal()));
        revalidate();
        repaint();
    }

    // Método para validar se todos os campos do cartão estão preenchidos
    private boolean camposCartaoPreenchidos() {
        String numeroCartao = textFieldNumeroCartao.getText().replaceAll("\\s", ""); // Remover espaços em branco
        String validade = textFieldValidade.getText();
        String cvv = textFieldCVV.getText();

        return !numeroCartao.trim().isEmpty() && !validade.trim().isEmpty() && !cvv.trim().isEmpty();
    }
}
