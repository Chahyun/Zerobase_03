package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.db.DbConnect;
import com.dto.BookMark;

public class BookMarkService {
	DbConnect dbConnect = new DbConnect();
	
	public void inputBookMark(String name, String number) throws ClassNotFoundException, SQLException {
		dbConnect.insertBookMark(name, number);		
	}

	public List<BookMark> getBookMarkList() throws ClassNotFoundException, SQLException {
		return dbConnect.selectBookMarkList();
	}

	public BookMark getBookMarkInfo(String id) throws ClassNotFoundException, SQLException {
		return dbConnect.selectBookMarkInfo(id);
	}

	public void modifyBookMark(int id, String name, String number) throws ClassNotFoundException, SQLException {
		dbConnect.updateBookMark(id, name, number);
	}

	public void deleteBookMark(int id) throws ClassNotFoundException, SQLException {
		dbConnect.deleteBookMark(id);
	}
	
	public void addBookMarkList(String bookMarkId ,String wifiNum) throws ClassNotFoundException, SQLException {
		dbConnect.insertBookMarkList(bookMarkId, wifiNum);
	}

	public List<Map<String, Object>> getUserBookList() {
		return dbConnect.selectUserBookMarkList();
	}

	public Map<String, Object> getUserBookMark(String id) {	
		return dbConnect.selectUserBookMark(id);
	}

	public void deleteUserBookMark(String id) {
		dbConnect.deleteUserBookMark(id);
	}

	public void deleteBookMarkList(int id) {
		dbConnect.deleteBookMarkList(id);
		
	}
	
}
