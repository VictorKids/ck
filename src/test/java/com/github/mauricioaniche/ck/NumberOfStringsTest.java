package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberOfStringsTest extends BaseTest {

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/strings");
	}

	@Test
	public void count() {
		CKClassResult a = report.get("strings.Strings");

		Assertions.assertEquals(4, a.getStringLiteralsQty());

		Assertions.assertEquals(2, a.getMethod("m1/0").get().getStringLiteralsQty());
		Assertions.assertEquals(2, a.getMethod("m2/0").get().getStringLiteralsQty());
		Assertions.assertEquals(0, a.getMethod("m3/0").get().getStringLiteralsQty());

	}
}
