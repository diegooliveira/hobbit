package hobbit.model;

import hobbit.model.pedido.Orcamento;
import hobbit.model.pedido.Orcamentos;
import hobbit.model.pedido.Pedidos;
import hobbit.model.pedido.ProdutoOrcamento;
import hobbit.model.pedido.ProdutosOrcamentos;
import hobbit.model.produto.Produto;
import hobbit.model.produto.Produtos;

public class ServicoOrcamento {

    private Orcamentos orcamentos;
    private Pedidos pedidos;
    private Produtos produtos;
    private ProdutosOrcamentos produtosOrcamentos;

    public ServicoOrcamento(Orcamentos orcamentos, Pedidos pedidos, Produtos produtos,
            ProdutosOrcamentos produtosOrcamentos) {
        this.orcamentos = orcamentos;
        this.pedidos = pedidos;
        this.produtos = produtos;
        this.produtosOrcamentos = produtosOrcamentos;
    }

    public Orcamento criarNovo() {
        Orcamento orcamento = Orcamento.criar(orcamentos.nextId());
        orcamentos.save(orcamento);
        return orcamento;
    }

    public Orcamento adicionar(int orcamentoId, int produtoId, double quantidade) {

        Orcamento orcamento = orcamentos.porId(orcamentoId);

        if (pedidos.contemParaOrcamento(orcamento))
            throw new HobbitException("O orçamento já está fechado. orcamentoId=" + orcamentoId);

        Produto produto = produtos.porId(produtoId);
        ProdutoOrcamento produtoOrcamento = ProdutoOrcamento.criar(produtosOrcamentos.nextId(), produto, quantidade,
                orcamento);
        produtosOrcamentos.salvar(produtoOrcamento);

        return orcamento;
    }

    public Orcamento encontrar(int id) {
        return orcamentos.porId(id);
    }
}
