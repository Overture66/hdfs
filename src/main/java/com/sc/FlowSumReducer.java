package com.sc;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowSumReducer extends Reducer<Text, Flow, Text, Flow> {

    @Override
    protected void reduce(Text key, Iterable<Flow> values,
                          Context context)
            throws IOException, InterruptedException {
        //  <phone:{flow,flow,flow,flow}>
        // reduce中的业务逻辑就是遍历values，然后进行累加求和再输出
        long up = 0;//
        long down = 0;
        for (Flow flow : values) {
            up += flow.getUp();
            down += flow.getDown();
        }
        context.write(key, new Flow(key.toString(), up, down));

    }

}


