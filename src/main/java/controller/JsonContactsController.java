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
        // Recupera los datos del formulario
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String photoString = request.getParameter("photo");

        // Crea un nuevo objeto JsonContacts con los datos del formulario
        JsonContacts contact = new JsonContacts(name, email, phone, photoString, UUID.randomUUID().toString());

        // Ruta del archivo JSON
        String filePath = "C:\\Users\\Jaco\\Documents\\NetBeansProjects\\files\\src\\main\\webapp\\assets\\json\\contactos.json";
        // 
        File file = new File(filePath);
        // definimos una lista llamada contacts
        List<JsonContacts> contacts = JsonContacts.readJson(filePath);
        // aniadiendo el nuevo contacto
        contacts.add(contact);
        
        //escribe el archivo usando la importacion bufferedwriter y pasando como
        // parametro el file que contiene la ruta
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // escribe el inicio del json como una lista o array
            writer.write("{\"contacts\":[");
            // recorre uno por uno los contactos
            for (int i = 0; i < contacts.size(); i++) {
                contacts.get(i).writeJson(writer);
                if (i < contacts.size() - 1) {
                    writer.write(",");
                }
            }
            // escribe el final del json
            writer.write("]}");
        } catch (IOException e) {
            // si no funciona el try, manda un error de excepcion
            throw new ServletException("Error al escribir el archivo", e);
        }

        // Redirige al usuario a la página de jsons
        request.setAttribute("contacts", contacts);
        response.sendRedirect("json-txt.jsp");
    }

}
