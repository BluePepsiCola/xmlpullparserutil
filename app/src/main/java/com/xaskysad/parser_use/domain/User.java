package com.xaskysad.parser_use.domain;


import com.xaskysad.xmlparser_master.xmlparser_master.AttributeNode;
import com.xaskysad.xmlparser_master.xmlparser_master.TextNode;

/**
 * Created by XaskYSab on 2017/3/23 0023.
 */

public class User {

    @AttributeNode(index = 0)
    public String userId;

    @TextNode("sex")
    public String sex;

    @TextNode("id_car")
    public String id_car;


    /**
     * auto generate...
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId_car() {
        return id_car;
    }

    public void setId_car(String id_car) {
        this.id_car = id_car;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", sex='" + sex + '\'' +
                ", id_car='" + id_car + '\'' +
                '}';
    }
}
