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
 * Servlet implementation class getPlayerName
 */
@WebServlet("/getPlayerName")
public class getPlayerName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPlayerName() {
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
        		String sql= "select firstName, lastName from nbaData.players where playerId = " + playerID;
        		ResultSet rs = stmt.executeQuery(sql);
        		while(rs.next()){
        			out.print(String.format("%s %s", rs.getString("firstName"), rs.getString("lastName")));
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
