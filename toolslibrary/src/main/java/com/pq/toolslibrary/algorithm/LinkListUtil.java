package com.pq.toolslibrary.algorithm;

import java.util.Stack;

/**
 * Created by pan on 2018/5/5.
 * 链表的一些操作
 * 1. 删除单链表中某个结点/值
 * 2。删除单链表中重复的结点/值
 */

public class LinkListUtil {


    /**
     *  定义一个结点
     * @param <T>
     */
    public static class Node<T>{
        T value;
        Node<T> next;
    }

      /*---------------------删除 链表存在的 结点---------------*/

    /**
     * 删除 链表存在的 结点
     * 若是头结点单独处理，其他统一处理，效率没有_2 高
     * @param head
     * @param node
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> void deleteNode_1(Node<T> head, Node<T> node){
        // 若是头结点
        if (null == node || null == head)return;
        if (head == node){
            head=null;
            return;
        }

        Node pre=head;
        while (node != pre.next){
            pre=pre.next;
        }

        pre.next=pre.next.next;

    }

    /**
     * 删除 链表存在的 结点   !! 保证存在
     * @param head
     * @param node
     * @param <T>
     */
    public static <T extends Comparable<T>> void deleteNode_2(Node<T> head, Node<T> node){
        //若是头结点
        if (null == node || null == head)return;
        if (head == node){
            head=null;
            return;
        }

        //若是尾结点，需要遍历
        if (node.next ==null){
            while (head.next != node){
                head = head.next;
            }
            head.next=null;
            return;
        }

        //其他情况  删除node的next结点，注意
        node.value = node.next.value;
        node.next =node.next.next;


    }



/*---------------------删除 链表存在的 结点  over---------------*/



/* ----------------删除一个指定值 （可能有多个）begin    ---------------*/

    /**
     * 删除一个指定值 （可能有多个） 普通遍历
     * @param head  头结点
     * @param value  待删除结点
     * @return true 找到并删除； false 未找到
     */
    public static <T extends Comparable<T>> void deleteNode_1 (Node<T> head, T value){
        if (null == head)return;

        //删除头部的value if exist!
        while (0==value.compareTo(head.value)){
            head = head.next;
        }


        Node<T> pre,cur;
        pre=cur=head;
        while (null!=cur){
            if (0 == value.compareTo(cur.value)){
                pre.next=cur.next;
            }
            pre=cur;
            cur=cur.next;
        }
    }

    /**
     * 删除一个指定值 （可能有多个） 利用栈的特性
     * @param head
     * @param value
     * @param <T>
     */
    public static <T extends Comparable<T>> void deleteNode_2 (Node<T> head, T value){
        Stack<Node> stack = new Stack<>();

        //入栈
        while (null!= head){
            if (0!=value.compareTo(head.value)){
                stack.push(head);
            }
            head = head.next;
        }

        // 重新出栈
        while (!stack.isEmpty()){
            stack.peek().next=head;
            head = stack.pop();
        }

    }


    /* ----------------删除一个指定值 （可能有多个）end    ---------------*/



    /* -------------删除重复的值的结点 begin------------*/





}
