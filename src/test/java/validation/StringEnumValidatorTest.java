package validation;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringEnumValidatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		User user = new User("xiaoming", "F");
		Set<ConstraintViolation<User>> result = factory.getValidator()
				.validate(user);
		assertTrue(null != result);

		user = new User("xiaoming", "Male");
		result = factory.getValidator().validate(user);
		assertTrue(null != result);
		assertEquals(0, result.size());
	}

}
