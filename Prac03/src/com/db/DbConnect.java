package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dto.BookMark;
import com.dto.History;
import com.dto.Wifi;

public class DbConnect {
	final static String dbFile = "D:\\edu\\java\\Prac03\\SQLite.db";
	final static String url = "jdbc:sqlite:" + dbFile;
	
	/* db연결 해제 */
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/* db연결 */
	public static Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;
		if (conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(url);
				return conn;

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/* 오픈API 와이파이정보 인서트 */
	public void insertWifiInfo(List<Wifi> wifiList) throws ClassNotFoundException, SQLException {
	    try (Connection conn = getConnection()) {
	        conn.setAutoCommit(false);

	        String query = "INSERT OR IGNORE INTO WifiInfo "
	        		+ "(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
	        		+ "VALUES "
	        		+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	            int cnt = 0;
	            for (Wifi wifi : wifiList) {
	                cnt++;
	                pstmt.setString(1, wifi.getX_SWIFI_MGR_NO());
	                pstmt.setString(2, wifi.getX_SWIFI_WRDOFC());
	                pstmt.setString(3, wifi.getX_SWIFI_MAIN_NM());
	                pstmt.setString(4, wifi.getX_SWIFI_ADRES1());
	                pstmt.setString(5, wifi.getX_SWIFI_ADRES2());
	                pstmt.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
	                pstmt.setString(7, wifi.getX_SWIFI_INSTL_TY());
	                pstmt.setString(8, wifi.getX_SWIFI_INSTL_MBY());
	                pstmt.setString(9, wifi.getX_SWIFI_SVC_SE());
	                pstmt.setString(10, wifi.getX_SWIFI_CMCWR());
	                pstmt.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
	                pstmt.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
	                pstmt.setString(13, wifi.getX_SWIFI_REMARS3());
	                pstmt.setDouble(14, wifi.getLAT());
	                pstmt.setDouble(15, wifi.getLNT());
	                pstmt.setString(16, wifi.getWORK_DTTM());

	                pstmt.addBatch();

	                if (cnt % 1000 == 0) {
	                    pstmt.executeBatch();
	                    pstmt.clearBatch();
	                    conn.commit();
	                }
	            }

	            pstmt.executeBatch();
	            conn.commit();
	            conn.rollback();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	/* 내 위경도와 가까운 Wifi리스트 20개 가져오기 */
	public List<Wifi> selectWifiList(String lat, String lnt) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = getConnection();
		List<Wifi> wifiList = new ArrayList<>();
		String query = " SELECT *, 6371 * 2 * "
				+ " ASIN(SQRT(POWER(SIN(((LAT -  ? ) * PI() / 180) / 2), 2) + COS( ?  * PI() / 180) * "
				+ " COS((LAT * PI() / 180))" + " * POWER(SIN(((LNT -  ? ) * PI() / 180) / 2), 2))) "
				+ " AS distance FROM WifiInfo ORDER BY distance LIMIT 0, 20";
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, lnt);
		pstmt.setString(2, lnt);
		pstmt.setString(3, lat);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Wifi wifi = new Wifi();
			wifi.setDistance(Math.round(Double.parseDouble(rs.getString("distance")) * 10000.0) / 10000.0);
			wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
			wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
			wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
			wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
			wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
			wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
			wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
			wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
			wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
			wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
			wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
			wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
			wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
			wifi.setLAT(rs.getDouble("LAT"));
			wifi.setLNT(rs.getDouble("LNT"));
			wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
			wifiList.add(wifi);
		}

		closeConnection(conn);
		return wifiList;
	}
	
	/* 알맞은 키의 wifi 정보 가져오기 */
	public Wifi selectWifi(String mgNum) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = getConnection();
		Wifi wifi = new Wifi();
		String query = " SELECT * "
				+ " FROM WifiInfo " 
				+ " WHERE X_SWIFI_MGR_NO = ? ";

		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, mgNum);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		while (rs.next()) {
			wifi.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
			wifi.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
			wifi.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
			wifi.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
			wifi.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
			wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
			wifi.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
			wifi.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
			wifi.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
			wifi.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
			wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
			wifi.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
			wifi.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
			wifi.setLAT(rs.getDouble("LAT"));
			wifi.setLNT(rs.getDouble("LNT"));
			wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
		}
		closeConnection(conn);
		return wifi;
	}
	/* 위치 히스토리 정보 인서트 */
	public void insertHistory(String lat, String lnt) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = getConnection();
		String query = " INSERT INTO History (lat, lnt, InquiryDate) "
				+ "VALUES "
				+ "( ? , ? , (SELECT datetime('now', 'localtime'))); ";

		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(query);
		pstmt.setDouble(1, Math.round(Double.parseDouble(lat) * 10000.0) / 10000.0);
		pstmt.setDouble(2, Math.round(Double.parseDouble(lnt) * 10000.0) / 10000.0);
		if (pstmt.executeUpdate() > 0) {
			System.out.println("로그저장 성공");
		} else {
			System.out.println("로그저장 실패");
		}
		pstmt.close();
		closeConnection(conn);
	}
	
	/* 총 wifi 갯수 가져오기 */
	public int selectWifiCnt() throws ClassNotFoundException, SQLException {
		int wifiCnt = 0;
		Connection conn = null;
		conn = getConnection();
		String query = " SELECT COUNT(*) AS wifiCnt "
				+ "FROM WifiInfo; ";
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(query);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		wifiCnt = rs.getInt("wifiCnt");
		closeConnection(conn);
		return wifiCnt;
	}
	
	/* 위치 기록 로그 리스트 가져오기 */
	public List<History> historyList() throws ClassNotFoundException, SQLException {
		List<History> historyList = new ArrayList<>();
		Connection conn = null;
		conn = getConnection();
		String query = " SELECT * FROM History "
				+ "ORDER BY id DESC ";
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(query);
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		while (rs.next()) {
			History history = new History();
			history.setId(rs.getInt("id"));
			history.setLat(rs.getDouble("lat"));
			history.setLnt(rs.getDouble("lnt"));
			history.setInquiryDate(rs.getString("inquiryDate"));
			historyList.add(history);
		}
		closeConnection(conn);
		return historyList;
	}
	
	/* 위치 기록 로그 제거 */
	public void deleteHistory(String id) throws ClassNotFoundException, NumberFormatException, SQLException {
		Connection conn = null;
		conn = getConnection();
		PreparedStatement pstmt = null;
		String query = " DELETE FROM History "
				+ "WHERE id = ? ";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.executeUpdate();
		closeConnection(conn);
	}

	/* 북마크그룹 생성 */
	public void insertBookMark(String name, String number) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = getConnection();

		String query = "INSERT INTO bookMark "
				+ "(bookMark_Num, bookMark_Name, createDay )" 
				+ "VALUES"
				+ "( ? , ? , (SELECT datetime('now', 'localtime'))); ";
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, Integer.parseInt(number));
		pstmt.setString(2, name);
		if (pstmt.executeUpdate() > 0) {
			System.out.println("북마크 저장 성공");
		} else {
			System.out.println("북마크 저장 실패");
		}
		pstmt.close();
		closeConnection(conn);
	}

	/* 북마크그룹 리스트 가져오기 */
	public List<BookMark> selectBookMarkList() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookMark> bookMarkList = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM BookMark "
						+ " ORDER BY bookMark_Num ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookMark bookMark = new BookMark();
				bookMark.setId(rs.getInt("id"));
				bookMark.setBookMark_Name(rs.getString("bookMark_Name"));
				bookMark.setBookMark_Num(rs.getInt("bookMark_Num"));
				bookMark.setCreateDay(rs.getString("createDay"));
				bookMark.setUpdateDay(rs.getString("updateDay"));
				bookMarkList.add(bookMark);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				closeConnection(conn);
			}
		}

		return bookMarkList;
	}

	/* 북마크 그룹 정보 가져오기 */
	public BookMark selectBookMarkInfo(String id) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String query = "SELECT id, bookMark_Name, bookMark_Num "
						+ " FROM BookMark WHERE id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			BookMark bookMark = new BookMark();
			while (rs.next()) {
				bookMark.setId(rs.getInt("id"));
				bookMark.setBookMark_Name(rs.getString("bookMark_Name"));
				bookMark.setBookMark_Num(rs.getInt("bookMark_Num"));
			}
			pstmt.close();
			closeConnection(conn);
			return bookMark;
		} catch (ClassNotFoundException | SQLException e) {
			throw e;
		}
	}
	
	/* 원하는 북마크 수정 */
	public void updateBookMark(int id, String name, String number) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String query = "UPDATE BookMark SET "
					+ " bookMark_Name = ?, bookMark_Num = ?, updateDay = (SELECT datetime('now', 'localtime')) "
					+ " WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, Integer.parseInt(number));
			pstmt.setInt(3, id);
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("북마크가 수정되었습니다.");
			} else {
				System.out.println("수정할 북마크를 찾지 못했습니다.");
			}
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				closeConnection(conn);
			}
		}
	}
	
	
	/* 원하는 북마크 제거 */
	public void deleteBookMark(int id) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String query = " DELETE FROM BookMark "
						+ " WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("북마크가 삭제되었습니다.");
			} else {
				System.out.println("삭제할 북마크를 찾지 못했습니다.");
			}
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				closeConnection(conn);
			}
		}
	}

	
	/* 북마크리스트 추가 */
	public void insertBookMarkList(String bookMarkId, String wifiNum) throws SQLException {
		String query = "INSERT INTO bookMarkList "
				+ "(bookMarkId, X_SWIFI_MGR_NO, inputWifiDay) "
				+ "VALUES (?, ?, datetime('now', 'localtime'))";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, Integer.parseInt(bookMarkId));
			pstmt.setString(2, wifiNum);
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("북마크리스트가 추가되었습니다.");
			} else {
				System.out.println("북마크리스트 추가 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* 북마크리스트 가져오기 */
	public List<Map<String, Object>> selectUserBookMarkList() {
		List<Map<String, Object>> bookMarkList = new ArrayList<>();
		String query = " SELECT bl.id, bm.bookMark_Name ,w.X_SWIFI_MAIN_NM, bl.inputWifiDay "
				+ " FROM bookMarkList bl "
				+ " JOIN WifiInfo w ON bl.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO "
				+ " JOIN bookMark bm ON bl.bookMarkId = bm.id; ";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Map<String, Object> bookMarkMap = new HashMap<>();
				bookMarkMap.put("id", rs.getInt("id"));
				bookMarkMap.put("bookMarkName", rs.getString("bookMark_Name"));
				bookMarkMap.put("wifiName", rs.getString("X_SWIFI_MAIN_NM"));
				bookMarkMap.put("inputWifiDay", rs.getString("inputWifiDay"));
				bookMarkList.add(bookMarkMap);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return bookMarkList;
	}

	/* 선택한 북마크 정보 가져오기 */
	public Map<String, Object> selectUserBookMark(String id) {
		Map<String, Object> userBookMark = new HashMap<>();
		String query = " SELECT bl.id, bm.bookMark_Name ,w.X_SWIFI_MAIN_NM, bl.inputWifiDay "
				+ " FROM bookMarkList bl "
				+ " JOIN WifiInfo w ON bl.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO "
				+ " JOIN bookMark bm ON bl.bookMarkId = bm.id "
				+ " WHERE bl.id = ? ";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				userBookMark.put("id", rs.getInt("id"));
				userBookMark.put("bookMarkName", rs.getString("bookMark_Name"));
				userBookMark.put("wifiName", rs.getString("X_SWIFI_MAIN_NM"));
				userBookMark.put("inputWifiDay", rs.getString("inputWifiDay"));
			}

		} catch (SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userBookMark;
	}

	
	/* 선택한 북마크 제거 */
	public void deleteUserBookMark(String id) {
		String query = " DELETE FROM bookMarkList WHERE id = ? ";
	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, Integer.parseInt(id));
	        int rowsDeleted = pstmt.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("북마크가 삭제되었습니다.");
	        } else {
	            System.out.println("북마크 삭제 실패");
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	public void deleteBookMarkList(int id){
	    try (Connection conn = getConnection();
	        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM bookMarkList WHERE bookMarkId = ?")) {
	        pstmt.setInt(1, id);
	        int rowsUpdated = pstmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("북마크 리스트가 삭제되었습니다.");
	        } else {
	            System.out.println("삭제할 북마크 리스트를 찾지 못했습니다.");
	        }
	    }catch(SQLException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	}
}
