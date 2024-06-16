package GUI;

import classes.Carrinho;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelPrincipal;
    private TelaLogin telaLogin;
    private TelaVitrine telaVitrine;
    private TelaCarrinho telaCarrinho;
    private TelaPagamento telaPagamento;
    private Carrinho carrinho;

    public MainFrame() {
        setTitle("FutCamisas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("imagens/Logo_FutCamisas.png");
        setIconImage(icon.getImage());

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        carrinho = new Carrinho();
        telaLogin = new TelaLogin(this);
        telaVitrine = new TelaVitrine(this);
        telaCarrinho = new TelaCarrinho(this, carrinho);
        telaPagamento = new TelaPagamento(this, carrinho);

        // Adiciona as telas ao painel principal usando um CardLayout
        panelPrincipal.add(telaLogin, "login");
        panelPrincipal.add(telaVitrine, "vitrine");
        panelPrincipal.add(telaCarrinho, "carrinho");
        panelPrincipal.add(telaPagamento, "pagamento");

        // Define a tela inicial como a tela de login
        cardLayout.show(panelPrincipal, "login");

        // Adiciona o painel principal ao JFrame
        add(panelPrincipal);

        setVisible(true);
    }

    public void mostrarTelaVitrine() {
        cardLayout.show(panelPrincipal, "vitrine");
    }

    public void mostrarTelaCarrinho() {
        telaCarrinho.atualizarTelaCarrinho();
        cardLayout.show(panelPrincipal, "carrinho");
    }

    public void mostrarTelaPagamento() {
        telaPagamento.atualizarTelaPagamento();
        cardLayout.show(panelPrincipal, "pagamento");
    }

    public TelaCarrinho getTelaCarrinho() {
        return telaCarrinho;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
