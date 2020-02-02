package ua.edu.sumdu.j2se.hritsay.tasks;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;

public class ConsoleView implements View {
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public int removeTaskView(AbstractTaskList taskList) {
        do {
            int idRemTask;
            try {
                do {
                    System.out.println("Enter ID of deleting task: ");
                    idRemTask = Integer.parseInt(bufferedReader.readLine());
                    if (checkId(idRemTask, taskList)) {
                        System.out.println("Removed.");
                        return idRemTask;
                    } else {
                        System.out.println("Task is not found!");
                    }
                } while (true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        } while(true);
    }

    @Override
    public Task addTaskView(AbstractTaskList taskList) {
        Task addTask = null;
        int taskId;
        String title;
        LocalDateTime time;
        LocalDateTime start;
        LocalDateTime end;
        int interval;
        boolean isActive;
        boolean isRepeatTask;
        try {
            do {
                try {
                    System.out.println("Enter an ID for the task:");
                    taskId = Integer.parseInt(bufferedReader.readLine());
                    if (checkId(taskId, taskList)) {
                        System.out.println("A task with such an ID is already on the list.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number!");
                }
            } while (true);

            System.out.println("Enter a title for the task: ");
            title = bufferedReader.readLine();
            System.out.println("It is active task? \n(Enter \"true\" if the task is active or something else in if it is not): ");
            isActive = Boolean.parseBoolean(bufferedReader.readLine());
            System.out.println("It is repeatable task? (true or false): \n(Enter \"true\" if the task is active or something else in if it is not): ");
            isRepeatTask = Boolean.parseBoolean(bufferedReader.readLine());
            if(isRepeatTask) {
                    System.out.println("Enter start time:");
                    start = readDate();
                    System.out.println("Enter end time: ");
                    end = readDate();
                    System.out.println("Enter interval of repeats: ");
                    interval = Integer.parseInt(bufferedReader.readLine());
                addTask = new Task(taskId, title, start, end, interval, isActive);
            } else {
                System.out.println("Enter time: ");
                time = readDate();
                addTask = new Task(taskId, title, time, isActive);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return addTask;
    }

    @Override
    public Task editView(AbstractTaskList taskList, int i) {
        do {
            try {
                Task chTask = taskList.getTask(i);

                System.out.println("Choose, what you want to change: \n" +
                        "1 - Change title \n" +
                        "2 - Active \n" +
                        "3 - Change time(for no repeat tasks) \n" +
                        "4 - Change start-time (for  repeat tasks) \n" +
                        "5 - Change end-time (for  repeat tasks) \n" +
                        "6 - Change interval (for  repeat tasks) \n");
                int act = Integer.parseInt(bufferedReader.readLine());

                switch (act) {
                    case 1:
                        System.out.println("Enter new title for the task: \n");
                        String name = bufferedReader.readLine();
                        chTask.setTitle(name);
                        break;
                    case 2:
                        System.out.println("It's  active task? (true or false): \n");
                        boolean isActive = Boolean.parseBoolean(bufferedReader.readLine());
                        chTask.setActive(isActive);
                        break;
                    case 3:
                        if (!chTask.isRepeated()) {
                            LocalDateTime date = readDate();
                            chTask.setTime(date);
                        } else {
                            System.out.println("Sorry, but it's repeatable task! \n");
                        }
                        break;
                    case 4:
                        if (chTask.isRepeated()) {
                            LocalDateTime startDate = readDate();
                            chTask.setTime(startDate, chTask.getEndTime(), chTask.getRepeatInterval());
                        } else {
                            System.out.println("Sorry, but it's no repeatable task! \n ");
                        }
                        break;
                    case 5:
                        if (chTask.isRepeated()) {
                            LocalDateTime endDate = readDate();
                            chTask.setTime(chTask.getStartTime(), endDate, chTask.getRepeatInterval());
                        } else {
                            System.out.println("Sorry, but it's no repeatable task! \n");
                        }
                        break;
                    case 6:
                        System.out.println("Enter an interval for repeatable task: \n");
                        int interval = Integer.parseInt(bufferedReader.readLine());
                        chTask.setInterval(interval);
                        break;
                }
                return chTask;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Input error!");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        } while(true);
    }

    private boolean checkId(int id, AbstractTaskList taskList) {
        for (Task task : taskList) {
            if (id == task.getTaskId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int readI(AbstractTaskList taskList) {
        do {
            try {
                int id;
                System.out.println("Enter an id for the task, please: \n");
                id = Integer.parseInt(bufferedReader.readLine());
                for (int i = 0; i < taskList.size(); i++) {
                    if (id == taskList.getTask(i).getTaskId()) {
                        return i;
                    }
                }
                System.out.println("This task is not found!");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        } while (true);
    }

    @Override
    public LocalDateTime readDate() {
        do {
        try {
            LocalDateTime date = LocalDateTime.now();
                System.out.println("Enter year: ");
                int year = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter month (1,2, 3 etc.): ");
                int month = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter day of month: ");
                int day = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter hours: ");
                int hours = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter minutes: ");
                int minutes = Integer.parseInt(bufferedReader.readLine());
                date = LocalDateTime.of(year, month, day, hours, minutes, 0);
                return date;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DateTimeException e) {
            System.out.println("Enter a valid date an time!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number!");
        }
        } while (true);
    }

    @Override
    public void calendarView(AbstractTaskList taskList) {
        System.out.println("Calendar for 7 days: ");
        System.out.println(prettyMap(Tasks.calendar(taskList, LocalDateTime.now(), LocalDateTime.now().plusDays(7))));
    }

    private String prettyMap(SortedMap<LocalDateTime, Set<Task>> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<LocalDateTime, Set<Task>>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<LocalDateTime, Set<Task>> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('\t').append(':').append('\t');
            Set<Task>  tasksForDate = entry.getValue();
            for(Task task : tasksForDate) {
                sb.append(task.getTitle());
            }
            if (iter.hasNext()) {
                sb.append(',').append('\n');
            } else {
            sb.append('.');
            }
        }

        return sb.toString();
    }

    public void hello() {
        System.out.println("Hello. I'm your Task Manager!");
    }

    @Override
    public int mainMenuView() {
        try {
            System.out.println("Choose your action:");
            int act = -1;
            while(true) {
                System.out.println(
                          "\t1 - Show all task list \n"
                        + "\t2 - Show calendar for a week \n"
                        + "\t3 - Add new task \n"
                        + "\t4 - Remove task \n"
                        + "\t5 - Edit task  \n"
                        + "\t0 - Exit");
                act = Integer.parseInt(bufferedReader.readLine());

                if(act > 5 || act < 0) {
                    System.out.println("Incorrect number!");
                } else {
                    return act;
                }
            }
        } catch (IOException e) {
            System.out.println("IO Error!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number!");
        }
        return -1;
    }

    @Override
    public void showListView(AbstractTaskList taskList) {
        AbstractTaskList repeatedTasks = new ArrayTaskList();
        AbstractTaskList noRepeatedTasks = new ArrayTaskList();
        for(Task task : taskList) {
            if(task.isRepeated()) {
                repeatedTasks.add(task);
            } else {
                noRepeatedTasks.add(task);
            }
        }
        System.out.println("Repeated tasks:");
        System.out.println(repeatedTasks.toString());
        System.out.println("No Repeated tasks:");
        System.out.println(noRepeatedTasks.toString());
    }

    @Override
    public int confirmSaving() {
        do {
        try {
            System.out.println("Save changes?\n 1 - Yes\n 0 - No");
            int act = -1;
            boolean yesOrNo = false;
            while(!yesOrNo) {
                int actTmp = Integer.parseInt(bufferedReader.readLine());
                if(actTmp == 1 || actTmp == 0) {
                    act = actTmp;
                    yesOrNo = true;
                } else {
                    System.out.println("Incorrect action! \nPlease choose 1 or 0.");
                }
            }
            return act;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number!");
        }
        } while (true);
    }

}
