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
 * Servlet implementation class getTeamData
 */
@WebServlet("/getTeamData")
public class getTeamData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTeamData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String teamID = request.getParameter("ID"); 
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
        		Connection con=DBUtil.getConnection();
        		Statement stmt=con.createStatement();
        		String sql= "select * from nbaData.teams where teamId = " + teamID;
        		ResultSet rs = stmt.executeQuery(sql);
        		while(rs.next()){
        			out.println(String.format("<li>%s</li>", rs.getString("teamName")));
        			out.println(String.format("<li>%s</li>", rs.getString("founded")));
        			out.println(String.format("<li>%s<li>", rs.getString("city")));
        			out.println(String.format("<li>%s<li>", rs.getString("arena")));
        			out.println(String.format("<li>%s<li>", rs.getString("GM")));
        			out.println(String.format("<li>%s<li>", rs.getString("GLeague")));
        			out.println(String.format("<li><a href=\"%s\">official website</a><li>", rs.getString("officialWebsite")));
        			out.println(String.format("<li><a href=\"%s\">facebook</a><li>", rs.getString("facebookLink")));
        			out.println(String.format("<li><a href=\"%s\">instagram</a><li>", rs.getString("instagramLink")));
        			out.println(String.format("<li><a href=\"%s\">twitter</a><li>", rs.getString("twitterLink")));
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
