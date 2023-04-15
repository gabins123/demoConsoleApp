package demoConsoleApp.Core.Data;

import baitap.Customer;
import com.google.gson.Gson;

import java.io.*;
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
    public void edit(String file, T customer, String key) throws IOException {
        Gson g = new Gson();
        var data =g.toJson(customer);
        BufferedReader fileReder = new BufferedReader(new FileReader(file));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        Boolean isFounded = false;
        while ((line = fileReder.readLine()) != null) {
            if(!isFounded  && line.contains(key)){
                inputBuffer.append(data).append('\n');
                isFounded = true;
                continue;
            }
            inputBuffer.append(line).append('\n');
        }
        if(!isFounded)
            inputBuffer.append(data).append('\n');
        fileReder.close();

        FileOutputStream fileOut = new FileOutputStream(file);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();
    }
}
