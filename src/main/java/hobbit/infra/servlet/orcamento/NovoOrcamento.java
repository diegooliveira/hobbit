package hobbit.infra.servlet.orcamento;

import hobbit.model.ServicoOrcamento;
import hobbit.model.pedido.Orcamento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovoOrcamento extends HttpServlet {

    private ServicoOrcamento servicoOrcamento;
    private String detalhesOrcamento;

    public NovoOrcamento(String detalhesOrcamento, ServicoOrcamento servicoOrcamento ) {
        this.detalhesOrcamento = detalhesOrcamento;
        this.servicoOrcamento = servicoOrcamento;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Orcamento orcamento = servicoOrcamento.criarNovo();
        req.setAttribute("orcamento", orcamento);
        resp.sendRedirect(detalhesOrcamento + "?orcamentoId="+orcamento.getId());
    }

    private static final long serialVersionUID = 1L;
}
