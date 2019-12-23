package ua.edu.sumdu.j2se.hritsay.tasks;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.util.function.ObjLongConsumer;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            //размер списка
            byte byteCountTasks = (byte)tasks.size();
            out.write(byteCountTasks);
            for(Task task : tasks) {
                //длинна названия задачи
                out.write((byte) task.getTitle().length());
            }
                /*//название задачи
                byte []titleBytes = task.getTitle().getBytes();
                for (byte eachByte : titleBytes) {
                    out.write(eachByte);
                }
                //активность
                if(task.isActive()) {
                    out.write((byte)1);
                } else {
                    out.write((byte)0);
                }
                //интервал
                out.write((byte)task.getRepeatInterval());
                //время
                if (task.isRepeated()) {

                } else {

                }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            for(Task task : tasks) {
                //длинна названия задачи
                task = (Task)ois.readObject();
            }
         } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            for(Task task : tasks) {
                oos.writeObject(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void readBinary(AbstractTaskList tasks, File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            for(Task task : tasks) {
                task = (Task) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void write(AbstractTaskList tasks, Writer out) {
        //записує задачі зі списку у потік в форматі JSON
        try {
        Gson gson = new Gson();
        out.write(gson.toJson(tasks));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {

    }

    public static void writeText(AbstractTaskList tasks, File file) {
        try {
            FileWriter writeInFile = new FileWriter(file);
            write(tasks, writeInFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {

    }



}
