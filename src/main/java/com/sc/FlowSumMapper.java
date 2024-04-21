package com.sc;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.commons.lang3.StringUtils;

public class FlowSumMapper extends Mapper<LongWritable, Text, Text, Flow>{

	@Override
	protected void map(LongWritable key, Text value,
					   Context context)
			throws IOException, InterruptedException {
		//拿一行数据
		String line = value.toString();
		//切分成各个字段
		String[] fields = StringUtils.split(line, "\t");

		//拿到我们需要的字段
		String phone = fields[1];
		long  up= Long.parseLong(fields[8]);
		long  down = Long.parseLong(fields[9]);
		//封装数据为kv并输出        <phone:flow>
		context.write(new Text(phone), new Flow(phone,up,down));
	}
}

