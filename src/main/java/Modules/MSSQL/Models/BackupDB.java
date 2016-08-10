package Modules.MSSQL.Models;

import Modules.MSSQL.Interfaces.IBackupDB;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class BackupDB implements IBackupDB {

    /**
     * Run auto backup DB
     */

    public void run() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(()-> {
            for (Integer i=0 ; i < 10 ; i++){
                try {
                    System.out.println(i);
                    Thread.sleep(1000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
