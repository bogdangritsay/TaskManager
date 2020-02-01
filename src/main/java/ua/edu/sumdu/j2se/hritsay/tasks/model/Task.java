package ua.edu.sumdu.j2se.hritsay.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {
    private int taskId;
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean isActive;

    /**
     * Constructor for non-repeatable task.
     *
     * @param title sets the title of a task.
     * @param time  sets the time of task execution.
     */
    public Task(final int taskId, final String title, final LocalDateTime time, final boolean isActive) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        this.taskId = taskId;
        this.title = title;
        this.time = time;
        this.isActive = isActive;

    }

    /**
     * Constructor for repeatable task.
     *
     * @param title    sets the title of a task.
     * @param start    sets starting time of task execution.
     * @param end      sets time for ending of task execution.
     * @param interval sets interval between task repeats.
     */
    public Task(final int taskId, final String title, final LocalDateTime start, final LocalDateTime end, final int interval, final boolean isActive) throws IllegalArgumentException {
        if (start == null || end == null || interval == 0) {
            throw new IllegalArgumentException("Start or end == null. Or interval for repeatable is 0.");
        } else {
            this.taskId = taskId;
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = start;
           this.isActive = isActive;
        }
    }

    public Task() {
        super();
    }

    /**
     * Getter for task title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for task title.
     *
     * @param title sets new task title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Getter for current status of task.
     *
     * @return isActive field.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Setter for isActive field.
     *
     * @param active sets new activity status.
     */
    public void setActive(final boolean active) {
        this.isActive = active;
    }

    /**
     * Getter for task time.
     *
     * @return time if task is non-repeatable.
     * return start if task is repeatable.
     */
    public LocalDateTime getTime() {
        if (interval == 0) {
            return time;
        } else {
            return start;
        }
    }

    /**
     * Setter for task time.
     * Converts repeatable task into a non-repeatable one.
     *
     * @param time sets new task time.
     */
    public void setTime(final LocalDateTime time) {
        if (this.interval != 0) {
            this.interval = 0;
            this.start = time;
            this.end = time;
        }
        this.time = time;
    }

    /**
     * Setter for task time.
     * Converts a non-repeatable task into a repeatable one.
     *
     * @param start    sets new task start time
     * @param end      sets new task end time
     * @param interval sets new task repeat interval
     */
    public void setTime(final LocalDateTime start, final LocalDateTime end, final int interval) {
        if (!isRepeated()) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    /**
     * Getter for start time of a repeatable task.
     *
     * @return time of non-repeatable task execution.
     * return start time of repeatable task execution.
     */
    public LocalDateTime getStartTime() {
        if (interval != 0) {
            return this.start;
        } else {
            return this.time;
        }
    }

    /**
     * Getter for end time of a repeatable task.
     *
     * @return time of non-repeatable task execution.
     * return end time of repeatable task execution.
     */
    public LocalDateTime getEndTime() {
        if (interval != 0) {
            return this.end;
        } else {
            return this.time;
        }
    }

    /**
     * Getter for interval of a repeatable task.
     *
     * @return 0, because non-repeatable tasks have
     * interval set to 0 by default.
     * return interval of repeatable task execution.
     */
    public int getRepeatInterval() {
        if (this.interval == 0) {
            return 0;
        } else {
            return this.interval;
        }
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Method to check whether task is repeatable or not.
     *
     * @return true if task is repeatable.
     * return false if task is non-repeatable.
     */
    public boolean isRepeated() {
        return ((this.interval > 0) && (!start.isEqual(end)));
    }

    /**
     * Method to get next execution time relative to given current time.
     *
     * @return -1 if the task is not active
     * return time If the task does not repeat
     * return -1 If current is longer than the end time
     * return start If current < start
     * return -1 if current < 0
     */

    public final LocalDateTime nextTimeAfter(final LocalDateTime current) {
        if (!isActive()) {
            return null;
        }
        if (!isRepeated()) {
            if (current.isAfter(time) || current.isEqual(time)) {
                return null;
            } else {
                return time;
            }
        } else {
            if (current.isBefore(start)) {
                return start;
            }
            if (current.isAfter(end) || current.isEqual(end)) {
                return null;
            } else {
                LocalDateTime date = start;
                LocalDateTime last = start;
                while (last.plusSeconds(interval).isBefore(end) || last.plusSeconds(interval).isEqual(end)) {
                    last = last.plusSeconds(interval);
                }
                if (!last.isEqual(end)) {
                    if (current.isBefore(end) && current.isAfter(last)) return null;
                }
                while (date.isBefore(end) || date.isEqual(end)) {
                    if (date.isAfter(current)) break;
                    date = date.plusSeconds(interval);
                }
                return date;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                isActive == task.isActive &&
                Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(title, time, start, end, interval, isActive);
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    @Override
    public String toString() {
        if (isRepeated()) {
            return taskId + "\t\t\t" + title + "\t\t\t" + start + "\t\t\t" + end + "\t" + interval + "\t" + isActive + "\n";

        } else {
            return taskId + "\t\t\t" + title + "\t\t\t" + time + "\t" + isActive + "\n";
        }

    }
}

