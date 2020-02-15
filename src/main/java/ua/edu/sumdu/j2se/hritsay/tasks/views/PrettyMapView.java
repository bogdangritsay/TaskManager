package ua.edu.sumdu.j2se.hritsay.tasks.views;

import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 Pretty view for the calendar
 */
public class PrettyMapView {
    /**
     * Method to pretty view of the map
     * @param map map to pretty view
     * @return pretty transformed map in string
     */
    static public String prettyMap(SortedMap<LocalDateTime, Set<Task>> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<LocalDateTime, Set<Task>>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<LocalDateTime, Set<Task>> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('\t').append(':').append('\t');
            Set<Task>  tasksForDate = entry.getValue();
            for (Task task : tasksForDate) {
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
}
