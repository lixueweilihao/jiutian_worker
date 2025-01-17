package com.lihao.protobuf.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lihao.protobuf.UserProto;

import java.io.Serializable;

/**
 * @Author : lihao
 * Created on : 2020-05-28
 * @Description : TODO描述类作用
 */
public class User implements Serializable,Protobufable{
    private static final long serialVersionUID = 468062760765055608L;

    private Long id;

    private String name;

    private String email;
    /** {0:男,1:女} **/
    private Integer sex;

    public User() {}

    public User(Long id, String name, String email, Integer sex) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
    }

    /***
     * 将byte解析为User对象
     * @param bytes
     */
    public User(byte[] bytes) {
        try {
            //将字节数组转换为UserProto.User对象
            UserProto.User user = UserProto.User.parseFrom(bytes);
            //UserProto.User对象转化为自己的User对象
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.sex = user.getSex();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    /** 编码 */
    @Override
    public byte[] encode() {
        UserProto.User.Builder builder = UserProto.User.newBuilder();
        builder.setId(id);
        builder.setName(name);
        builder.setEmail(email);
        builder.setSex(sex);
        return builder.build().toByteArray();
    }

    @Override
    public String toString() {
        return "[ID:" + id + ", 姓名：" + name + ", 性别：" + (sex==0?"男":"女") + ", 邮箱：" + email + "]";
    }

    /********************** getter & setter******************************/
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getSex() { return sex; }
    public void setSex(Integer sex) { this.sex = sex; }
    /********************** getter & setter******************************/
}

