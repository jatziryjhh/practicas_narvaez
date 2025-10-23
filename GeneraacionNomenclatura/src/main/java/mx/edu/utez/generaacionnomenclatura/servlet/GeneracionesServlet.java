package mx.edu.utez.generaacionnomenclatura.servlet;

import mx.edu.utez.generaacionnomenclatura.dao.GeneracionDAO;
import mx.edu.utez.generaacionnomenclatura.dao.GeneracionDAOImpl;
import mx.edu.utez.generaacionnomenclatura.dto.Generacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/generaciones")
public class GeneracionesServlet extends HttpServlet {
    private GeneracionDAO generacionDAO = new GeneracionDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Generacion> lista = generacionDAO.getAll();
            req.setAttribute("listaGeneraciones", lista);
            req.getRequestDispatcher("jsp/Generaciones.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

