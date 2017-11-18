package mikelaud.domain.tags;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Consumer;

import org.junit.Test;

public class PredefinedTagTypeTest {

	private void iterate(Consumer<PredefinedTagType> aConsumer) {
		for (PredefinedTagType tagType : PredefinedTagType.values()) {
			aConsumer.accept(tagType);
		}
	}
	
	@Test
	public void testConstructor() {
		iterate(resolution -> assertNotNull(resolution));
	}

	@Test
	public void testToString() {
		iterate(resolution -> {
			assertNotNull(resolution.toString());
			assertTrue(resolution.toString().length() > 0);			
		});
	}

}
