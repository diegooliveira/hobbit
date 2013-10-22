package hobbit.infra.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Template {

    public void preencher(String template, HttpServletRequest req, HttpServletResponse resp,
            ServletContext servletContext) throws IOException;
}
