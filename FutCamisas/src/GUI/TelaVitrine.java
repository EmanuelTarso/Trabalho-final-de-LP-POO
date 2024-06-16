package GUI;

import classes.Produto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TelaVitrine extends JPanel {

    private MainFrame mainFrame;
    private List<Produto> produtos;

    public TelaVitrine(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.produtos = Produto.lerProdutos();
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Título da tela
        JLabel labelTitulo = new JLabel("Camisas");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setBorder(new EmptyBorder(10, 0, 20, 0)); // Espaçamento ao redor do título

        // Painel para exibir a lista de produtos
        JPanel panelProdutos = new JPanel(new GridLayout(0, 3, 20, 20)); // Grid com 3 colunas, espaçamento de 20 pixels
        panelProdutos.setBorder(new EmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor dos produtos

        // Exibindo produtos na vitrine
        for (Produto produto : produtos) {
            JPanel panelProduto = criarPanelProduto(produto);
            panelProdutos.add(panelProduto);
        }

        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botão para ir para a tela do carrinho
        JButton botaoIrCarrinho = new JButton("Ir para o Carrinho");
        botaoIrCarrinho.addActionListener(e -> mainFrame.mostrarTelaCarrinho());

        // Adicionar componentes à tela
        add(labelTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botaoIrCarrinho, BorderLayout.SOUTH);
    }

    private JPanel criarPanelProduto(Produto produto) {
        JPanel panelProduto = new JPanel(new BorderLayout());
        panelProduto.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Carregar e redimensionar imagem do produto
        ImageIcon imagemProduto = carregarImagemRedimensionada(produto.getCaminhoImagem(), 150, 150);
        JLabel labelImagem = new JLabel(imagemProduto);
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);

        // Nome do produto
        JLabel labelNome = new JLabel(produto.getNome());
        labelNome.setFont(new Font("Arial", Font.BOLD, 16));
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);

        // Preço do produto
        JLabel labelPreco = new JLabel("R$" + String.format("%.2f", produto.getPreco()));
        labelPreco.setHorizontalAlignment(SwingConstants.CENTER);

        // Combo box para selecionar o tamanho
        JComboBox<String> comboBoxTamanho = new JComboBox<>(new String[]{"P", "M", "G"});
        comboBoxTamanho.setSelectedIndex(0); // Seleciona o tamanho padrão

        // Botão para adicionar ao carrinho
        JButton botaoAdicionar = new JButton("Adicionar ao Carrinho");
        botaoAdicionar.addActionListener(e -> {
            String tamanhoSelecionado = (String) comboBoxTamanho.getSelectedItem();
            mainFrame.getCarrinho().adicionarItem(produto, 1, tamanhoSelecionado);
            JOptionPane.showMessageDialog(this, "Camisa adicionada ao carrinho!");
        });

        // Painel para nome, preço, tamanho e botão
        JPanel panelDetalhes = new JPanel(new GridLayout(4, 1));
        panelDetalhes.add(labelNome);
        panelDetalhes.add(labelPreco);
        panelDetalhes.add(comboBoxTamanho);
        panelDetalhes.add(botaoAdicionar);

        // Adicionar componentes ao painel do produto
        panelProduto.add(labelImagem, BorderLayout.CENTER);
        panelProduto.add(panelDetalhes, BorderLayout.SOUTH);

        return panelProduto;
    }

    // Método para carregar e redimensionar a imagem
    private ImageIcon carregarImagemRedimensionada(String caminhoImagem, int largura, int altura) {
        ImageIcon imagemOriginal = new ImageIcon(caminhoImagem);
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }
}
