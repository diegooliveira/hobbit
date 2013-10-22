package hobbit.model.pedido;

import java.util.Date;

public class Orcamento {

    private final int id;
    private final Date dataCriacao;

    public static Orcamento criar(int id) {
        return new Orcamento(id, new Date());
    }

    public static Orcamento recarregar(int id, Date date) {
        return new Orcamento(id, date);
    }

    private Orcamento(int id, Date dataCriacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public String toString() {
        return "Orcamento [id=" + id + ", dataCriacao=" + dataCriacao + "]";
    }
}
