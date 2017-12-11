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
import com.Entity.gameCompare;

/**
 * Servlet implementation class getGameSummary
 */
@WebServlet("/getGameSummary")
public class getGameSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getGameSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String gameID = request.getParameter("ID"); 
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
        		Connection con=DBUtil.getConnection();
        		Statement stmt=con.createStatement();
        		String sql= "select * from nbaData.games where gameId = " + gameID;
        		ResultSet rs = stmt.executeQuery(sql);
        		String teamHome = null;
        		String teamAway = null;
        		while(rs.next()){
        			teamHome = rs.getString("teamHome");
        			teamAway = rs.getString("teamAway");
        			break;
        		}
        		rs.close();
        		gameCompare gc = new gameCompare();
        		String tHsql = "select PTS, AST, DREB+OREB, DREB, OREB, STL, BLK, FGM/FGA, FG3M/FG3A, FTM/FTA, PF, TOV from nbaData.matchUp where gameId = " + gameID + " and teamId = " + teamHome;
        		String tAsql = "select PTS, AST, DREB+OREB, DREB, OREB, STL, BLK, FGM/FGA, FG3M/FG3A, FTM/FTA, PF, TOV from nbaData.matchUp where gameId = " + gameID + " and teamId = " + teamAway;
        		rs = stmt.executeQuery(tHsql);
        		double[] items = new double[12];
        		while(rs.next()) {
        			for(int i = 0; i < gameCompare.itemList.length; i++) {
        				items[i] = Double.parseDouble(rs.getString(gameCompare.itemList[i]));
        			}
        			gc.add(0, items);
        			break;
        		}
        		rs.close();
        		rs = stmt.executeQuery(tAsql);
        		while(rs.next()) {
        			for(int i = 0; i < gameCompare.itemList.length; i++) {
        				items[i] = Double.parseDouble(rs.getString(gameCompare.itemList[i]));
        			}
        			gc.add(1, items);
        			break;
        		}
        		out.println(gc.toJson());
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
