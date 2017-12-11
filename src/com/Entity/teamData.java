package com.Entity;

import java.util.Map;

import com.google.gson.Gson;

public class teamData {

	public static String[] item = {
			"FTM", "FTA", "FGM", "FGA", "FG3M", "FG3A",
			"TOV", "PTS", "AST", "STL", "DREB", "OREB",
			"PF", "BLK"
			};
	
	public Double FTM;
	public Double FTA;
	public Double FGM;
	public Double FGA;
	public Double FG3M;
	public Double FG3A;
	public Double TOV;
	public Double PTS;
	public Double AST;
	public Double STL;
	public Double DREB;
	public Double OREB;
	public Double PF;
	public Double BLK;
	
	public teamData(Map<String, Double> m) {
		FTM = m.get("FTM");
		FTA = m.get("FTA");
		FGM = m.get("FGM");
		FGA = m.get("FGA");
		FG3M = m.get("FG3M");
		FG3A = m.get("FG3A");
		TOV = m.get("TOV");
		PTS = m.get("PTS");
		AST = m.get("AST");
		STL = m.get("STL");
		DREB = m.get("DREB");
		OREB = m.get("OREB");
		PF = m.get("PF");
		BLK = m.get("BLK");
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
