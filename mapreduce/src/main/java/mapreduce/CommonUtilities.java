package mapreduce;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommonUtilities {
	private static final Logger LOG = Logger.getLogger(CommonUtilities.class.getName());
	public static List<String> splitData(String record) {
		if (record.toString().contains(",")) {
			List<String> songstream = Arrays.asList(record.toString().split(","));
			return songstream;
		}
		LOG.log(Level.INFO, "invalid record format" + record.toString());
		return null;

	}

	public static boolean checkValidRecord(List<String> value) {
		boolean flag = false;
		if (value.size() == 5) {

			if (value.get(0) != "" && value.get(3) != "" && value.get(4) != "") {

				try {
					Date date = null;
					date = new SimpleDateFormat("yyyy-MM-dd").parse(value.get(4));

					flag = true;

				} catch (Exception ex) {
					LOG.log(Level.INFO, "invalid date format in record" + value.toString());
					flag = false;

				}

			} else {
				LOG.log(Level.INFO, "invalid record - record does not have all values" + value.toString());
				flag = false;
			}
		}
		return flag;

	}

	public static int getStreamInterval(int playedHour) {
		int interval = 0;
		if (playedHour >= 00 && playedHour <= 05) {

			interval = 1;
		} else if (playedHour >= 06 && playedHour <= 11) {
			interval = 2;
		} else if (playedHour >= 12 && playedHour <= 17) {
			interval = 3;
		} else if ((playedHour >= 18 && playedHour <= 23)) {
			interval = 4;
		}

		return interval;

	}

	public static String getDateFromStream(String date) {
		String streamDate = null;
		if (date.contains("-")) {
			List<String> dates = Arrays.asList(date.split("-"));
			streamDate = dates.get(2);
		}
		return streamDate;

	}

}
