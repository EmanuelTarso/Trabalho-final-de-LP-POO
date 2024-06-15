package GUI;

import classes.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaLogin extends JPanel {
    private MainFrame mainFrame;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaLogin(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel labelUsuario = new JLabel("Email:");
        campoUsuario = new JTextField(20);
        JLabel labelSenha = new JLabel("CPF:");
        campoSenha = new JPasswordField(20);
        JButton botaoLogin = new JButton("Login");

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelUsuario, gbc);

        gbc.gridx = 1;
        add(campoUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelSenha, gbc);

        gbc.gridx = 1;
        add(campoSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(botaoLogin, gbc);
    }

    private void handleLogin() {
        String email = campoUsuario.getText();
        String cpf = new String(campoSenha.getPassword());

        // Verificar se o cliente existe no banco de dados
        List<Cliente> clientes = Cliente.imprimeClientes();
        boolean loginSucesso = false;
        Cliente clienteLogado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.getCpf().equals(cpf)) {
                loginSucesso = true;
                clienteLogado = cliente;    
                break;
            }
        }

        if (loginSucesso) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            mainFrame.mostrarTelaVitrine(); // Mostrar a tela de vitrine após o login
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos. Tente novamente.");
            campoUsuario.setText("");
            campoSenha.setText("");
        }
    }
}
