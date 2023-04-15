package demoConsoleApp.Core;

import static java.nio.file.StandardCopyOption.*;
import demoConsoleApp.Core.Data.DataAPI;
import demoConsoleApp.Core.Data.DataPath;
import demoConsoleApp.Utility.FileUtility;
import demoConsoleApp.Window.CreateSavingAccountWindow;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class InterestConfig {
    private List<InterestConfigRecord> records;
    public List<InterestConfigRecord> getRecords() {
        return Collections.unmodifiableList(records);
    }
    private boolean isInitialized;
    private static InterestConfig instance;
    private final int REFRESH_RATE = 10 * 1000;
    private Timer timer;

    public static InterestConfig getInstance() {
        if (instance == null) {
            instance = new InterestConfig();
            instance.initialize();
        }
        return instance;
    }

    public void initialize() {
        if (isInitialized)
            return;
        try {
            File theDir = new File(DataPath.TEMP_INTEREST_CONFIG);
            if (!theDir.exists()){
                var e = theDir.mkdirs();
                System.out.println(e);
            }
            Files.copy(Path.of(DataPath.INTEREST_CONFIG), Path.of(DataPath.TEMP_INTEREST_CONFIG), REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            records = new ArrayList<>();
            return;
        }
        isInitialized = true;
        records = DataAPI.load(DataPath.TEMP_INTEREST_CONFIG, InterestConfigRecord.class);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(!FileUtility.compareTwoFiles(DataPath.INTEREST_CONFIG, DataPath.TEMP_INTEREST_CONFIG)){
                        System.out.println("Refresh rate config");
                        records = DataAPI.load(DataPath.INTEREST_CONFIG, InterestConfigRecord.class);
                        Files.copy(Path.of(DataPath.INTEREST_CONFIG), Path.of(DataPath.TEMP_INTEREST_CONFIG), REPLACE_EXISTING);
                        for (var configObservers : configObservers ) {
                            configObservers.OnRefresh(records);
                        }
                    }
                } catch (IOException e) {
                    isInitialized = false;
                    System.out.println(e.getMessage());
                }
            }
        }, REFRESH_RATE, REFRESH_RATE);
    }

    public InterestConfigRecord getRecord(int id) {
        for (var rc : records) {
            if (rc.getID() == id)
                return rc;
        }
        return null;
    }
    protected void finalize()
    {
        timer.cancel();
    }
    List<IConfigObserver<InterestConfigRecord>> configObservers = new ArrayList<>();
    public void registered(IConfigObserver<InterestConfigRecord> createSavingAccountWindow) {
        if(!configObservers.contains(createSavingAccountWindow)){
            configObservers.add(createSavingAccountWindow);
        }
    }

    public void unregistered(CreateSavingAccountWindow createSavingAccountWindow) {
            configObservers.remove(createSavingAccountWindow);
    }
//    public void ConfigInitializeSetup() {
//        DataAPI.set(DataPath.INTEREST_CONFIG, new InterestConfigRecord(1,7, Calendar.DATE, 0.2f ));
//        DataAPI.set(DataPath.INTEREST_CONFIG, new InterestConfigRecord(2,1, Calendar.MONTH, 5.5f ));
//        DataAPI.set(DataPath.INTEREST_CONFIG, new InterestConfigRecord(3,6, Calendar.MONTH, 7.5f ));
//        DataAPI.set(DataPath.INTEREST_CONFIG, new InterestConfigRecord(4,1, Calendar.YEAR, 7.9f ));
//    }
}
