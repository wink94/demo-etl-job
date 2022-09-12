package com.windula.demoetl;

import com.windula.demoetl.steps.FetchDataInitializeStep;
import com.windula.demoetl.steps.FetchDataStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ETLProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(ETLProcess.class);

    @Autowired
    private FetchDataInitializeStep fetchDataInitializeStep;
    @Autowired
    private FetchDataStep fetchDataStep;
    public void startETLProcess(){
        executeSteps();
    }

    private void executeSteps(){
        try {
            fetchDataInitializeStep.execute();
            fetchDataStep.execute();
        } catch (InterruptedException e){
            LOGGER.error("Interrupted exception", e);
            Thread.currentThread().interrupt();
        }
        catch (Exception e) {
            LOGGER.error("process failed ",  e);
        }
    }
}
