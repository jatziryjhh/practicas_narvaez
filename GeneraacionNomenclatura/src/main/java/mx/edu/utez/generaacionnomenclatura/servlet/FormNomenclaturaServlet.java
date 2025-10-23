package mx.edu.utez.generaacionnomenclatura.servlet;
import mx.edu.utez.generaacionnomenclatura.dao.NomenclaturaDAO;
import mx.edu.utez.generaacionnomenclatura.dao.NomenclaturaDAOImpl;
import mx.edu.utez.generaacionnomenclatura.dto.Nomenclatura;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/form_nomenclatura")
public class FormNomenclaturaServlet extends HttpServlet {
    private NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            String generacionIdParam = req.getParameter("generacionId");

            Nomenclatura n = null;
            if (idParam != null) { // Modificar
                int id = Integer.parseInt(idParam);
                n = nomenclaturaDAO.getByGeneracion(0) // temporal, ajustaremos método
                        .stream()
                        .filter(nom -> nom.getId() == id)
                        .findFirst().orElse(null);
            } else if (generacionIdParam != null) { // Agregar
                n = new Nomenclatura();
                n.setIdGeneracion(Integer.parseInt(generacionIdParam));
            }

            req.setAttribute("nomenclatura", n);
            req.getRequestDispatcher("/jsp/form_nomenclatura.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String idParam = req.getParameter("id");
            String generacionIdParam = req.getParameter("generacionId");
            int valorNumerico = Integer.parseInt(req.getParameter("valorNumerico"));
            String descripcion = req.getParameter("descripcion");

            Nomenclatura n = new Nomenclatura();
            n.setValorNumerico(valorNumerico);
            n.setDescripcion(descripcion);

            if (idParam != null && !idParam.isEmpty()) { // Modificar
                n.setId(Integer.parseInt(idParam));
                nomenclaturaDAO.update(n);
                resp.sendRedirect("nomenclaturas?generacionId=" + req.getParameter("generacionId"));
            } else if (generacionIdParam != null) { // Agregar
                n.setIdGeneracion(Integer.parseInt(generacionIdParam));
                nomenclaturaDAO.add(n);
                resp.sendRedirect("nomenclaturas?generacionId=" + generacionIdParam);
            } else {
                resp.sendRedirect("generaciones");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

