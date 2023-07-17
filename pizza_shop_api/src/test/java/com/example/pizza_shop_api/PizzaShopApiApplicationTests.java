package com.example.pizza_shop_api;

import com.example.pizza_shop_api.login_and_register.LoginController;
import com.example.pizza_shop_api.login_and_register.UserRepository;
import com.example.pizza_shop_api.login_and_register.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@SpringBootTest
class PizzaShopApiApplicationTests {


	@Mock
	private UserRepository userRepository;

	private LoginController loginController;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		loginController = new LoginController(userRepository);
	}

	@Test
	void testGetUser() {
		// Arrange
		String expectedUsername = "Frederick";
		int password = 123456;

		// Act
		String actualUsername = loginController.getUser(expectedUsername, password);

		// Assert
		assertEquals(expectedUsername, actualUsername);
	}




	@Test
	void testAddNewUser_WhenUserDoesNotExist() {
		// Arrange
		String userName = "John";
		int password = 123456;
		when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());

		// Act
		String result = loginController.addNewUser(userName, password);

		// Assert
		Optional<UserService> existingUser = userRepository.findByUserName(userName);
		assertEquals(false, existingUser.isPresent());
		assertEquals("User saved", result);
	}

}
