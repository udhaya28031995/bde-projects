package mapreduce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class SaavnPartitioner extends Partitioner<Text,IntWritable > implements
Configurable {

private Configuration configuration;
HashMap<String, Integer> months = new HashMap<String, Integer>();

/**
* Set up the months hash map in the setConf method.
*/
public void setConf(Configuration configuration) {
this.configuration = configuration;
months.put("01", 0);
months.put("02", 1);
months.put("03", 2);
months.put("04", 3);
months.put("05", 4);
months.put("06", 5);
months.put("07", 6);
months.put("08", 7);
months.put("09", 8);
months.put("10", 9);
months.put("11", 10);
months.put("12", 11);
months.put("13", 12);
months.put("14", 13);
months.put("15", 14);
months.put("16", 15);
months.put("17", 16);
months.put("18", 17);
months.put("19", 18);
months.put("20", 19);
months.put("21", 20);
months.put("22", 21);
months.put("23", 22);
months.put("24", 23);
months.put("25", 24);
months.put("26", 25);
months.put("27", 26);
months.put("28", 27);
months.put("29", 28);
months.put("30", 29);
months.put("31", 30);
}

/**
* Implement the getConf method for the Configurable interface.
*/
public Configuration getConf() {
return configuration;
}

/**
* You must implement the getPartition method for a partitioner class.
* This method receives the three-letter abbreviation for the month
* as its value. (It is the output value from the mapper.)
* It should return an integer representation of the month.
* Note that January is represented as 0 rather than 1.
* 
* For this partitioner to work, the job configuration must have been
* set so that there are exactly 12 reducers.
*/
@Override
public int getPartition(Text key, IntWritable value, int numPartitions) {
// TODO Auto-generated method stub

List<String> date = Arrays.asList(key.toString().split(","));	
return (int) (months.get(date.get(0)));

}
}
