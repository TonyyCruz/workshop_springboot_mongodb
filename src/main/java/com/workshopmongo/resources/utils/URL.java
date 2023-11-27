package com.workshopmongo.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

public class URL {

  private URL() {}

  public static String decodeParam(String text) {
    try {
      return URLDecoder.decode(text, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static Instant stringToDate(String stringDate, Instant defaultDate) {
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    formater.setTimeZone(TimeZone.getTimeZone("GMT"));
    try {
      return formater.parse(stringDate).toInstant();
    } catch (ParseException e) {
      return defaultDate;
    }
  }
}
