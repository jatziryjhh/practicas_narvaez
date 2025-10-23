package mx.edu.utez.generaacionnomenclatura.servlet;
import mx.edu.utez.generaacionnomenclatura.dao.NomenclaturaDAO;
import mx.edu.utez.generaacionnomenclatura.dao.NomenclaturaDAOImpl;
import mx.edu.utez.generaacionnomenclatura.dao.GeneracionDAO;
import mx.edu.utez.generaacionnomenclatura.dao.GeneracionDAOImpl;
import mx.edu.utez.generaacionnomenclatura.dto.Nomenclatura;
import mx.edu.utez.generaacionnomenclatura.dto.Generacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/nomenclaturas")
public class NomenclaturasServlet extends HttpServlet {
    private NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAOImpl();
    private GeneracionDAO generacionDAO = new GeneracionDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int generacionId = Integer.parseInt(req.getParameter("generacionId"));

            Generacion g = generacionDAO.getAll().stream()
                    .filter(gen -> gen.getId() == generacionId)
                    .findFirst().orElse(null);

            if (g == null) {
                resp.sendRedirect("generaciones");
                return;
            }

            List<Nomenclatura> lista = nomenclaturaDAO.getByGeneracion(generacionId);

            req.setAttribute("generacion", g);
            req.setAttribute("listaNomenclaturas", lista);
            req.getRequestDispatcher("/jsp/nomenclaturas.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
