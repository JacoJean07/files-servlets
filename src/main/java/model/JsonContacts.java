package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonContacts {
    // definimos los atributos de la clase
    private String name;
    private String email;
    private String phone;
    private String fotoString;
    private String id;

    // definimos nuestro constructor con parametros
    public JsonContacts(String name, String email, String phone, String fotoString, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fotoString = fotoString;
        this.id = id;
    }

    //definimos nuestro constructor sin parametros
    public JsonContacts() {
    }

    //metodos getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFotoString() {
        return fotoString;
    }

    public String getId() {
        return id;
    }

    // metodos setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }

    public void setId(String id) {
        this.id = id;
    }

    // metodo para escribir nuestro contacto con una estructura clave = valor dentro del json
    public void writeJson(BufferedWriter writer) throws IOException {
        writer.write("{\n");
        writer.write("\t\"id\": \"" + id + "\",\n");
        writer.write("\t\"name\": \"" + name + "\",\n");
        writer.write("\t\"email\": \"" + email + "\",\n");
        writer.write("\t\"phone\": \"" + phone + "\",\n");
        writer.write("\t\"fotoString\": \"" + fotoString + "\"\n");
        writer.write("}\n");
    }

    // Leer el archivo JSON y devolver una lista de objetos JsonContacts
    public static List<JsonContacts> readJson(String path) {
        // Crear una lista para almacenar los objetos JsonContacts
        List<JsonContacts> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            // Leer el contenido del archivo JSON y parsearlo en una string
            String line;
            StringBuilder jsonContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            // Parsear el contenido del archivo JSON en una lista de objetos JsonContacts
            String jsonString = jsonContent.toString();
            jsonString = jsonString.substring(1, jsonString.length() - 1); // Eliminar la cabecera y la cola de la lista
            String[] items = jsonString.split("\\},\\{");
            for (String item : items) {
                item = item.replace("{", "").replace("}", "").replace("\"", "");
                String[] attributes = item.split(",");
                JsonContacts contact = new JsonContacts();
                for (String attribute : attributes) {
                    String[] keyValue = attribute.split(":");
                    switch (keyValue[0].trim()) {
                        // case sera igual a la clave
                        case "id":
                            // pasa metodo para sobrescribir el valor
                            // almacenado en keyvalue y usa el metodo trim
                            // para eliminar espacios en blanco al inicio y fin
                            contact.setId(keyValue[1].trim());
                            break;
                        case "name":
                            contact.setName(keyValue[1].trim());
                            break;
                        case "email":
                            contact.setEmail(keyValue[1].trim());
                            break;
                        case "phone":
                            contact.setPhone(keyValue[1].trim());
                            break;
                        case "fotoString":
                            // recorre la url letra por letra hasta el final y elimina los dos puntos
                            // para luego volverlos a poner
                            StringBuilder fotoValue = new StringBuilder();
                            for (int i = 1; i < keyValue.length; i++) {
                                fotoValue.append(keyValue[i]);
                                if (i < keyValue.length - 1) {
                                    fotoValue.append(":"); // Agregar los dos puntos como parte del valor
                                }
                            }
                            contact.setFotoString(fotoValue.toString().trim());
                            break;
                    }
                }
                contacts.add(contact);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
        return contacts;
    }
}
