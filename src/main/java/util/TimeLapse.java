package util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeLapse {
	
	
	public String between(Date startDate, Date endDate){

		Date d1 = startDate;
		Date d2 = endDate;
		String timeLapse = "";
 
		try {

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
 
			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
			
			timeLapse = diffDays +" Days " + diffHours + " Hours " + diffMinutes + " Minutes " + diffSeconds 
					+ " Seconds";
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
		
		return timeLapse;
	}
	
	
	public long getMinutesBetween(LocalDateTime reportedInstant, LocalDateTime closedInstant){
		
		LocalDateTime d1 = reportedInstant;
		LocalDateTime d2 = closedInstant;
		
		long minutesSince = d1.until(d2, ChronoUnit.MINUTES);
		return minutesSince;
	}
	
	public long getMinutesBetween(Date reportedInstant, Date closedInstant){
		
		DateConverter dateConverter = new DateConverter();
		
		
		LocalDateTime d1 = dateConverter.dateToLocalDateTime(reportedInstant);
		LocalDateTime d2 = dateConverter.dateToLocalDateTime(closedInstant);
		
		long minutesSince = d1.until(d2, ChronoUnit.MINUTES);

		return minutesSince;
	}
	

}
