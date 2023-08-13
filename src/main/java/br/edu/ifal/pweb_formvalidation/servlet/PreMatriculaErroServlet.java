package br.edu.ifal.pweb_formvalidation.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prematriculaerro")
public class PreMatriculaErroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("Erro no cadastro do aluno.");

        String errorMessages = (String) request.getAttribute("errorMessages");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Não foi possível realizar a pré-matrícula.</h2>");
        out.println("<h3>Verifique no seu formulário:</h3>");
        out.println("<p>" + errorMessages + "</p>");
        out.println("</body></html>");

        out.close();
    }
}