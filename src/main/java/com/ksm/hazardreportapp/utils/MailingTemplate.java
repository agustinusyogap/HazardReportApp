/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.utils;

import java.util.Date;

/**
 *
 * @author kelvi
 */
public class MailingTemplate {

    public static String newReportToHSE(Date date, String originator, String desc, int reportId) {

        String template = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "  <head>"
                + "    <meta charset='UTF-8' />"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />"
                + "    <title>Document</title>"
                + "    <style>"
                + "      body{"
                + "        font-family: 'Montserrat', sans-serif;  "
                + "      }"
                + "      .title {"
                + "        width: 100%;"
                + "        padding: 10px;"
                + "        background-color: #51cbce !important;"
                + "        color: #fff;"
                + "        margin-bottom: 20px;"
                + "        border-radius:5px;"
                + "        text-align:center;"
                + "      }"
                + "    </style>"
                + "  </head>"
                + "  <body>"
                + "    <div class='title'>"
                + "      <h3>There's new report for you!</h3>"
                + "    </div>"
                + "    <div class='body-text'>"
                + "      <h4 style='margin-bottom:10px'>New Report Created!</h4>"
                + "      <p>" + originator + " created new report with :</p>"
                + "      <p>Datetime : " + date + "</p>"
                + "      <p>"
                + "        Desc : " + desc + ""
                + "      </p>"
                + "      <p style='text-align:center; margin-bottom:20px; margin-top:10px; font-weight:700; font-size:14px'>For detail you should open hazardApplication or </p><div style='text-align:center'>"
                + "      <a style='background: #51cbce; padding: 10px 20px; color:#fff; border-radius:10px' href='http://localhost:8084/admin/report/details/" + reportId + "'>Click here!</a>"
                + "    </div></div>"
                + "  </body>"
                + "</html>";

        return template;
    }

    public static String newReportToOriginator(Date date, String originator, String desc, int reportId) {

        String template = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "  <head>"
                + "    <meta charset='UTF-8' />"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />"
                + "    <title>Document</title>"
                + "    <style>"
                + "      body{"
                + "        font-family: 'Montserrat', sans-serif;  "
                + "      }"
                + "      .title {"
                + "        width: 100%;"
                + "        padding: 10px;"
                + "        background-color: #51cbce !important;"
                + "        color: #fff;"
                + "        margin-bottom: 20px;"
                + "        border-radius:5px;"
                + "        text-align:center;"
                + "      }"
                + "    </style>"
                + "  </head>"
                + "  <body>"
                + "    <div class='title'>"
                + "      <h3 style='margin-bottom:10px'>Hei, " + originator + " Your report is sent!</h3>"
                + "    </div>"
                + "    <div class='body-text'>"
                + "      <h4 style='margin-bottom:10px'>your report will be processed immediately</h4>"
                + "      <p>Report created with :</p>"
                + "      <p>Datetime : " + date + "</p>"
                + "      <p>"
                + "        Desc : " + desc + ""
                + "      </p>"
                + "      <p style='text-align:center; margin-bottom:20px; margin-top:10px; font-weight:700; font-size:14px'>For detail you should open hazardApplication or </p><div style='text-align:center'>"
                + "      <a style='background: #51cbce; padding: 10px 20px; color:#fff; border-radius:10px' href='http://localhost:8084/admin/report/details/" + reportId + "'>Click here!</a>"
                + "    </div></div>"
                + "  </body>"
                + "</html>";

        return template;
    }

    public static String notifyProgress(Date date, String text, int reportId) {

        String template = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "  <head>"
                + "    <meta charset='UTF-8' />"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />"
                + "    <title>Document</title>"
                + "    <style>"
                + "      body{"
                + "        font-family: 'Montserrat', sans-serif;  "
                + "      }"
                + "      .title {"
                + "        width: 100%;"
                + "        padding: 10px;"
                + "        background-color: #51cbce !important;"
                + "        color: #fff;"
                + "        margin-bottom: 20px;"
                + "        border-radius:5px;"
                + "        text-align:center;"
                + "      }"
                + "    </style>"
                + "  </head>"
                + "  <body>"
                + "    <div class='title' style=''>"
                + "      <h3>There's new notification for you!</h3>"
                + "    </div>"
                + "    <div class='body-text'>"
                + "      <h4 style='margin-bottom:10px'>Your Report Progress Is Updated!</h4>"
                + "      <p>Now your reports is on : </p>"
                + "      <p>Datetime : " + date + "</p>"
                + "      <p>"
                + "        Progress : " + text + ""
                + "      </p>"
                + "      <p style='text-align:center; margin-bottom:20px; margin-top:10px; font-weight:700; font-size:14px'>For detail you should open hazardApplication or </p><div style='text-align:center'>"
                + "      <a style='background: #51cbce; padding: 10px 20px; color:#fff; border-radius:10px' href='http://localhost:8084/admin/report/details/" + reportId + "'>Click here!</a>"
                + "    </div></div>"
                + "  </body>"
                + "</html>";

        return template;
    }

    public static String notifyProgressEnd(Date date, String text, int reportId) {

        String template = "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "  <head>"
                + "    <meta charset='UTF-8' />"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1.0' />"
                + "    <title>Document</title>"
                + "    <style>"
                + "      body{"
                + "        font-family: 'Montserrat', sans-serif;  "
                + "      }"
                + "      .title {"
                + "        width: 100%;"
                + "        padding: 10px;"
                + "        background-color: #51cbce !important;"
                + "        color: #fff;"
                + "        margin-bottom: 20px;"
                + "        border-radius:5px;"
                + "        text-align:center;"
                + "      }"
                + "    </style>"
                + "  </head>"
                + "  <body>"
                + "    <div class='title'>"
                + "      <h3>There's new notification for you!</h3>"
                + "    </div>"
                + "    <div class='body-text'>"
                + "      <h4 style='margin-bottom:10px'>Your report is closed!</h4>"
                + "      <p>Datetime : " + date + "</p>"
                + "      <p>" + text + "</p>"
                + "      <p style='text-align:center; margin-bottom:20px; margin-top:10px; font-weight:700; font-size:14px'>For detail you should open hazardApplication or </p><div style='text-align:center'>"
                + "      <a style='background: #51cbce; padding: 10px 20px; color:#fff; border-radius:10px' href='http://localhost:8084/admin/report/details/" + reportId + "'>Click here!</a>"
                + "    </div></div>"
                + "  </body>"
                + "</html>";

        return template;
    }
}
