/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sequenceGenrator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author nicsi
 */
public class GetCurrentDateTime {
Logger logger = Logger.getLogger(GetCurrentDateTime.class.getName());
    public  String getDate() {
        // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String c_date = dateFormat.format(date);
        System.out.println("c_Date   " + c_date);
        return c_date;

    }

    public  String getTime() {

        // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        // DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        String c_time = dateFormat.format(date);
        System.out.println("c_time    " + c_time);
        return c_time;

    }

}
