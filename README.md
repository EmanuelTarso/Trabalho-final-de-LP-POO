# Sistema de vendas FutCamisas 👕


## Requisitos

- Java 8 ou superior
- IDE de sua preferência (Eclipse, IntelliJ, NetBeans, etc.)

---

## Dependências Necessárias

- Biblioteca Swing para interfaces gráficas (já incluída no JDK)
- Classes customizadas (Cliente, Carrinho, ItensPedido, Pagamento, Produto)

---

## Arquitetura

O projeto é estruturado em diferentes pacotes e classes, cada um responsável por uma funcionalidade específica do sistema. As principais telas e classes são:

- **MainFrame**: A classe principal que gerencia as diferentes telas do aplicativo.
- **TelaLogin**: Tela de login do usuário.
- **TelaVitrine**: Tela de exibição dos produtos disponíveis.
- **TelaCarrinho**: Tela de exibição dos itens no carrinho de compras.
- **TelaPagamento**: Tela de pagamento, onde o usuário pode finalizar sua compra.
- **Classes de negócio**: `Cliente`, `Carrinho`, `ItensPedido`, `Pagamento`, `Produto` - responsáveis pela lógica de negócio do sistema.

### Diagrama de Classes

<div>
<img align="center" alt="Diagrama de Classes" src="https:https://github.com/EmanuelTarso/Trabalho-final-de-LP-POO/blob/main/Img/Diagrama_FutCamisas.png?raw=true"/>
</div>
---

## Como Utilizar

1. **Configuração do Ambiente:**
   - Certifique-se de ter o JDK 8 ou superior instalado.
   - Abra o projeto em sua IDE de preferência.

2. **Execução do Projeto:**
   - Navegue até a classe `MainFrame` e execute o método `main`.

3. **Navegação no Sistema:**
   - **Tela de Login:**
     - Insira o e-mail e senha de um cliente cadastrado para acessar o sistema.
     - Clique no botão "Login" para autenticar.
   - **Tela de Vitrine:**
     - Visualize os produtos disponíveis.
     - Adicione produtos ao carrinho conforme desejado.
   - **Tela de Carrinho:**
     - Visualize os itens adicionados ao carrinho.
     - Altere quantidades ou remova itens conforme necessário.
     - Clique em "Finalizar Compra" para ir para a tela de pagamento.
   - **Tela de Pagamento:**
     - Escolha a forma de pagamento (Pix, Cartão de Crédito, Cartão de Débito, Boleto).
     - Se a opção for cartão, preencha os dados do cartão (Número do Cartão, Validade, CVV).
     - Clique em "Finalizar Pagamento" para concluir a compra.

---

## Imagens do Projeto

1. **Tela de Login**
<img align="center" alt="Tela Login" src="https:https://github.com/EmanuelTarso/Trabalho-final-de-LP-POO/blob/main/Img/TelaLogin_FutCamisas.png?raw=true"/>

2. **Tela de Vitrine**
<img align="center" alt="Tela Vitrine" src="https:https://github.com/EmanuelTarso/Trabalho-final-de-LP-POO/blob/main/Img/TelaVitrine_FutCamisas.png?raw=true"/>

3. **Tela de Carrinho**
<img src="imagens/TelaCarrinho_FutCamisas.png" alt="Tela de Carrinho" width="600"/>

4. **Telas de Pagamento**
<img src="https://github.com/EmanuelTarso/Trabalho-final-de-LP-POO/blob/main/Img/TelaPagamento_FutCamisas.png" alt="Tela de Pagamento" width="600"/>
