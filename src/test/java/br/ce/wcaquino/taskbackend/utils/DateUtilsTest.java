package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;
import org.junit.Test;
import org.junit.Assert;

public class DateUtilsTest {
	
	@Test
	public void returnTrueFutureDates() {
		LocalDate date = LocalDate.of(2030, 01, 01);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void returnFalsePastDates() {
		LocalDate date = LocalDate.of(2010, 01, 01);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void returnTruePresentDates() {
		LocalDate date = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
}