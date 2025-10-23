package mx.edu.utez.nomenclaturas.controller;
import mx.edu.utez.nomenclaturas.model.bean.NomenclaturaBean;
import mx.edu.utez.nomenclaturas.model.dao.NomenclaturaDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "NomenclaturaFormServlet", value = "/NomenclaturaFormServlet")
public class NomenclaturaFormServlet extends HttpServlet {

    private NomenclaturaDao dao = new NomenclaturaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idN = request.getParameter("idNomenclatura");
        String idG = request.getParameter("idGeneracion");

        if(idN != null) {
            int idNomenclatura = Integer.parseInt(idN);
            NomenclaturaBean nomenclatura = dao.obtenerPorId(idNomenclatura);
            request.setAttribute("nomenclatura", nomenclatura);
        } else if(idG != null) {
            int idGeneracion = Integer.parseInt(idG);
            NomenclaturaBean bean = new NomenclaturaBean();
            bean.setIdGeneracion(idGeneracion);
            request.setAttribute("nomenclatura", bean);
        }

        request.getRequestDispatcher("/jsp/nomenclatura_form.jsp").forward(request, response);
    }
}

