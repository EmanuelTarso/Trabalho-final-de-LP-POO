package GUI;

import classes.Carrinho;
import classes.ItensPedido;
import classes.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaPagamento extends JPanel {

    private MainFrame mainFrame;
    private Carrinho carrinho;
    private JLabel labelTotal;
    private JComboBox<String> comboBoxFormaPagamento;

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
        JLabel labelFormaPagamento = new JLabel("Forma de Pagamento:");
        comboBoxFormaPagamento = new JComboBox<>(new String[]{"Pix", "Cartão de Crédito", "Cartão de Débito", "Boleto"});
        comboBoxFormaPagamento.setSelectedIndex(0); // Seleciona o Pix como padrão

        // Botão para finalizar pagamento
        JButton botaoFinalizarPagamento = new JButton("Finalizar Pagamento");
        botaoFinalizarPagamento.addActionListener(e -> {
            String formaPagamento = (String) comboBoxFormaPagamento.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Pagamento finalizado com " + formaPagamento + " no valor de R$ " +
                    String.format("%.2f", carrinho.calcularValorTotal()));
            encerrarAplicacao();
        });

        // Painel para o botão de finalizar pagamento
        JPanel panelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
        panelBotao.add(botaoFinalizarPagamento);

        // Painel para o valor total e forma de pagamento
        JPanel panelPagamento = new JPanel(new GridLayout(3, 1, 10, 10));
        panelPagamento.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento em torno do painel
        panelPagamento.add(labelTotal);
        panelPagamento.add(labelFormaPagamento);
        panelPagamento.add(comboBoxFormaPagamento);

        // Painel central para centralizar os elementos
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(panelItens, BorderLayout.CENTER);
        panelCentral.add(panelPagamento, BorderLayout.CENTER); // Adicionando à direita para centralizar

        // Adicionar componentes à tela
        add(labelTitulo, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelBotao, BorderLayout.SOUTH);
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
    }

    private void encerrarAplicacao() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose(); // Fecha a janela atual
        }
        System.exit(0); // Encerra a aplicação
    }
}
