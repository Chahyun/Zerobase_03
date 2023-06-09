package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookMark {
	int id;
	int bookMark_Num;
	String bookMark_Name;
	String createDay;
	String updateDay;
	String wifiInputDay;
	String wifiName;
}
