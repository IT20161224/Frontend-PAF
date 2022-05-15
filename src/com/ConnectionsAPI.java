package com;
import model.Connections;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ConnectionsAPI")
public class ConnectionsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connections ConnectionsObj = new Connections(); 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = ConnectionsObj.insertConnections(request.getParameter("conName"), 
				 request.getParameter("conType"), 
				request.getParameter("conDesc"), 
				request.getParameter("conadminId")); 
				response.getWriter().write(output); 
	}

	// Convert request parameters to a Map
	private static Map<String, String> getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 

	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 String output =ConnectionsObj.updateConnections(paras.get("hidItemIDSave").toString(), 
		 paras.get("conName").toString(), 
		paras.get("conType").toString(), 
		paras.get("conDesc").toString(),
		paras.get("conAdminId").toString()); 
		response.getWriter().write(output); 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<?, ?> paras = getParasMap(request); 
		 String output =ConnectionsObj.deleteConnections(paras.get("conID").toString()); 
		response.getWriter().write(output); 
	}

}