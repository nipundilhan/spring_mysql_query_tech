package com.sprng.comn_comman.util;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class DateFormatter {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm";
	private static final String DATE_FORMAT_NOTE = "dd/MMM/yyyy HH:mm";
	private static final String TIME_ZONE = "Asia/Colombo";
	
	public static Date convertStringToDate(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			return formatter.parse(date);
		}catch (Exception e) {
//			AppLogger.getSyslogger().error("Convet string to date", e.getMessage());
		}
		return null;
	}
	
	public static String convertDateToString(Date date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			return formatter.format(date);
		}catch (Exception e) {
//			AppLogger.getSyslogger().error("Convet date to string", e.getMessage());
		}
		return null;
	}
	
	
	public static String convertDateToStringWithTimeZone(Date date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			formatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
			return formatter.format(date);
	
		}catch (Exception e) {
//			AppLogger.getSyslogger().error("Convet date to string", e.getMessage());
		}
		return null;
	}
	
	public static Date convertStringToDateWithTimeZone(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			formatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
			return formatter.parse(date);
		}catch (Exception e) {
//			AppLogger.getSyslogger().error("Convet string to date", e.getMessage());
		}
		return null;
	}

	public static String getFormatedCurrentTimeForNote() {
		try {
			LocalDateTime currentDate = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT_NOTE);
			return dtf.format(currentDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getFormatedCurrentTime() {
		try {
			LocalDateTime currentDate = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT_TIME);
			return dtf.format(currentDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date convertStringToDateTime(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
			return formatter.parse(date);
		}catch (Exception e) {
//			AppLogger.getSyslogger().error("Convert string to date time", e.getMessage());
		}
		return null;
	}
	
	public static String convertDateTimeToString(Date date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TIME);
			return formatter.format(date);
		}catch (Exception e) {
//			AppLogger.getSyslogger().error("Convert date time to string", e.getMessage());
		}
		return null;
	}
}

