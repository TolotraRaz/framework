package framework;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String uri = request.getRequestURI();       // ex: /demo-app/test/aaa
        String context = request.getContextPath();  // ex: /demo-app
        String path = uri.substring(context.length() + "/test/".length()); // "aaa"

        try {
            Class<?> cls = Class.forName("app.controllers." + path + "Controller");
            Object obj = cls.getDeclaredConstructor().newInstance();

            String result = (String) cls.getMethod("execute", HttpServletRequest.class)
                                       .invoke(obj, request);

            response.setContentType("text/html");
            response.getWriter().write(result);

        } catch (ClassNotFoundException e) {
            response.getWriter().write("Page non trouvée pour " + path);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erreur serveur : " + e.getMessage());
        }
    }
}
