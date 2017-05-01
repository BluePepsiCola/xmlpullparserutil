package com.xaskysad.parser_use.domain;


import com.xaskysad.xmlparser_master.xmlparser_master.AttributeNode;
import com.xaskysad.xmlparser_master.xmlparser_master.TextNode;
import com.xaskysad.xmlparser_master.xmlparser_master.UserNode;

/**
 * Created by XaskYSab on 2017/3/22 0022.
 */

public class Person {

    @AttributeNode(index = 0)
    public String id;

    @TextNode("name")
    public String name;

    @TextNode("age")
    public String age;


    @UserNode("user")
    public User user;


    /**
     * auto generate...
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", user=" + user +
                '}';
    }
}
