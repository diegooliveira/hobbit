package hobbit.infra.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafTemplate implements Template {
    
    private TemplateEngine engine;

    public ThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        
        engine = new TemplateEngine();
        engine.setCacheManager(null);
        engine.addDialect(new LayoutDialect());
        engine.setTemplateResolver(templateResolver);
    }

    public void preencher(String template, HttpServletRequest req, HttpServletResponse resp,
            ServletContext servletContext) throws IOException {
        
        WebContext context = new WebContext(req, resp, servletContext, req.getLocale());
        engine.process(template, context, resp.getWriter());
    }

}
