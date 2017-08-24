package com.qcosmos.vpb.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vpb on 8/22/17.
 */

public class ToDoItem implements Serializable {
    String item;
    Integer priority = 1;

    ToDoItem(String item) {
        this.item = Utils.capitalizeString(item);
    }

    public String toString() {
        return "item-value = " + item;
    }

    public static void saveAll(Context context, List<ToDoItem> toDoItemList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(GlobalData.FILE_NAME, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(toDoItemList);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ToDoItem> loadAll(Context context) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<ToDoItem> items = new ArrayList<ToDoItem>();
        try {
            fis = context.openFileInput(GlobalData.FILE_NAME);
            ois = new ObjectInputStream(fis);
            items = (List<ToDoItem>) ois.readObject();
            System.out.println("retrieved = " + items);
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return items;
        }
    }
}
