package com.restinit.core.reporting;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;

@Lazy
@Component
public class ReportingService {
    private static ExtentReports extent;
    private static String reportFileName = "Test-Automaton-Report.html";
    private static String windowsPath = System.getProperty("user.dir") + "\\RestInitResults";
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = getReportFileLocation();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    //Select the extent report file location based on platform
    private static String getReportFileLocation() {
        String reportFileLocation = null;
        reportFileLocation = winReportFileLoc;
        createReportPath(windowsPath);
        System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");

        return reportFileLocation;
    }

    //Create the report path if it does not exist
    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }


}

