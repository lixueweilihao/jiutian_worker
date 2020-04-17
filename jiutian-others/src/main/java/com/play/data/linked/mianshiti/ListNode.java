package com.play.data.linked.mianshiti;

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
public class ListNode {
    ListNode next;
    int val;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
