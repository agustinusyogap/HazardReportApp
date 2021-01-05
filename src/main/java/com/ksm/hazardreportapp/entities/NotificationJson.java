package com.ksm.hazardreportapp.entities;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class NotificationJson {
    private int id;
    private Date date;
    private String message;
    private int report;
}
