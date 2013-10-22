package hobbit.infra.servlet;

import hobbit.model.ServicoOrcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orcamento")
public class OrcamentoControlador {

    private final ServicoOrcamento servicoOrcamento;

    @Autowired
    public OrcamentoControlador(ServicoOrcamento servicoOrcamento) {
        this.servicoOrcamento = servicoOrcamento;
    }

    @RequestMapping("/novo")
    public ModelAndView novo() {
        return new ModelAndView(//
                "orcamento",//
                "orcamento", servicoOrcamento.criarNovo());
    }

    @RequestMapping("/adicionar")
    public ModelAndView adiciona(@RequestParam int orcamentoId, @RequestParam int produtoId) {
        return new ModelAndView(//
                "orcamento", //
                "orcamento", servicoOrcamento.adicionar(orcamentoId, produtoId, 10));
    }

}