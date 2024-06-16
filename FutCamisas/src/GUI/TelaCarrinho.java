package GUI;

import classes.Carrinho;
import classes.ItensPedido;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCarrinho extends JPanel {

    private MainFrame mainFrame;
    private Carrinho carrinho;
    private JPanel panelItens; // Painel para exibir os itens do carrinho

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

        // Painel para exibir os itens do carrinho
        panelItens = new JPanel();
        panelItens.setLayout(new BoxLayout(panelItens, BoxLayout.Y_AXIS)); // Layout vertical
        panelItens.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno

        atualizarTelaCarrinho(); // Atualiza os itens do carrinho inicialmente

        // Botão para voltar para a vitrine
        JButton botaoVoltar = new JButton("Voltar para Vitrine");
        botaoVoltar.addActionListener(e -> mainFrame.mostrarTelaVitrine());

        // Botão para ir para o pagamento
        JButton botaoIrPagamento = new JButton("Ir para Pagamento");
        botaoIrPagamento.addActionListener(e -> mainFrame.mostrarTelaPagamento());

        // Painel para os botões de ação
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
        panelBotoes.add(botaoVoltar);
        panelBotoes.add(botaoIrPagamento);

        // Adicionar componentes à tela
        add(labelTitulo, BorderLayout.NORTH);
        add(new JScrollPane(panelItens), BorderLayout.CENTER); // Usar JScrollPane para rolar os itens se necessário
        add(panelBotoes, BorderLayout.SOUTH);
    }

    public void atualizarTelaCarrinho() {
        panelItens.removeAll(); // Remove todos os itens do painel

        List<ItensPedido> itens = carrinho.getItens();

        for (ItensPedido item : itens) {
            JPanel panelItem = new JPanel(new BorderLayout());
            panelItem.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Imagem do produto
            ImageIcon imagemProduto = carregarImagem(item.getProduto().getCaminhoImagem());
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

            // Botão para remover uma unidade do item do carrinho
            JButton botaoRemoverItem = new JButton("Remover");
            botaoRemoverItem.addActionListener(e -> {
                if (item.getQuantidade() > 1) {
                    carrinho.removerUmaUnidadeItem(item); // Remove uma unidade do item do carrinho
                } else {
                    carrinho.removerItem(item); // Se for a última unidade, remove o item inteiro
                }
                atualizarTelaCarrinho(); // Atualiza a tela do carrinho após a remoção
            });
            panelItem.add(botaoRemoverItem, BorderLayout.EAST);

            panelItens.add(panelItem);
        }

        revalidate(); // Atualiza o layout do painel
        repaint(); // Redesenha o painel
    }

    private ImageIcon carregarImagem(String caminho) {
        ImageIcon imagem = new ImageIcon(caminho);
        Image img = imagem.getImage();
        Image novaImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(novaImg);
    }
}
