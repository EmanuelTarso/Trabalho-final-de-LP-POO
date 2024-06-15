package GUI;

import classes.Carrinho;
import classes.ItensPedido;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaCarrinho extends JPanel {

    private MainFrame mainFrame;
    private Carrinho carrinho;

    private JPanel panelItens;
    private JLabel labelTotal;

    public TelaCarrinho(MainFrame mainFrame, Carrinho carrinho) {
        this.mainFrame = mainFrame;
        this.carrinho = carrinho;

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Título da tela
        JLabel labelTitulo = new JLabel("Carrinho de Compras");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setBorder(new EmptyBorder(10, 0, 20, 0)); // Espaçamento ao redor do título

        // Painel para exibir os itens do carrinho
        panelItens = new JPanel();
        panelItens.setLayout(new BoxLayout(panelItens, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelItens);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botão para ir para a tela de pagamento
        JButton botaoPagamento = new JButton("Ir para Pagamento");
        botaoPagamento.addActionListener(e -> mainFrame.mostrarTelaPagamento());

        // Painel para o botão de pagamento
        JPanel panelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotao.add(botaoPagamento);

        // Painel para exibir o valor total
        labelTotal = new JLabel("Valor Total: R$ " + String.format("%.2f", carrinho.calcularValorTotal()));
        labelTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel panelValorTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelValorTotal.add(labelTotal);

        // Adicionar componentes à tela
        add(labelTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotao, BorderLayout.SOUTH);
        add(panelValorTotal, BorderLayout.EAST);

        atualizarTelaCarrinho(); // Atualiza a exibição do carrinho inicialmente
    }

    public void atualizarTelaCarrinho() {
        panelItens.removeAll();

        List<ItensPedido> itens = carrinho.getItens();

        for (ItensPedido item : itens) {
            JPanel panelItem = new JPanel(new BorderLayout());
            panelItem.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Imagem do produto
            ImageIcon imagemProduto = carregarImagemRedimensionada(item.getProduto().getCaminhoImagem(), 100, 100);
            JLabel labelImagem = new JLabel(imagemProduto);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            panelItem.add(labelImagem, BorderLayout.WEST);

            // Detalhes do produto
            JLabel labelNome = new JLabel(item.getProduto().getNome());
            JLabel labelQuantidade = new JLabel("Quantidade: " + item.getQuantidade());
            JLabel labelTamanho = new JLabel("Tamanho: " + item.getTamanho());
            JLabel labelValorTotal = new JLabel("Valor Total: R$" + String.format("%.2f", item.getValorTotal()));

            JPanel panelDetalhes = new JPanel(new GridLayout(4, 1));
            panelDetalhes.add(labelNome);
            panelDetalhes.add(labelQuantidade);
            panelDetalhes.add(labelTamanho);
            panelDetalhes.add(labelValorTotal);
            panelItem.add(panelDetalhes, BorderLayout.CENTER);

            panelItens.add(panelItem);
        }

        // Atualiza o valor total
        labelTotal.setText("Valor Total: R$ " + String.format("%.2f", carrinho.calcularValorTotal()));

        // Atualiza a interface
        revalidate();
        repaint();
    }

    // Método para carregar e redimensionar a imagem
    private ImageIcon carregarImagemRedimensionada(String caminhoImagem, int largura, int altura) {
        ImageIcon imagemOriginal = new ImageIcon(caminhoImagem);
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }
}
