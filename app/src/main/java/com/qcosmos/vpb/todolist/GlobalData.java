package com.qcosmos.vpb.todolist;

import java.util.ArrayList;

/**
 * Created by vpb on 8/22/17.
 */

public final class GlobalData {
    public static final String INDEX_KEY = "index";
    public static final int ILLEGAL_INDEX = -1;
    public static final String ITEM_KEY = "item";
    public static final int REQUEST_CODE = 99;
    public static final String FILE_NAME = "todoList3.ser"; //"todolist.txt

    //ArrayList for list of to-do items
    public static ArrayList<ToDoItem> todoItems = new ArrayList<ToDoItem>();

}
