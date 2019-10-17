package com.Utill;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ReusableMethods extends BaseClass{
	
	
	public static int totalScore() {
		Integer energy = CommonMethod.res.path("scores.energy");
		
		if (energy == null ) {
			energy = 0;
			}
		Integer water = CommonMethod.res.path("scores.water");
		if ( water == null ) {
			water = 0;
			}
		
		Integer waste = CommonMethod.res.path("scores.waste");
			if ( waste == null ) {
			waste = 0;
			}
		
		Integer transport = CommonMethod.res.path("scores.transport");
		if ( transport == null ) {
			transport = 0;
		}
		
		Integer human_experience = CommonMethod.res.path("scores.human_experience");
		if ( human_experience == null ) {
			human_experience = 0;
		}
		
		
		Integer base = CommonMethod.res.path("scores.base");
		if ( base == null ) {
			base = 0;
		}
		
		Integer total_score = energy + water + waste + transport + human_experience + base;
		
		System.out.println(total_score);
		return total_score;
		
		}

		
	}


