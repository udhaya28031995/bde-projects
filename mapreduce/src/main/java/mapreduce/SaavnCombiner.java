package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SaavnCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

	// The input key class is Text (Each word acts as key)
	// The input value class is IntWritable, for each word 1
	// The output key class is Text
	// The output value class is IntWritable

	private IntWritable result = new IntWritable();

	// this is used to count the occurances of each word

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;

		// initially sum is set as 0, before we start
		// counting teh occurances

		for (IntWritable val : values) {
			sum += val.get();

			// for each occurance we are adding the value (which is 1)
			// so, in other words we are adding 1 for each occurance of a
			// particular word
		}
		result.set(sum);

		// putting the value of result as the number of occurances found

		context.write(key, result);

		// writing the result

	}
}

