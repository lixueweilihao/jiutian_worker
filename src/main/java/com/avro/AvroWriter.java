package com.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/9  9:40
 */
public class AvroWriter {
    private Schema schema;

    // parser 是专门用来把字符串或者 avsc 文件转换成 schema 对象的一个工具类
    private Schema.Parser parser = new Schema.Parser();

    public AvroWriter() {
        super();
    }

    // 构造方法，初始化schema， 根据要序列化的数据而定
    public AvroWriter(String schameFile) throws Exception {

        this.schema = parser.parse(new File(schameFile));
    }

    public void writeData(GenericRecord record) throws Exception{

        // 这里没有创建 schema 的模式对象，需要使用 GenericDatumWriter 来实例化对象
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>();
        DataFileWriter<GenericRecord> fileWriter = new DataFileWriter<GenericRecord>(writer);

        fileWriter.create(schema, new File("noobjuesrlogaction.avro"));
        fileWriter.append(record);
        fileWriter.flush();
    }

    public static void main(String[] args) throws Exception {

        // 用模式文件的位置作为参数初始化 writer 序列化类
        AvroWriter avroWriter = new AvroWriter("D:/WorkerSpace/play_demo_work/src/main/java/com/avro/user_action_log.avsc");

        // 创建 GenericRecord 对象
        GenericRecord record = new GenericData.Record(avroWriter.schema);
        record.put("userName", "jim");
        record.put("actionType", "new_tweet");
        record.put("ipAddress", "192.168.1.3");
        record.put("gender", 0);
        record.put("provience", "yunnan");

        avroWriter.writeData(record);

    }
}
