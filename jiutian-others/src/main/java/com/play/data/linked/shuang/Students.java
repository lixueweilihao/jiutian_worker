package com.play.data.linked.shuang;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : lihao
 * Created on : 2020-04-13
 * @Description : TODO描述类作用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Students {
    String name = "";
    int credit;
    int age;

    public String show() {
        return "Students{" +
                "name='" + name + '\'' +
                ", credit=" + credit +
                ", age=" + age +
                '}';
    }


}
