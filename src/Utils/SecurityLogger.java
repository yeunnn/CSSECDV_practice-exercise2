/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
import java.nio.file.*;
/**
 *
 * @author PC
 * SecurityLogger
 *
 * A thin wrapper around java.util.logging that funnels all security-relevant
 * events (logins, registration, DB errors, policy violations) into **one
 * daily rotating file** under the project’s ./logs directory.
 *
 * Key points
 * ----------
 * •  One log file per day  →  logs/security-YYYY-MM-DD.log
 * •  INFO  = normal events   (successful login / registration)
 * •  WARN  = suspicious but not fatal (failed login, weak password attempt)
 * •  ERROR = security breach or exception
 *
 * Usage
 * -----
 *  SecurityLogger.info("Login success for '"+user+"'");
 *  SecurityLogger.warn("Weak password attempt by '"+user+"'");
 *  SecurityLogger.error("DB insert failed", ex);
 *
 */
public class SecurityLogger {
    
    private static final Logger log = Logger.getLogger("SecurityLog");

    static {
        try {
            // Ensure the ./logs directory exists
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("logs"));

            // Daily-rotating log file: logs/security-YYYY-MM-DD.log
            String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            java.util.logging.Handler fh =
                new java.util.logging.FileHandler("logs/security-" + date + ".log", true);

            fh.setFormatter(new java.util.logging.SimpleFormatter());
            log.addHandler(fh);
            log.setLevel(java.util.logging.Level.INFO);
            log.setUseParentHandlers(false);    // keep console clean
        } catch (Exception ex) {
            // Last-chance fallback: print to console so errors aren’t lost
            System.err.println("Could not initialise security log: " + ex);
        }
    }

    private SecurityLogger() {}

    public static void info(String msg){  log.log(Level.INFO,  msg); }
    public static void warn(String msg){  log.log(Level.WARNING, msg); }
    /** Attach the stack trace for severe errors */
    public static void error(String msg, Throwable t){
        log.log(Level.SEVERE, msg, t);
    }
}
