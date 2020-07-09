package mapreduce;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SaavnReducer extends Reducer<Text, IntWritable, IntWritable,Text> {
	private TreeMap<Integer, String> tmap2; 
    @Override
    public void setup(Context context) throws IOException, 
                                     InterruptedException 
    { 
        tmap2 = new TreeMap<Integer, String>(); 
    } 
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		
		int count = 0; 
		String name = key.toString(); 
        for (IntWritable val : values) 
        { 
            count = val.get(); 
        } 
        tmap2.put(count, name); 
        if (tmap2.size() > 100) 
        { 
            tmap2.remove(tmap2.firstKey()); 
        } 
		
	}
	  @Override
	    public void cleanup(Context context) throws IOException, 
	                                       InterruptedException 
	    { 
	  
	        for (Map.Entry<Integer, String> entry : tmap2.entrySet())  
	        { 
	  
	            int count = entry.getKey(); 
	            String name = entry.getValue(); 
	            context.write(new IntWritable(count), new Text(name)); 
	        } 
	    } 
}

