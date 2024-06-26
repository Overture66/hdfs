package com.sc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class FlowSumRunner extends Configured implements Tool{


    @Override
    public int run(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowSumRunner.class);

        job.setMapperClass(FlowSumMapper.class);
        job.setReducerClass(FlowSumReducer.class);

        //设置map程序的输出key、value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Flow.class);

        //设置   输出 key、value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Flow.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));//输入数据路径     /flow/input

        //检查一下参数所指定的输出路径是否存在，如果已存在，先删除
        Path output = new Path(args[1]);
        FileSystem fs = FileSystem.get(conf);
        if(fs.exists(output)){
            fs.delete(output, true);
        }

        FileOutputFormat.setOutputPath(job, new Path(args[1]));//输出数据路径   /flow/output

        return job.waitForCompletion(true)?0:1;
    }


    public static void main(String[] args) throws Exception {
        int  status = ToolRunner.run(new Configuration(), new FlowSumRunner(), args);
        System.exit(status);
    }
}

