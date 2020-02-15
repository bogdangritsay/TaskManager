package ua.edu.sumdu.j2se.hritsay.tasks.models;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for operations with tasks.
 */
public class Tasks {

    /**
     * Method to get task list with time incoming in diapason
     * @param tasks task list to analise
     * @param from start time of diapason
     * @param to end time of diapason
     * @return task list of incoming tasks
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        ArrayTaskList selected = new ArrayTaskList();

        for (Task task : tasks) {
            if (task != null) {
                if (task.isActive() && task.nextTimeAfter(from) != null && task.nextTimeAfter(from).isBefore(to.plusSeconds(1))) {
                    selected.add(task);
                }
            }
        }
        return selected;
    }

    /**
     * Method to get calendar of tasks
     * @param tasks task list to analise
     * @param start start time of diapason
     * @param end end time of diapason
     * @return sorted calendar of the tasks
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        Iterable<Task> taskList = Tasks.incoming(tasks, start, end);
        Set<LocalDateTime> dateList = new HashSet<>();

        for (Task task : taskList) {
            if (task.getRepeatInterval() > 0) {
                dateList.addAll(Tasks.getRepeatDates(task).stream().filter(date -> date.isBefore(end.plusSeconds(1)) && date.isAfter(start)).collect(Collectors.toList()));
            } else {
                dateList.add(task.getTime());
            }
        }
        for (LocalDateTime date : dateList) {
            Set<Task> set = new HashSet<>();
            for (Task task : taskList) {
                if (Tasks.getRepeatDates(task).contains(date) && !date.equals(task.getStartTime())) {
                    List<LocalDateTime> tempDates = Tasks.getRepeatDates(task);
                    for (LocalDateTime tempDate : tempDates) {
                        if (tempDate.equals(date)) {
                            set.add(task);
                        }
                    }
                } else {
                    if (task.getStartTime().equals(date)) {
                        set.add(task);
                    }
                }
            }
            calendar.put(date, set);
        }
        return calendar;
    }

    private static List<LocalDateTime> getRepeatDates(Task task) {
        ArrayList<LocalDateTime> dates = new ArrayList<>();
        LocalDateTime date = task.getStartTime();
        while (date.isBefore(task.getEndTime())) {
            dates.add(date);
            date = date.plusSeconds(task.getRepeatInterval());
        }
        return dates;
    }
}
