package demoConsoleApp.Core.Data;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileLoader<T> {
    public List<T> load(String file, Class<T> tClass) throws Exception {
        Gson g = new Gson();
        List<T> datas = new ArrayList<>();
        File fileInput = new File(file);
        Scanner scanner = new Scanner(fileInput);
        while (scanner.hasNext()) {
            datas.add(g.fromJson(scanner.nextLine(), tClass));
        }
        return datas;
    }
}
