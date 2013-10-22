package hobbit.infra.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

@Controller
public class HomeHandler extends HttpServlet {

    private Template template;

    public HomeHandler(Template template) {
        this.template = template;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        template.preencher("home", req, resp, getServletContext());
    }

    private static final long serialVersionUID = 1L;

}
