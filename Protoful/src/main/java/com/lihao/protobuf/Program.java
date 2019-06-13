package com.lihao.protobuf;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.lihao.protobuf.PersonProtos.*;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2019/1/14  11:45
 */
public class Program {

    public static void main(String[] args) throws Exception {
        Person person1 = Person.newBuilder()
                .setName("Dong Xicheng")
                .setEmail("dongxicheng@yahoo.com")
                .setId(11111)
                .addPhone(Person.PhoneNumber.newBuilder()
                        .setNumber("15110241024")
                        .setType(0))
                .addPhone(Person.PhoneNumber.newBuilder()
                        .setNumber("01025689654")
                        .setType(1)).build();
        try {
            FileOutputStream output = new FileOutputStream("example.txt");
            person1.writeTo(output);
            output.close();
        } catch(Exception e) {
            System.out.println("Write Error！");
        }
        try {
            FileInputStream input = new FileInputStream("example.txt");
            Person person2 = Person.parseFrom(input);
            System.out.println("person2:" + person2);
        } catch(Exception e) {
            System.out.println("Read Error!");
        }
    }
}