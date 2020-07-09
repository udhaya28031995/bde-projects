package mapreduce;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;




	public class SaavnMapper extends Mapper<Object, Text, Text, IntWritable> {

		private static final Logger LOG = Logger.getLogger(SaavnMapper.class.getName());
		private final static IntWritable one = new IntWritable(1);
/*		private Text word = new Text();*/

	
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

			List<String> record = CommonUtilities.splitData(value.toString());
			if (record.equals(null)) {
				LOG.log(Level.INFO, "skipping record - invalid type" + value.toString());
			} else {
				boolean isvalid = CommonUtilities.checkValidRecord(record);
				if (isvalid) {
					int selectInterval = CommonUtilities.getStreamInterval(Integer.parseInt(record.get(3)));
					if (selectInterval == 0) {
						LOG.log(Level.INFO, "skipping record - invalid stream played hour" + value.toString());
					} else {

						String date = CommonUtilities.getDateFromStream(record.get(4));

						context.write(new Text(date + "," + record.get(0) + "," + selectInterval), one);

					}
				} else {
					LOG.log(Level.INFO, "skipping record - invalid type" + value.toString());
				}	

			}
		}
	}

