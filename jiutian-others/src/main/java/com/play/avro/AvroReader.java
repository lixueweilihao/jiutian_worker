package com.play.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;

import java.io.File;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/9  10:13
 */
public class AvroReader {
    private static String schemaString="{\"type\":\"record\",\"name\":\"sUserActionLog\",\"namespace\":\"com.zhiyou100.bd17.scheme\",\"fields\":[{\"name\":\"userName\",\"type\":\"string\"},{\"name\":\"actionType\",\"type\":\"string\"},{\"name\":\"ipAddress\",\"type\":\"string\"},{\"name\":\"gender\",\"type\":\"int\"}]}";
//    private static String schemaString="{\"type\":\"record\",\"name\":\"sUserActionLog\",\"namespace\":\"com.zhiyou100.bd17.scheme\",\"fields\":[{\"name\":\"userName\",\"type\":\"string\"},{\"name\":\"actionType\",\"type\":\"string\"},{\"name\":\"ipAddress\",\"type\":\"string\"},{\"name\":\"gender\",\"type\":\"int\"},{\"name\":\"provience\",\"type\":\"string\"},{\"name\":\"description\",\"type\":\"string\",\"default\":\"\"}]}";
    public static void main(String[] args) throws Exception {
        Schema.Parser parser = new Schema.Parser();
        Schema newSchema = parser.parse(schemaString);
        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(null,newSchema);
        DataFileReader<GenericRecord> fileReader = new DataFileReader<GenericRecord>(new File("noobjuesrlogaction.avro"), reader);

        GenericRecord record = null;

        while(fileReader.hasNext()) {

            record = fileReader.next();
            System.out.println(record);
        }
        fileReader.close();
    }
}
