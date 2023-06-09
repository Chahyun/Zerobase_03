package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;

import com.dto.History;
import com.dto.Wifi;
import com.service.WifiService;

public class WifiController {
	WifiService wifiService = new WifiService();
	
	public void inputWifi() throws  IOException, JSONException, SQLException, ClassNotFoundException{
		wifiService.inputWifi();
	}
	
	public int getWifiCnt() throws  IOException, JSONException, SQLException, ClassNotFoundException{
		return wifiService.getWifiCnt();
	}
	
	public  List<Wifi> myPosWifi(String lat, String lnt) throws ClassNotFoundException, SQLException {
		System.out.println(lat + " " + lnt);
		return wifiService.getWifiList(lat, lnt);
	}
	
	public Wifi detailWifi(String mgNum) throws ClassNotFoundException, SQLException {
		return wifiService.getDetailWIfi(mgNum);
	}
	
	public void inputHistory(String lat, String lnt) throws ClassNotFoundException, SQLException {
		wifiService.insertHistory(lat, lnt);
	}
	
	public List<History> getHistoryList() throws ClassNotFoundException, SQLException {
		return wifiService.historyList();
	}
	
	public void deleteHistory(String id) throws ClassNotFoundException, SQLException {
	    wifiService.deleteHistory(id);
	}
	
}
