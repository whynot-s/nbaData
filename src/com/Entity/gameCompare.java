package com.Entity;

import com.google.gson.Gson;

public class gameCompare {

	public static String[] itemList = {
			"PTS", "AST", "DREB+OREB", "DREB", "OREB", "STL"
			, "BLK", "FGM/FGA", "FG3M/FG3A", "FTM/FTA", "PF", "TOV"
		};
	
	public Double[] PTS = new Double[2];
	public Double[] AST = new Double[2];
	public Double[] REB = new Double[2];
	public Double[] DREB = new Double[2];
	public Double[] OREB = new Double[2];
	public Double[] STL = new Double[2];
	public Double[] BLK = new Double[2];
	public Double[] FGP = new Double[2];
	public Double[] FG3P = new Double[2];
	public Double[] FT3P = new Double[2];
	public Double[] PF = new Double[2];
	public Double[] TOV = new Double[2];
	
	public gameCompare() {
		
	}
	
	public void add(int index, double[] items) {
		PTS[index] = items[0];
		AST[index] = items[1];
		REB[index] = items[2];
		DREB[index] = items[3];
		OREB[index] = items[4];
		STL[index] = items[5];
		BLK[index] = items[6];
		FGP[index] = items[7];
		FG3P[index] = items[8];
		FT3P[index] = items[9];
		PF[index] = items[10];
		TOV[index] = items[11];
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
