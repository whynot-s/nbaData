package com.Entity;

import com.google.gson.Gson;

public class totalTeamData {

	public Double[] PTS = new Double[30];
	public Double[] AST = new Double[30];
	public Double[] REB = new Double[30];
	public Double[] STL = new Double[30];
	public Double[] BLK = new Double[30];
	public Double[] TOV = new Double[30];
	public Double[] PF = new Double[30];
	public int counter;
	
	public totalTeamData() {
		counter = -1;
	}
	
	public void add(double pts, double ast, double reb, double stl, double blk, double tov, double pf) {
		if(counter++ == 30)return;
		PTS[counter] = pts;
		AST[counter] = ast;
		REB[counter] = reb;
		STL[counter] = stl;
		BLK[counter] = blk;
		TOV[counter] = tov;
		PF[counter] = pf;
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
