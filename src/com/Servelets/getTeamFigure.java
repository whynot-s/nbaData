package com.Servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBtools.DBUtil;
import com.Entity.teamData;

/**
 * Servlet implementation class getTeamFigure
 */
@WebServlet("/getTeamFigure")
public class getTeamFigure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTeamFigure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String teamId = request.getParameter("teamId"); 
		String year = request.getParameter("year");
		String sql = "select 	AVG(FTM), AVG(FTA), AVG(FGM), AVG(FGA), \n" + 
				"    AVG(FG3M),AVG(FG3A), AVG(TOV), AVG(PTS),\n" + 
				"    AVG(AST), AVG(STL), AVG(DREB), AVG(OREB),\n" + 
				"    AVG(PF), AVG(BLK) from matchup where teamId = \"" + teamId +"\" and gameId in (select gameId from games where gameDate like \"" + year + "%\");";
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Double> items = new HashMap<String, Double>();
        try{
    			Connection con=DBUtil.getConnection();
    			Statement stmt=con.createStatement();
    			ResultSet rs = stmt.executeQuery(sql);
	    		while(rs.next()){
	    			for(String s : teamData.item)items.put(s, Double.parseDouble(rs.getString(String.format("AVG(%s)", s))));
	    			break;
	    		}
	    		rs.close();
	    		teamData t = new teamData(items);
	    		out.println(t.toJson());
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
