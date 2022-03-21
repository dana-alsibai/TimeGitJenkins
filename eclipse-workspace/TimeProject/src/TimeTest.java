import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {


		@Test
		public void testGetTotalSecondsGood() {
			int seconds = Time.getTotalSeconds("05:05:05");
			assertTrue("The seconds were not modified properly", 
					seconds == 18305);
		}

		@Test
		public void testGetTotalSecondsBad() {
			assertThrows(StringIndexOutOfBoundsException.class, () -> {
				Time.getTotalSeconds("10:00");

			});

			assertThrows(NumberFormatException.class, () -> {
				Time.getTotalSeconds("10:0:0");

			});
			assertThrows(Exception.class, () -> {
				Time.getTotalSeconds("-3498#");

			});
		}

		@Test
		public void testGetTotalSecondsBoundary() {
			int seconds = Time.getTotalSeconds("05:59:59");
			assertTrue("The seconds were not modified properly", 
					seconds == 21599);
		}

		@ParameterizedTest
		@ValueSource(strings = { "05:00:00", "05:03:59" })
		void testGetSecondsGoodAndBoundry(String candidate) {
			int seconds = Time.getSeconds(candidate);
			assertTrue("The seconds were not calculated properly", seconds == 00 
					|| seconds == 59);
		}

		@Test
		public void testGetSecondsBad() {
			assertThrows(NumberFormatException.class, () -> {
				Time.getTotalMinutes("05:05:3.2");

			});
		}

		@ParameterizedTest
		@ValueSource(strings = { "05:00:00", "05:03:59", "05:03:00" })
		void testGetTotalMinutesGoodAndBoundry(String candidate) {
			int minutes = Time.getTotalMinutes(candidate);
			assertTrue("The minutes were not calculated properly", minutes == 00 
					|| minutes == 03 || minutes == 04);
		}

		@Test
		public void testGetTotalMinutesBad() {
			assertThrows(NumberFormatException.class, () -> {
				Time.getTotalMinutes("10:2:00");

			});
		}

		@ParameterizedTest
		@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
		void testGetTotalHoursGoodAndBoundry(String candidate) {
			int hours = Time.getTotalHours(candidate);
			assertTrue("The hours were not calculated properly", hours == 05
					|| hours == 06);
		}

		@Test
		public void testGetTotalHoursBad() {
			assertThrows(NumberFormatException.class, () -> {
				Time.getTotalHours("2:00:30");

			});
		}

	}
