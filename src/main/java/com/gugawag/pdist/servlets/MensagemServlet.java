package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet{

    @EJB(lookup="java:module/mensagemService")
    private MensagemService mensagemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operacao = req.getParameter("oper");
        PrintWriter resposta = resp.getWriter();

        switch (operacao){
            case "1":{
                long id = Integer.parseInt(req.getParameter("id"));
                Mensagem mensagem = mensagemService.pesquisarPorId(id);
                resposta.println("Mensagem ID(" + mensagem.getId() + ") : " + mensagem.getTexto());
                break;
            }
            case "2":{
                long id = Integer.parseInt(req.getParameter("id"));
                String texto = req.getParameter("texto");
                mensagemService.inserir(id, texto);
            }
            case "3": {
                for(Mensagem mensagem: mensagemService.listar()){
                    resposta.println("Mensagem ID(" + mensagem.getId() + ") : " + mensagem.getTexto());
                }
                break;
            }
        }
    }
}
