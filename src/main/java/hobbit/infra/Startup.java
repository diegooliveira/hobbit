package hobbit.infra;

import java.nio.file.Path;
import java.nio.file.Paths;

import hobbit.infra.hsqldb.OrcamentosH2;
import hobbit.infra.servlet.HomeHandler;
import hobbit.infra.servlet.Template;
import hobbit.infra.servlet.ThymeleafTemplate;
import hobbit.infra.servlet.orcamento.DetalhesOrcamento;
import hobbit.infra.servlet.orcamento.NovoOrcamento;
import hobbit.model.ServicoOrcamento;
import hobbit.model.pedido.Orcamentos;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Startup {

    private static final Logger LOGGER = LoggerFactory.getLogger(Startup.class);

    public static void main(String args[]) {
        LOGGER.info("Iniciando sistema");
        try {
            Template processadorTemplate = new ThymeleafTemplate();


            Path path = Paths.get(System.getenv("HOME")).resolve(".hobbitData");
            Orcamentos orcamentos = new OrcamentosH2(path);
            ServicoOrcamento servicoOrcamento = new ServicoOrcamento(orcamentos, null, null, null);
            

            ServletContextHandler servletHandler = new ServletContextHandler();
            servletHandler.addServlet(new ServletHolder(new HomeHandler(processadorTemplate)), "/home");
            servletHandler.addServlet(new ServletHolder(new NovoOrcamento("/orcamento/detalhes", servicoOrcamento)), "/orcamento/novo");
            servletHandler.addServlet(new ServletHolder(new DetalhesOrcamento(processadorTemplate, servicoOrcamento)), "/orcamento/detalhes");

            ResourceHandler rh0 = new ResourceHandler();
            rh0.setBaseResource(Resource.newClassPathResource("/static"));

            ContextHandler context0 = new ContextHandler();
            context0.setContextPath("/");
            context0.setHandler(rh0);

            ContextHandlerCollection contexts = new ContextHandlerCollection();
            contexts.setHandlers(new Handler[] { context0, servletHandler });

            Server server = new Server(8080);
            server.setHandler(contexts);
            server.start();
            server.join();
        } catch (Exception ex) {
            LOGGER.error("Erro iniciando o servidor", ex);
        }
    }
}
