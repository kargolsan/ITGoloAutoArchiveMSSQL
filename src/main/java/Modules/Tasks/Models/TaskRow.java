package Modules.Tasks.Models;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class TaskRow {

    private final SimpleLongProperty id;
    private final SimpleStringProperty server;
    private final SimpleStringProperty instance;
    private final SimpleStringProperty database;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;
    private final SimpleStringProperty savePath;
    private final SimpleStringProperty savePathReserve;
    private final SimpleStringProperty daysOfWeek;
    private final SimpleStringProperty hours;


    public TaskRow(Long id,
                   String server,
                   String instance,
                   String database,
                   String userName,
                   String password,
                   String savePath,
                   String savePathReserve,
                   String daysOfWeek,
                   String hours
    ) {

        this.id = new SimpleLongProperty(id);
        this.server = new SimpleStringProperty(server);
        this.instance = new SimpleStringProperty(instance);
        this.database = new SimpleStringProperty(database);
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.savePath = new SimpleStringProperty(savePath);
        this.savePathReserve = new SimpleStringProperty(savePathReserve);
        this.daysOfWeek = new SimpleStringProperty(daysOfWeek);
        this.hours = new SimpleStringProperty(hours);
    }

    public Long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getServer() {
        return server.get();
    }

    public SimpleStringProperty serverProperty() {
        return server;
    }

    public void setServer(String server) {
        this.server.set(server);
    }

    public String getInstance() {
        return instance.get();
    }

    public SimpleStringProperty instanceProperty() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance.set(instance);
    }

    public String getDatabase() {
        return database.get();
    }

    public SimpleStringProperty databaseProperty() {
        return database;
    }

    public void setDatabase(String database) {
        this.database.set(database);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getSavePath() {
        return savePath.get();
    }

    public SimpleStringProperty savePathProperty() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath.set(savePath);
    }

    public String getSavePathReserve() {
        return savePathReserve.get();
    }

    public SimpleStringProperty savePathReserveProperty() {
        return savePathReserve;
    }

    public void setSavePathReserve(String savePathReserve) {
        this.savePathReserve.set(savePathReserve);
    }

    public String getDaysOfWeek() {
        return daysOfWeek.get();
    }

    public SimpleStringProperty daysOfWeekProperty() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek.set(daysOfWeek);
    }

    public String getHours() {
        return hours.get();
    }

    public SimpleStringProperty hoursProperty() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours.set(hours);
    }
}
