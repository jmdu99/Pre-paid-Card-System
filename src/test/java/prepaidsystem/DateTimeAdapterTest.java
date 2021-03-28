package prepaidsystem;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTimeAdapterTest {
  static DateTimeAdapter d=new DateTimeAdapter();
	@Test
	@DisplayName("Test DateTimeAdapter")
	void dateTimeAdapterTest() {
		assertNotNull(d);
	}
	@Test
	@DisplayName("Test DateTimeAdapter2")
	void dateTimeAdapterTest2() throws Exception {
		Date date = new Date();
		d.marshal(date);
		assertNotNull(date);
		String xml = "21-12-2019 15:45:30";
		d.unmarshal(xml);
		assertNotNull(xml);
	}
}
