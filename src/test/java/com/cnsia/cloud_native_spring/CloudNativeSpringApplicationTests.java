package com.cnsia.cloud_native_spring;


import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import com.cnsia.cloud_native_spring.models.Book;

@SpringBootTest
class CloudNativeSpringApplicationTests {
	private static Validator validator;

	@BeforeAll // 가장 먼저 실행되는 메소드
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();	// 기본 ValidatorFactory를 생성
		validator = factory.getValidator();										// ValidatorFactory로부터 Validator를 생성
	}

	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		Book book = new Book("1234567890", "Cloud Native Spring in Action", "Thomas Vitale", 9.99);	// 유효한 book 객체 생성
		Set<ConstraintViolation<Book>> violations = validator.validate(book);						// book 객체를 validate 메소드에 전달하여 유효성 검사를 수행
		assertThat(violations.isEmpty());															// violations 객체가 비어있는지 확인
	}

	@Test
	void whenIsbnDefineButIncorrectThenValidationFails() {
		Book book = new Book("A123456789", "Cloud Native Spring in Action", "Thomas Vitale", 9.99);	// isbn 필드가 잘못된 book 객체 생성
		Set<ConstraintViolation<Book>> violations = validator.validate(book);						// book 객체를 validate 메소드에 전달하여 유효성 검사를 수행
		assertThat(violations).hasSize(1);															// violations 객체의 크기가 1인지 확인
		assertThat(violations.iterator().next().getMessage())
			.isEqualTo("The book ISBN must be a 10 or 13 digit number");							// violations 객체의 첫 번째 메시지가 "The book ISBN must be a 10 or 13 digit number"인지 확인
	}
}
