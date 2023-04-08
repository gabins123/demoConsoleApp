package demoConsoleApp.Core.Data;

import baitap.Customer;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class DataSaver<T> {
    public void save(String file, T customer) throws IOException {
        Gson g = new Gson();
        var data =g.toJson(customer);
        File fileOutput = new File(file);
        FileWriter fileWriter = new FileWriter(fileOutput, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data + "\n");
        bufferedWriter.close();
    }
}
