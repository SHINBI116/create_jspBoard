package jspboard.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Comments {
	int board_id;
	int comment_id;
	String comment_content;
	String comment_writer;
	String comment_password;
	Date comment_write_date;
	
	public Comments(ResultSet rs) throws SQLException {
		board_id = rs.getInt("board_id");
		comment_id = rs.getInt("comment_id");
		comment_content = rs.getString("comment_content");
		comment_writer = rs.getString("comment_writer");
		comment_password = rs.getString("comment_password");
		comment_write_date = rs.getDate("comment_write_date");
	}
	
	public Comments(int board_id, int comment_id, String comment_content, String comment_writer,
			String comment_password, Date comment_write_date) {
		super();
		this.board_id = board_id;
		this.comment_id = comment_id;
		this.comment_content = comment_content;
		this.comment_writer = comment_writer;
		this.comment_password = comment_password;
		this.comment_write_date = comment_write_date;
	}



	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}

	public String getComment_password() {
		return comment_password;
	}

	public void setComment_password(String comment_password) {
		this.comment_password = comment_password;
	}

	public Date getComment_write_date() {
		return comment_write_date;
	}

	public void setComment_write_date(Date comment_write_date) {
		this.comment_write_date = comment_write_date;
	}

	@Override
	public String toString() {
		return "Comments [board_id=" + board_id + ", comment_id=" + comment_id + ", comment_content=" + comment_content
				+ ", comment_writer=" + comment_writer + ", comment_password=" + comment_password
				+ ", comment_write_date=" + comment_write_date + "]";
	}
	
	
	
}
