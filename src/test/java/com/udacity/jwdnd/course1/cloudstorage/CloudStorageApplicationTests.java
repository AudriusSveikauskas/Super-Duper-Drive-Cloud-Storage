package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver webDriver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.webDriver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.webDriver != null) {
			webDriver.quit();
		}
	}

	@Test
	@Order(1)
	public void getSignupPage() {
		webDriver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", webDriver.getTitle());
	}

	@Test
	@Order(2)
	public void getLoginPage() {
		webDriver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", webDriver.getTitle());
	}

	@Test
	@Order(3)
	public void getHomePage() {
		webDriver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", webDriver.getTitle());
	}

}
