package mx.edu.utez.nomenclaturas.controller;
import mx.edu.utez.nomenclaturas.model.bean.GeneracionBean;
import mx.edu.utez.nomenclaturas.model.dao.GeneracionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GeneracionServlet", value = "/GeneracionServlet")
public class GeneracionServlet extends HttpServlet {
    private GeneracionDao dao = new GeneracionDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GeneracionBean> lista = dao.findAll();
        req.setAttribute("listaGeneraciones", lista);
        req.getRequestDispatcher("jsp/generaciones.jsp").forward(req, resp);
    }
}
