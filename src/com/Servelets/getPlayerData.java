package com.Servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBtools.DBUtil;

/**
 * Servlet implementation class getPlayerData
 */
@WebServlet("/getPlayerData")
public class getPlayerData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPlayerData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String playerID = request.getParameter("ID"); 
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
        		Connection con=DBUtil.getConnection();
        		Statement stmt=con.createStatement();
        		String sql= "select * from nbaData.players where playerId = " + playerID;
        		ResultSet rs = stmt.executeQuery(sql);
        		while(rs.next()){
        			out.println(String.format("<li>%s %s</li>", rs.getString("firstName"), rs.getString("lastName")));
        			out.println(String.format("<li>%s</li>", rs.getString("birthday")));
        			out.println(String.format("<li>%s<li>", rs.getString("height")));
        			out.println(String.format("<li>%s<li>", rs.getString("weight")));
        			out.println(String.format("<li>%s<li>", rs.getString("jersey")));
        			out.println(String.format("<li>%s<li>", rs.getString("position")));
        			if(rs.getString("draftNumber") != null)out.println(String.format("<li>%s<li>", rs.getString("draftNumber")));
        			if(rs.getString("draftRound") != null)out.println(String.format("<li>%s<li>", rs.getString("draftRound")));
        			if(rs.getString("draftYear") != null)out.println(String.format("<li>%s<li>", rs.getString("draftYear")));
        			break;
        		}
        		rs.close();
        }
	    catch(Exception ex){
	    		ex.printStackTrace();
	    }
	    finally{
	    		DBUtil.Close();
	    		out.flush();
	    		out.close();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
