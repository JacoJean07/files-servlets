package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonContacts {
    private String name;
    private String email;
    private String phone;
    private String fotoString;
    private String id;

    public JsonContacts(String name, String email, String phone, String fotoString, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fotoString = fotoString;
        this.id = id;
    }

    public JsonContacts() {}

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getFotoString() { return fotoString; }
    public String getId() { return id; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setFotoString(String fotoString) { this.fotoString = fotoString; }
    public void setId(String id) { this.id = id; }

    public void writeJson(BufferedWriter writer) throws IOException {
        writer.write("{\n");
        writer.write("\t\"id\": \"" + id + "\",\n");
        writer.write("\t\"name\": \"" + name + "\",\n");
        writer.write("\t\"email\": \"" + email + "\",\n");
        writer.write("\t\"phone\": \"" + phone + "\",\n");
        writer.write("\t\"fotoString\": \"" + fotoString + "\"\n");
        writer.write("}\n");
    }

    public static List<JsonContacts> readJson(String path) {
        List<JsonContacts> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            StringBuilder jsonContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            String jsonString = jsonContent.toString();
            // Parse JSON string manually
            jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove the outer braces
            String[] items = jsonString.split("\\},\\{");
            for (String item : items) {
                item = item.replace("{", "").replace("}", "").replace("\"", "");
                String[] attributes = item.split(",");
                JsonContacts contact = new JsonContacts();
                for (String attribute : attributes) {
                    String[] keyValue = attribute.split(":");
                    switch (keyValue[0].trim()) {
                        case "id":
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
                            contact.setFotoString(keyValue[1].trim());
                            break;
                    }
                }
                contacts.add(contact);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo .json", e);
        }
        return contacts;
    }
}
