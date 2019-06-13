//package com.lihao.protobuf;
//
///**
// * Copyright @ 2018
// * All right reserved.
// *
// * @author Li Hao
// * @since 2018/11/21  11:24
// */
//import com.google.protobuf.InvalidProtocolBufferException;
//
//import java.io.*;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//import java.util.List;
//
//public class test1
//{
//    private static RandomAccessFile rw;
//    private static FileChannel channel;
//    FileOutputStream output;
//
//    public void xuLieHua() throws IOException {
//        PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();
//        builder.setEmail("xiaoxiangzi@email.com");
//        builder.setId(1);
//        builder.setName("筱灬湘子");
//        builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("1001").setType(PersonProbuf.Person.PhoneType.MOBILE));
//        builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("1002").setType(PersonProbuf.Person.PhoneType.HOME));
//        PersonProbuf.Person person = builder.build();
//
//        byte[] bytes = person.toByteArray();
//        String path = System.getProperty("user.dir");
//        File file = new File(path+"\\Protoful\\src\\main\\java\\abc");
//
//        if(!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        FileOutputStream fileOut = new FileOutputStream(file);
//        person.writeDelimitedTo(fileOut);
////        try {
////
////            rw = new RandomAccessFile(file,"rw");
////            person.writeTo(rw);
////            FileChannel channel = rw.getChannel();
////            ByteBuffer by = ByteBuffer.allocate(100);
////            for(byte a : bytes){
////                by.put(a);
////            }
////            channel.write(by);
//////            rw.write(bytes);
////
//////            person.writeTo(output);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }finally {
////            output.close();
////        }
//    }
//    public void fanXuLie(){
//        String path = System.getProperty("user.dir");
//        File file = new File(path+"\\Protoful\\src\\main\\java\\abc");
//        try {
//            FileInputStream inputStream = new FileInputStream(file);
//            PersonProbuf.Person person2 = PersonProbuf.Person.parseDelimitedFrom(inputStream);
//            System.out.println(person2.getEmail()+""+person2.getName()+""+person2.getId());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) throws IOException {
//       test1 test = new test1();
//       test.fanXuLie();
//
//
////        try
////        {
////            PersonProbuf.Person person2 = PersonProbuf.Person.parseFrom(buf);
////
////            System.out.println(person2.getName() + ", " + person2.getEmail());
////
////            List<PersonProbuf.Person.PhoneNumber> lstPhones = person2.getPhoneList();
////
////            for (PersonProbuf.Person.PhoneNumber phoneNumber : lstPhones)
////            {
////                System.out.println(phoneNumber.getNumber());
////            }
////        }
////        catch (InvalidProtocolBufferException e)
////        {
////            e.printStackTrace();
////        }
//
////        System.out.println(buf);
//    }
//}