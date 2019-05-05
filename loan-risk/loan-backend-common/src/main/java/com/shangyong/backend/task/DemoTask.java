package com.shangyong.backend.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DemoTask {

	/**
	 * 打印格式化后的时间
	 */
    public void reportCurrentByCron(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat().format(new Date()));
    }

    /**
     * 时间格式化
     * @return
     */
    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
