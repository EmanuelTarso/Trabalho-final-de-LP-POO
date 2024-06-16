package GUI;

import classes.Carrinho;
import classes.ItensPedido;
import classes.Pagamento;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class TelaPagamento extends JPanel {

    private MainFrame mainFrame;
    private Carrinho carrinho;
    private JLabel labelTotal;
    private JComboBox<String> comboBoxFormaPagamento;
    private JFormattedTextField textFieldNumeroCartao;
    private JFormattedTextField textFieldValidade;
    private JFormattedTextField textFieldCVV;
    private JPanel panelDadosCartao;
    private JButton botaoFinalizarPagamento;

    public TelaPagamento(MainFrame mainFrame, Carrinho carrinho) {
        this.mainFrame = mainFrame;
        this.carrinho = carrinho;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("Pagamento");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelItens = new JPanel();
        panelItens.setLayout(new BoxLayout(panelItens, BoxLayout.Y_AXIS));
        panelItens.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<ItensPedido> itens = carrinho.getItens();
        for (ItensPedido item : itens) {
            JPanel panelItem = criarPanelItem(item);
            panelItens.add(panelItem);
            panelItens.add(Box.createVerticalStrut(10));
        }

        labelTotal = new JLabel("Total: R$" + String.format("%.2f", carrinho.calcularValorTotal()));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18));
        labelTotal.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelFormaPagamento = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel labelFormaPagamento = new JLabel("Forma de Pagamento:");
        comboBoxFormaPagamento = new JComboBox<>(new String[]{"Pix", "Cartão de Crédito", "Cartão de Débito", "Boleto"});
        comboBoxFormaPagamento.setSelectedIndex(0);
        panelFormaPagamento.add(labelFormaPagamento);
        panelFormaPagamento.add(comboBoxFormaPagamento);

        panelDadosCartao = new JPanel(new GridLayout(3, 2, 10, 10));
        panelDadosCartao.setBorder(BorderFactory.createTitledBorder("Dados do Cartão"));

        JLabel labelNumeroCartao = new JLabel("Número do Cartão:");
        try {
            MaskFormatter mascaraCartao = new MaskFormatter("#### #### #### ####");
            mascaraCartao.setPlaceholderCharacter('_');
            textFieldNumeroCartao = new JFormattedTextField(mascaraCartao);
        } catch (ParseException e) {
            e.printStackTrace();
            textFieldNumeroCartao = new JFormattedTextField();
        }

        JLabel labelValidade = new JLabel("Validade (MM/AA):");
        try {
            MaskFormatter mascaraValidade = new MaskFormatter("##/##");
            mascaraValidade.setPlaceholderCharacter('_');
            textFieldValidade = new JFormattedTextField(mascaraValidade);
        } catch (ParseException e) {
            e.printStackTrace();
            textFieldValidade = new JFormattedTextField();
        }

        JLabel labelCVV = new JLabel("CVV:");
        try {
            MaskFormatter mascaraCVV = new MaskFormatter("###");
            mascaraCVV.setPlaceholderCharacter('_');
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

        panelDadosCartao.setVisible(false);

        comboBoxFormaPagamento.addActionListener(e -> {
            String formaSelecionada = (String) comboBoxFormaPagamento.getSelectedItem();
            if (formaSelecionada.equals("Cartão de Crédito") || formaSelecionada.equals("Cartão de Débito")) {
                panelDadosCartao.setVisible(true);
                verificarCamposCartao();
            } else {
                panelDadosCartao.setVisible(false);
                botaoFinalizarPagamento.setEnabled(true);
            }
            revalidate();
            repaint();
        });

        botaoFinalizarPagamento = new JButton("Finalizar Pagamento");
        botaoFinalizarPagamento.addActionListener(e -> {
            String formaPagamento = (String) comboBoxFormaPagamento.getSelectedItem();
            if (formaPagamento.equals("Cartão de Crédito") || formaPagamento.equals("Cartão de Débito")) {
                String numeroCartao = textFieldNumeroCartao.getText().replaceAll("\\s", "");
                String validade = textFieldValidade.getText();
                String cvv = textFieldCVV.getText();

                if (numeroCartao.trim().isEmpty() || validade.trim().isEmpty() || cvv.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os dados do cartão para continuar.");
                    return;
                }

                Pagamento pagamento = new Pagamento(numeroCartao, validade, cvv);
            }

            JOptionPane.showMessageDialog(this, "Pagamento finalizado com " + formaPagamento + " no valor de R$ " +
                    String.format("%.2f", carrinho.calcularValorTotal()));
            mainFrame.mostrarTelaCarrinho();

            System.exit(0);
        });

        JButton botaoVoltarCarrinho = new JButton("Voltar para Carrinho");
        botaoVoltarCarrinho.addActionListener(e -> mainFrame.mostrarTelaCarrinho());

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotoes.add(botaoVoltarCarrinho);
        panelBotoes.add(botaoFinalizarPagamento);

        JPanel panelPagamento = new JPanel(new GridLayout(3, 1, 10, 10));
        panelPagamento.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPagamento.add(labelTotal);
        panelPagamento.add(panelFormaPagamento);
        panelPagamento.add(panelDadosCartao);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(panelItens, BorderLayout.CENTER);
        panelCentral.add(panelPagamento, BorderLayout.CENTER);

        add(labelTitulo, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        adicionarDocumentListeners();
    }

    private JPanel criarPanelItem(ItensPedido item) {
        JPanel panelItem = new JPanel(new BorderLayout());
        panelItem.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel labelNome = new JLabel(item.getProduto().getNome());
        labelNome.setFont(new Font("Arial", Font.BOLD, 16));
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelDetalhes = new JLabel("R$" + String.format("%.2f", item.getProduto().getPreco()) + " x " + item.getQuantidade());
        labelDetalhes.setHorizontalAlignment(SwingConstants.CENTER);

        panelItem.add(labelNome, BorderLayout.CENTER);
        panelItem.add(labelDetalhes, BorderLayout.EAST);

        return panelItem;
    }

    public void atualizarTelaPagamento() {
        labelTotal.setText("Total: R$" + String.format("%.2f", carrinho.calcularValorTotal()));
        revalidate();
        repaint();
    }

    private boolean camposCartaoPreenchidos() {
        String numeroCartao = textFieldNumeroCartao.getText().replaceAll("\\s", "").replace("_", "");
        String validade = textFieldValidade.getText().replace("_", "");
        String cvv = textFieldCVV.getText().replace("_", "");

        // Faz com que o botão só seja habilitado caso todos os campos estejam completamente preenchidos
        return numeroCartao.length() == 16 && validade.length() == 5 && cvv.length() == 3;} 

    private void adicionarDocumentListeners() {
        DocumentListener listener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { verificarCamposCartao(); }
            public void removeUpdate(DocumentEvent e) { verificarCamposCartao(); }
            public void changedUpdate(DocumentEvent e) { verificarCamposCartao(); }
        };

        textFieldNumeroCartao.getDocument().addDocumentListener(listener);
        textFieldValidade.getDocument().addDocumentListener(listener);
        textFieldCVV.getDocument().addDocumentListener(listener);
    }

    private void verificarCamposCartao() {
        String formaSelecionada = (String) comboBoxFormaPagamento.getSelectedItem();
        if (formaSelecionada.equals("Cartão de Crédito") || formaSelecionada.equals("Cartão de Débito")) {
            botaoFinalizarPagamento.setEnabled(camposCartaoPreenchidos());
        }
    }
}
