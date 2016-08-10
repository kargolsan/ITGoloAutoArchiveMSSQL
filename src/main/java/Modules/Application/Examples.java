package Modules.Application;

import Modules.Application.Interfaces.ILogService;
import Modules.Application.Models.Services.LogService;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class Examples {
    private void logsExamples(){
        ILogService log = new LogService();
        log.addSuccess("successwewewe");
        log.addError("errorwweew");
        log.addWarning("warningwweew");
        log.addInfo("infowweew");
        log.add("normal");
    }
}
