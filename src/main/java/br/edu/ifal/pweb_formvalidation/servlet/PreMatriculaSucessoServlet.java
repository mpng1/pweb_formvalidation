package br.edu.ifal.pweb_formvalidation.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prematriculasucesso")
public class PreMatriculaSucessoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Pré matriculando Aluno");
        
        String dadosAluno = (String) request.getAttribute("dadosAluno");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Pré-matrícula concluída!</h2>");
        out.println("<h3>Segue comprovante:</h3>");
        out.println("<p>" + dadosAluno + "</p>");
        out.println("</body></html>");

        out.close();
        
    }
    
}