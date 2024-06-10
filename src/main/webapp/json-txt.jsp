
<%@ page import="model.JsonContacts" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.BufferedWriter" %>

<jsp:include page="includes/header.jsp" />


<%
    List<JsonContacts> contacts = new ArrayList<>();
    try {
        String path = "C:\\Users\\Jaco\\Documents\\NetBeansProjects\\files\\src\\main\\webapp\\assets\\json\\contactos.json";
        contacts = JsonContacts.readJson(path);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<div class="container d-flex">
    <div class="container input_section">
        <!-- este json va a guardar contactos simulando una base de datos -->
        <form id="form" method="POST" action="JsonContactsController">
            <div class="form-group">
                <label for="name">Nombre</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="phone">Teléfono</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>
            <div class="form-group">
                <label for="email">Correo</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="photo">Foto (URL)</label>
                <input type="text" class="form-control" id="photo" name="photo" required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
        </form>
    </div>
    <div class="container view_section">
        <!-- seccion para mostrar los contactos dentro del archivo .json y iterar sobre el array para mostrarlos -->
        <div class="row">
            <div class="col-md-12 table_section">
                <div class="">
                    <h1>Contactos</h1>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Foto</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (JsonContacts contact : contacts) {
                                    out.println("<tr>");
                                    out.println("<td>" + contact.getName() + "</td>");
                                    out.println("<td>" + contact.getEmail() + "</td>");
                                    out.println("<td>" + contact.getPhone() + "</td>");
                                    // Mostrar el foto en una imagen con 24px de altura
                                    // La imagen se carga desde el archivo JSON
                                    out.println("<td><img src=\"" + contact.getFotoString() + "\" alt=\"foto\" height=\"50\"></td>");
                                    out.println("</tr>");
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp" />
