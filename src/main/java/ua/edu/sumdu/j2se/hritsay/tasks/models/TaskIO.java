package ua.edu.sumdu.j2se.hritsay.tasks.models;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hritsay.tasks.controllers.ConsoleController;


import java.io.*;
import java.time.*;

public class TaskIO  {
    final static Logger logger = Logger.getLogger(ConsoleController.class);
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream oos = new DataOutputStream(out)) {
            oos.write(tasks.size());
            for(Task task : tasks) {
                oos.write(task.getTitle().length());
                oos.writeUTF(task.getTitle());
                if (task.isActive()) {
                    oos.write( 1);
                } else {
                    oos.write(0);
                }
                oos.write(task.getRepeatInterval());
                if (task.isRepeated()) {
                    Instant startInstant = task.getStartTime().atZone(ZoneId.systemDefault()).toInstant();
                    long startMillis = startInstant.toEpochMilli();
                    oos.writeLong(startMillis);
                    Instant endInstant = task.getEndTime().atZone(ZoneId.systemDefault()).toInstant();
                    long endMillis = endInstant.toEpochMilli();
                    oos.writeLong(endMillis);
                } else {
                    Instant timeInstant = task.getTime().atZone(ZoneId.systemDefault()).toInstant();
                    long timeMillis = timeInstant.toEpochMilli();
                    oos.writeLong(timeMillis);
                }
            }
        } catch (IOException e) {
           logger.error("Input/output exception");
        }

    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream dis = new DataInputStream(in)) {
            int size = dis.read();
            Task task;
            int taskId;
            String title;
            boolean active;
            int repeatInterval;
            LocalDateTime start, end, time;
            for (int i = 0; i < size; i++) {
                dis.read();
                taskId = dis.read();
                title = dis.readUTF();
                active = (dis.read() == 1);
                repeatInterval = dis.read();
                if(repeatInterval > 0 ) {
                    start = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                    end = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(taskId, title, start, end, repeatInterval, active);
                } else {
                    time = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(taskId, title, time, active);
                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException e) {
            logger.error("Input/output exception");
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (FileOutputStream fos = new FileOutputStream(file)){
            TaskIO.write(tasks, fos);
        } catch (IOException e) {
            logger.error("Input/output exception");
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try(FileInputStream fis = new FileInputStream(file)) {
            TaskIO.read(tasks, fis);
        } catch (IOException e) {
            logger.error("Input/output exception");
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        try(Writer outW = out) {
            ArrayTaskList list = new ArrayTaskList();
            for(Task task : tasks) {
                list.add(task);
            }
        Gson gson = new Gson();
        outW.write(gson.toJson(list, ArrayTaskList.class));
        } catch (IOException e) {
            logger.error("Input/output exception");
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        try (Reader inR = in) {
        AbstractTaskList list;
        Gson gson = new Gson();
        list = gson.fromJson(inR, ArrayTaskList.class);
        for(Task task : list) {
            tasks.add(task);
        }
        } catch(IOException e) {
            logger.error("Input/output exception");
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) {
        try (FileWriter fwr = new FileWriter(file)){
            write(tasks, fwr);
        } catch (IOException e) {
            logger.error("Input/output exception");
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader frd = new FileReader(file)){
            read(tasks, frd);
        } catch (IOException e) {
            logger.info("Input/output exception: new file was been created.");
        }
    }

}


