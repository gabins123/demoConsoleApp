package demoConsoleApp.Core.Data;

import baitap.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseLocal {

    public <T> List<T> load(String path, Class<T> classes)
    {
        FileLoader<T> loader = new FileLoader<>();
        try{
            return loader.load(path, classes);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
    public <T> boolean set(String path,T data)
    {
        var saver = new DataSaver<T>();
        try {
            saver.save(path,data);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public <T> boolean set(String path,T data, String key )
    {
        var saver = new DataSaver<T>();
        try {
            saver.edit(path,data,key);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public <T> boolean delete(String path, String key )
    {
        var saver = new DataSaver<T>();
        try {
            saver.delete(path,key);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
