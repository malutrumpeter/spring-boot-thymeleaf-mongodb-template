package util;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.core.convert.converter.Converter;

public class DateConverter {


    public LocalDateTime dateToLocalDateTime(Date d) { 
    	Date date = d;
    	Instant instant = Instant.ofEpochMilli(date.getTime());
    	LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    	
        return ldt;
    }
    

 	public Date localDateTimeToDate(LocalDateTime ldt) { 
    	
    	LocalDateTime localDateTime = ldt;
    	Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    	Date date = Date.from(instant);
    	
    	return date;
    }
 	
 	public Date localDateToDate(LocalDate ld){
 		LocalDate localDate = ld;
 		Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
 		Date date = Date.from(instant);
 		
 		return date;
 	}
 	
 	public LocalDate dateToLocalDate(Date d){
 		Date date = d;
 		Instant instant = Instant.ofEpochMilli(date.getTime());
 		LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
 		
 		return localDate; 		
 	}
 	
 	public LocalTime dateToLocalTime(Date d){ 		
 		Date date = d;
 		Instant instant = Instant.ofEpochMilli(date.getTime());
 		LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
 		
 		return localTime;
 		
 	}
 	
 	public Date localTimeToDate(LocalTime lt){
 		
 		int A_YEAR = LocalDateTime.now().getYear();
 		int A_MONTH = LocalDateTime.now().getMonthValue();
 		int A_DAY = LocalDateTime.now().getDayOfMonth();
 		
 		LocalTime localTime = lt;
 		Instant instant = localTime.atDate(LocalDate.of(A_YEAR, A_MONTH, A_DAY)).
 		        atZone(ZoneId.systemDefault()).toInstant();
 		Date date = Date.from(instant);
 		
 		return date;
 	}
 	
}