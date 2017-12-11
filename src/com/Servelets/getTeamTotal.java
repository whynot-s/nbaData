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
import com.Entity.totalTeamData;

/**
 * Servlet implementation class getTeamTotal
 */
@WebServlet("/getTeamTotal")
public class getTeamTotal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTeamTotal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String year = request.getParameter("year");
		String sql = "select AVG(TOV), AVG(PTS),\n" + 
				"    AVG(AST), AVG(STL), AVG(DREB) + AVG(OREB),\n" + 
				"    AVG(PF), AVG(BLK) from matchup where gameId in (select gameId from games where gameDate like \"" + year + "%\") group by teamId order by teamId asc;";
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
    			Connection con=DBUtil.getConnection();
    			Statement stmt=con.createStatement();
    			ResultSet rs = stmt.executeQuery(sql);
    			totalTeamData tt = new totalTeamData();
	    		while(rs.next()){
	    			tt.add(Double.parseDouble(rs.getString("AVG(PTS)")),
	    					Double.parseDouble(rs.getString("AVG(AST)")),
	    					Double.parseDouble(rs.getString("AVG(DREB) + AVG(OREB)")),
	    					Double.parseDouble(rs.getString("AVG(STL)")),
	    					Double.parseDouble(rs.getString("AVG(BLK)")),
	    					Double.parseDouble(rs.getString("AVG(TOV)")),
	    					Double.parseDouble(rs.getString("AVG(PF)")));
	    		}
	    		rs.close();
	    		out.println(tt.toJson());
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
