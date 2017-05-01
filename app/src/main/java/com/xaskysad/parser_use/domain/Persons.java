package com.xaskysad.parser_use.domain;


import com.xaskysad.xmlparser_master.xmlparser_master.TList;

import java.util.ArrayList;

/**
 * Created by XaskYSab on 2017/3/22 0022.
 */

public class Persons {

    @TList("person")
    public ArrayList<Person> list;

    public ArrayList<Person> getList() {
        return list;
    }

    public void setList(ArrayList<Person> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "list=" + list +
                '}';
    }
}



