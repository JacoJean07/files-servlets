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

<div class="container">
    <h1>Contactos</h1>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th scope="col">#</th>
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
                    out.println("<td>" + contact.getId() + "</td>");
                    out.println("<td>" + contact.getName() + "</td>");
                    out.println("<td>" + contact.getEmail() + "</td>");
                    out.println("<td>" + contact.getPhone() + "</td>");
                    out.println("<td>" + contact.getFotoString() + "</td>");
                    out.println("</tr>");
                }
            %>
        </tbody>
    </table>
</div>

<jsp:include page="includes/footer.jsp" />
