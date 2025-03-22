package com.pys.course.admin.web.entity.converter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import com.pys.course.admin.web.entity.Days;

public class DaysConverter {
	
  public static List<Days> convertDayMaskToEnums(int dayMask) {
		  
		  EnumSet<Days> weekdayEnumSet = EnumSet.allOf(Days.class);
	        List<Days> dayEnums = new ArrayList<>();

	        for (Days weekday : weekdayEnumSet) {
	            if ((dayMask & (1 << weekday.ordinal())) != 0) {
	                dayEnums.add(weekday);
	            }
	        }


	        return dayEnums;
	    }

  public static String getClassDaysFromEnum(List<Days> days) {
	  
	  String classDays = days.stream()
				.map(Enum::toString)
              .collect(Collectors.joining(","));
		
		
		if(days.containsAll(EnumSet.range(Days.SUNDAY,Days.SATURDAY))) {
			
			classDays = "ALL";
			
		}else if(days.containsAll(EnumSet.range(Days.MONDAY,Days.FRIDAY))) {
			
			classDays = "WEEKDAYS";
			
		}else if(days.containsAll(EnumSet.of(Days.SATURDAY,Days.SUNDAY))){
			
			classDays = "WEEKEND";
		}
		
		return classDays;
	  
  }
}
