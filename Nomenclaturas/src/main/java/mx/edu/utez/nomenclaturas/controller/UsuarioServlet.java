package mx.edu.utez.nomenclaturas.controller;

import mx.edu.utez.nomenclaturas.model.bean.UsuarioBean;
import mx.edu.utez.nomenclaturas.model.dao.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UsuarioServlet", value = "/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDao dao = new UsuarioDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        UsuarioBean user = dao.validarUsuario(usuario, password);

        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", user);
            response.sendRedirect(request.getContextPath() + "/GeneracionServlet");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

