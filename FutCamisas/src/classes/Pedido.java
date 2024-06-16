package classes;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private Vendedor vendedor;
    private List<ItensPedido> itens;
    private Date data;
    private double total;

    // Construtor
    public Pedido(int numeroPedido, Cliente cliente, Vendedor vendedor, List<ItensPedido> itens, Date data, double total) {
    this.numeroPedido = numeroPedido;
    this.cliente = cliente;
    this.vendedor = vendedor;
    this.itens = itens;
    this.data = data;
    this.total = total;
    }

    // Getters e Setters
    public int getNumeroPedido(){
        return numeroPedido;
    }
    public void setNumeroPedido(int numeroPedido){
        this.numeroPedido = numeroPedido;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    private double calcularTotal() {
        double total = 0;
        for (ItensPedido item : itens) {
            total += item.getValorTotal();
        }   
        return total;
    }
}
