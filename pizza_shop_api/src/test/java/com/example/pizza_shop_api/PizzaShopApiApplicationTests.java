package com.example.pizza_shop_api;

import com.example.pizza_shop_api.login_and_register.LoginController;
import com.example.pizza_shop_api.model.User;
import com.example.pizza_shop_api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
	void testAddNewUser_WhenUserDoesNotExist() {
		// Arrange
		String userName = "John";
		int password = 123456;
		when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());

		// Act
		String result = loginController.addNewUser(userName, password);

		// Assert
		Optional<User> existingUser = userRepository.findByUserName(userName);
		assertEquals(false, existingUser.isPresent());
		assertEquals("User saved", result);
	}

}
