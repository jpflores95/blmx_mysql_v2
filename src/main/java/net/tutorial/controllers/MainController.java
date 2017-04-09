package net.tutorial.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tutorial.utilities.DBService;
import net.tutorial.utilities.DataService;

import net.tutorial.utilities.TextToSpeechService;

@WebServlet({ "home", "" })
public class MainController extends HttpServlet {
	RequestDispatcher dispatcher;
	DataService ds = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("action");
		String id = req.getParameter("id");
		String viewName = "home";
		
		if (param != null && param.equals("new")) {
			viewName = "contact";
		} else if (param != null && param.equals("edit")) {
			ds = new DataService();
			
			viewName = "contact";
			req.setAttribute("document", ds.findRecord(Integer.parseInt(id)));

		} else {
			ds = new DataService();
			
			if (param != null && id != null && param.equals("delete")) {
				ds.deleteRecord(Integer.parseInt(id));
			}

			req.setAttribute("contacts", ds.allRecords());
		}

		dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
	
		if(req.getParameter("db")!= null){
			
		Map<String, Object> record = new HashMap<String, Object>();
		ds = new DataService();
		
		record.put("name", name);
		record.put("email", email);
		record.put("mobile", mobile);
	
		if (id != null) {
			ds.updateRecord(DataService.INSERT_RECORD, record);
		} else {
			record.put("_id", Integer.parseInt(id));
			ds.updateRecord(DataService.UPDATE_RECORD, record);
		}
		resp.sendRedirect("home");
		}
		

		else if(req.getParameter("t2s") != null){
				
		TextToSpeechService service = new TextToSpeechService();
		String text = req.getParameter("name");
		service.getAudio(text, resp);
			
		}
		
		
	}


}