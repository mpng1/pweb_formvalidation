package br.edu.ifal.pweb_formvalidation.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prematricula")
public class PreMatriculaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	StringBuilder dadosAluno = new StringBuilder();
    	
    	StringBuilder errorMessages = new StringBuilder();
                
    	String nomeAluno = request.getParameter("nome");
        String nascimento = request.getParameter("nascimento");
        String nomeMae = request.getParameter("nomemae");
        String nomePai = request.getParameter("nomepai");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String serie = request.getParameter("serie");
        String turno = request.getParameter("turno");
        String[] extracurriculars = request.getParameterValues("extracurriculars");
        
        dadosAluno.append("Nome do Aluno: ").append(nomeAluno).append("<br>");
        dadosAluno.append("Nascimento: ").append(nascimento).append("<br>");
        dadosAluno.append("Nome da mãe: ").append(nomeMae).append("<br>");
        dadosAluno.append("Nome do pai: ").append(nomePai).append("<br>");
        dadosAluno.append("Telefone: ").append(telefone).append("<br>");
        dadosAluno.append("Email: ").append(email).append("<br>");
        dadosAluno.append("Série: ").append(serie).append("<br>");
        dadosAluno.append("Turno: ").append(turno).append("<br>");
        if (extracurriculars != null && extracurriculars.length > 0) {
            dadosAluno.append("Atividades Extracurriculares: ");
            for (String extracurricular : extracurriculars) {
                dadosAluno.append(extracurricular).append(", ");
            }
           
            dadosAluno.setLength(dadosAluno.length() - 2);
            dadosAluno.append("<br>");
        }
        
        
        if (nomeAluno == null || nomeAluno.isEmpty()) {
            errorMessages.append("- Nome do Aluno inválido.<br>");
        }
        if (nascimento == null || !isValidDate(nascimento)) {
            errorMessages.append("- Data de nascimento inválida.<br>");
        }
        if (nomeMae == null || nomeMae.isEmpty()) {
            errorMessages.append("- Nome da mãe inválido.<br>");
        }
        if (nomePai == null || nomePai.isEmpty()) {
            errorMessages.append("- Nome do pai inválido. <br>");
        }
        if (telefone == null || !isValidPhoneNumber(telefone)) {
            errorMessages.append("- Telefone inválido.<br>");
        }
        if (email == null || !isValidEmail(email)) {
            errorMessages.append("- Email inválido.<br>");
        }
        if (turno == null || turno.isEmpty()) {
            errorMessages.append("- Escolha um turno.<br>");
        }
        if (extracurriculars != null && extracurriculars.length > 3) {
            errorMessages.append("- Você pode escolher no máximo 3 atividades extracurriculares.<br>");
        }
        
        if (errorMessages.length() > 0) {
            
        	request.setCharacterEncoding("UTF-8");
        	request.setAttribute("errorMessages", errorMessages.toString());
            
            request.getRequestDispatcher("/prematriculaerro").forward(request, response);
            return;
        }
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("dadosAluno", dadosAluno.toString());
        
        request.getRequestDispatcher("/prematriculasucesso").forward(request, response);
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String cleanedNumber = phoneNumber.replaceAll("[^0-9]", "");

        String[] acceptedFirstDigits = {
            "61", "62", "64", "65", "66", "67", "82", "71", "73", "74", "75", "77",
            "85", "88", "98", "99", "83", "81", "87", "86", "89", "84", "79", "68",
            "96", "92", "97", "91", "93", "94", "69", "95", "63", "27", "28", "31",
            "32", "33", "37", "35", "38", "21", "22", "24", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "41", "42", "43", "44", "45", "46", "51",
            "53", "54", "55", "47", "48", "49"
        };

        for (String acceptedPrefix : acceptedFirstDigits) {
            if (cleanedNumber.startsWith(acceptedPrefix)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        if (!email.contains("@") || !email.contains(".")) {
            return false; 
        }
                
        return true;
    }
	
}
