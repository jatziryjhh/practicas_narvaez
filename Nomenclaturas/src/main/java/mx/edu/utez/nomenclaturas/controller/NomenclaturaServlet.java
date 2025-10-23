package mx.edu.utez.nomenclaturas.controller;

import mx.edu.utez.nomenclaturas.model.bean.NomenclaturaBean;
import mx.edu.utez.nomenclaturas.model.bean.GeneracionBean;
import mx.edu.utez.nomenclaturas.model.dao.NomenclaturaDao;
import mx.edu.utez.nomenclaturas.model.dao.GeneracionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NomenclaturaServlet", value = "/NomenclaturaServlet")
public class NomenclaturaServlet extends HttpServlet {
    private NomenclaturaDao dao = new NomenclaturaDao();
    private GeneracionDao genDao = new GeneracionDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idGenStr = req.getParameter("idGeneracion");
        String idNomStr = req.getParameter("idNomenclatura");
        String nuevo = req.getParameter("nuevo");

        if (idNomStr != null && !idNomStr.isEmpty()) {
            // Modificar nomenclatura
            int idNom = Integer.parseInt(idNomStr);
            NomenclaturaBean n = dao.findById(idNom);
            req.setAttribute("nomenclatura", n);
            req.getRequestDispatcher("/jsp/nomenclatura_form.jsp").forward(req, resp);

        } else if (nuevo != null && nuevo.equals("true") && idGenStr != null) {
            // Agregar nueva nomenclatura
            req.setAttribute("idGeneracion", Integer.parseInt(idGenStr));
            req.getRequestDispatcher("/jsp/nomenclatura_form.jsp").forward(req, resp);

        } else if (idGenStr != null && !idGenStr.isEmpty()) {
            // Listar nomenclaturas de una generación
            int idGen = Integer.parseInt(idGenStr);
            List<NomenclaturaBean> lista = dao.findByGeneracion(idGen);
            GeneracionBean generacion = genDao.findById(idGen); // obtener info de la generación

            req.setAttribute("listaNomenclaturas", lista);
            req.setAttribute("generacion", generacion);
            req.setAttribute("idGeneracion", idGen);
            req.getRequestDispatcher("/jsp/nomenclaturas.jsp").forward(req, resp);

        } else {
            // fallback: redirige a lista de generaciones
            resp.sendRedirect(req.getContextPath() + "/GeneracionServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idNomStr = req.getParameter("idNomenclatura");
        String idGenStr = req.getParameter("idGeneracion");
        String valorStr = req.getParameter("valorNumerico");
        String descripcion = req.getParameter("descripcion");

        if (idGenStr == null || idGenStr.isEmpty() || valorStr == null || valorStr.isEmpty() || descripcion == null) {
            resp.sendRedirect(req.getContextPath() + "/GeneracionServlet");
            return;
        }

        int valor = Integer.parseInt(valorStr);
        int idGen = Integer.parseInt(idGenStr);
        boolean status = true; // activo por default

        if (idNomStr != null && !idNomStr.isEmpty()) {
            // actualizar
            int idNom = Integer.parseInt(idNomStr);
            NomenclaturaBean n = new NomenclaturaBean(idNom, valor, descripcion, idGen, status);
            dao.update(n);
        } else {
            // insertar nueva nomenclatura
            NomenclaturaBean n = new NomenclaturaBean(0, valor, descripcion, idGen, status);
            dao.insert(n);
        }

        // redirigir a la lista de nomenclaturas de esa generación
        resp.sendRedirect(req.getContextPath() + "/NomenclaturaServlet?idGeneracion=" + idGen);
    }
}