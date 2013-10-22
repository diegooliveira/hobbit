package hobbit.infra.servlet.orcamento;

import hobbit.infra.servlet.Template;
import hobbit.model.ServicoOrcamento;
import hobbit.model.pedido.Orcamento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetalhesOrcamento extends HttpServlet {

    private Template template;
    private ServicoOrcamento servicoOrcamento;

    public DetalhesOrcamento(Template template, ServicoOrcamento servicoOrcamento) {
        this.template = template;
        this.servicoOrcamento = servicoOrcamento;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int orcamentoId = Integer.parseInt(req.getParameter("orcamentoId"));
        Orcamento orcamento = servicoOrcamento.encontrar(orcamentoId);
        req.setAttribute("orcamento", orcamento);

        template.preencher("orcamento", req, resp, getServletContext());
    }

    private static final long serialVersionUID = 1L;

}
