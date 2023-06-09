package com.controller;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dto.BookMark;
import com.service.BookMarkService;


public class BookMarkController {
	BookMarkService bookMarkService = new BookMarkService();
	
	public void inputBookMark(String name, String number) throws ClassNotFoundException, SQLException {
		bookMarkService.inputBookMark(name, number);
	}
	
	public List<BookMark> getBookMarkList() throws ClassNotFoundException, SQLException {
		return bookMarkService.getBookMarkList();
	}
	
	public BookMark getBookMarkInfo(String id) throws ClassNotFoundException, SQLException {
		return bookMarkService.getBookMarkInfo(id);
	}
	
	public void modifyBookMark(int id, String name, String number) throws ClassNotFoundException, SQLException {
		System.out.println(id);
		bookMarkService.modifyBookMark(id, name, number);
	}
	public void deleteBookMark(int id) throws ClassNotFoundException, SQLException {
		bookMarkService.deleteBookMark(id);
	}
	
	public void addBookMarkList(String bookMarkId, String wifiNum) throws ClassNotFoundException, SQLException {
		bookMarkService.addBookMarkList(bookMarkId, wifiNum);
	}
	
	public List<Map<String,Object>> getUserBookList(){
		return bookMarkService.getUserBookList();
	}
	public Map<String,Object> getUserBookMark(String id){
		return bookMarkService.getUserBookMark(id);
	}
	
	public void deleteUserBookMark(String id) {
		bookMarkService.deleteUserBookMark(id);
	}
	
	public void deleteBookMarkList(int id) throws ClassNotFoundException, SQLException {
		bookMarkService.deleteBookMarkList(id);
	}
}
