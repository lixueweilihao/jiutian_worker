package com.lihao.protobuf.demo;

import com.lihao.protobuf.AddressBookProtos.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * 使用protobuf类示例: <br />
 * 从控制台输入相关信息，然后将数据序列化到文件。
 * @desc org.chench.test.protobuf.AddPerson
 * @author chench9@lenovo.com
 * @date 2017年6月7日
 */
public class AddPerson {

    static Person PromptForAddress(BufferedReader stdin, PrintStream stdout) throws IOException {
        Person.Builder person = Person.newBuilder();

        stdout.print("Enter person ID: ");
        person.setId(Integer.valueOf(stdin.readLine()));

        stdout.print("Enter name: ");
        person.setName(stdin.readLine());

        stdout.print("Enter email address (blank for none): ");
        String email = stdin.readLine();
        if(email.length() > 0) {
            person.setEmail(email);
        }

        while(true) {
            stdout.print("Enter a phone number (or leave blank to finish): ");

            String number = stdin.readLine();
            if(number.length() == 0) {
                break;
            }

            Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder();
            phoneNumber.setNumber(number);

            stdout.print("Is this a mobile, home, or work phone? ");
            String type = stdin.readLine();
            if("mobile".equalsIgnoreCase(type)) {
                phoneNumber.setType(Person.PhoneType.MOBILE);
            }else if("home".equalsIgnoreCase(type)) {
                phoneNumber.setType(Person.PhoneType.HOME);
            }else if("work".equalsIgnoreCase(type)) {
                phoneNumber.setType(Person.PhoneType.WORK);
            }

            person.addPhones(phoneNumber);
        }

        return person.build();
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        AddressBook.Builder addressBook = AddressBook.newBuilder();
//        try {
//            // 从指定文件读取数据
//            addressBook.mergeFrom(new FileInputStream(args[0]));
//        } catch (FileNotFoundException e) {
//            System.out.println(args[0] + ": File not found.  Creating a new file.");
//            e.printStackTrace();
//        }

        addressBook.addPeople(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                System.out));

        FileOutputStream out = new FileOutputStream(args[0]);
        addressBook.build().writeTo(out);
    }

}