<%-- 
    Document   : index
    Created on : 7 jun. 2024, 13:21:50
    Author     : Jaco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-bs-theme="dark">
    <head>
        <!-- bootswath -->
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
        <!-- meta -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FILE & JSP</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
            <div class="container-fluid" bis_skin_checked="1">
                <a class="navbar-brand" href="index.jsp">Navbar</a> <!-- AQUI VAMOS A PONER UNA IMAGEN -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarColor01" bis_skin_checked="1">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="index.jsp">Home
                                <span class="visually-hidden">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="json-txt.jsp">JSON & TXT</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>