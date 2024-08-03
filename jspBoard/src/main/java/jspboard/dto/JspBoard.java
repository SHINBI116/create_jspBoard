package jspboard.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JspBoard {
	int board_id;
	String board_title;
	String board_writer;
	String board_password;
	String board_writer_ip_addr;
	String board_content;
	Date board_write_date;
	int board_view_count;
	int board_good_count;
	int board_bad_count;
	
	
	
	public JspBoard(ResultSet rs) throws SQLException {
		board_id = rs.getInt("board_id");
		board_title = rs.getString("board_title");
		board_writer = rs.getString("board_writer");
		board_password = rs.getString("board_password");
		board_writer_ip_addr = rs.getString("board_writer_ip_addr");
		board_content = rs.getString("board_content");
		board_write_date = rs.getDate("board_write_date");
		board_view_count = rs.getInt("board_view_count");
		board_good_count = rs.getInt("board_good_count");
		board_bad_count = rs.getInt("board_bad_count");
	}



	public JspBoard(int board_id, String board_title, String board_writer, String board_password,
			String board_writer_ip_addr, String board_content, Date board_write_date, int board_view_count,
			int board_good_count, int board_bad_count) {
		super();
		this.board_id = board_id;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_password = board_password;
		this.board_writer_ip_addr = board_writer_ip_addr;
		this.board_content = board_content;
		this.board_write_date = board_write_date;
		this.board_view_count = board_view_count;
		this.board_good_count = board_good_count;
		this.board_bad_count = board_bad_count;
	}



	public int getBoard_id() {
		return board_id;
	}



	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}



	public String getBoard_title() {
		return board_title;
	}



	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}



	public String getBoard_writer() {
		return board_writer;
	}



	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}



	public String getBoard_password() {
		return board_password;
	}



	public void setBoard_password(String board_password) {
		this.board_password = board_password;
	}



	public String getBoard_writer_ip_addr() {
		return board_writer_ip_addr;
	}



	public void setBoard_writer_ip_addr(String board_writer_ip_addr) {
		this.board_writer_ip_addr = board_writer_ip_addr;
	}



	public String getBoard_content() {
		return board_content;
	}



	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}



	public Date getBoard_write_date() {
		return board_write_date;
	}



	public void setBoard_write_date(Date board_write_date) {
		this.board_write_date = board_write_date;
	}



	public int getBoard_view_count() {
		return board_view_count;
	}



	public void setBoard_view_count(int board_view_count) {
		this.board_view_count = board_view_count;
	}



	public int getBoard_good_count() {
		return board_good_count;
	}



	public void setBoard_good_count(int board_good_count) {
		this.board_good_count = board_good_count;
	}



	public int getBoard_bad_count() {
		return board_bad_count;
	}



	public void setBoard_bad_count(int board_bad_count) {
		this.board_bad_count = board_bad_count;
	}



	@Override
	public String toString() {
		return "JspBoard [board_id=" + board_id + ", board_title=" + board_title + ", board_writer=" + board_writer
				+ ", board_password=" + board_password + ", board_writer_ip_addr=" + board_writer_ip_addr
				+ ", board_content=" + board_content + ", board_write_date=" + board_write_date + ", board_view_count="
				+ board_view_count + ", board_good_count=" + board_good_count + ", board_bad_count=" + board_bad_count
				+ "]";
	}
	
	
	
}
