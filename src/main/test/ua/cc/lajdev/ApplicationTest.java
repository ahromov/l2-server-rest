package ua.cc.lajdev;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.cc.lajdev.game.service.CastleService;

class ApplicationTest {

	@Autowired
	private CastleService cs;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCSgetAll() {
	}

	public CastleService getCs() {
		return cs;
	}

	public void setCs(CastleService cs) {
		this.cs = cs;
	}

}
