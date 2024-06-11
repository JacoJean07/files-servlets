package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.List;
import model.JsonContacts;
import java.util.UUID;


public class JsonContactsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String photoString = request.getParameter("photo");

        JsonContacts contact = new JsonContacts(name, email, phone, photoString, UUID.randomUUID().toString());

        String filePath = "C:\\Users\\Jaco\\Documents\\NetBeansProjects\\files\\src\\main\\webapp\\assets\\json\\contactos.json";

        File file = new File(filePath);

        List<JsonContacts> contacts = JsonContacts.readJson(filePath);

        contacts.add(contact);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("{\"contacts\":[");
            for (int i = 0; i < contacts.size(); i++) {
                contacts.get(i).writeJson(writer);
                if (i < contacts.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]}");
        } catch (IOException e) {
            throw new ServletException("Error al escribir el archivo", e);
        }

        request.setAttribute("contacts", contacts);
        response.sendRedirect("json-txt.jsp");
    }

}
