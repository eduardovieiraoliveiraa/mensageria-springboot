package com.eduardooliveiradev.programacaofuncional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class SupplierTest {
	
	@Test
	public void testDateFormated() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		Supplier<String> supplier = () -> dateTimeFormatter.format(LocalDateTime.now());
		assertEquals(dateTimeFormatter.format(LocalDateTime.now()), supplier.get());
	}

}
