package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;
import model.JsonContacts;
import java.util.UUID;

@MultipartConfig
public class JsonContactsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera los datos del formulario
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Part photoPart = request.getPart("photo");
        //definir photoString como null
        String photoString = null;

        // Crea un nuevo objeto JsonContacts con los datos del formulario
        JsonContacts contact = new JsonContacts(name, email, phone, photoString, UUID.randomUUID().toString());

        // Ruta del archivo JSON
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

        // Redirige al usuario a la página de éxito
        request.setAttribute("contacts", contacts);
        response.sendRedirect("json-txt.jsp");
    }

    // Método auxiliar para obtener el nombre del archivo de una parte de la petición
    private String getFileName(Part part) throws IOException {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "").replace(" ", "");
            }
        }
        throw new IOException("No se ha encontrado el nombre del archivo");
    }
}
