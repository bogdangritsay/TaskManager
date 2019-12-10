package ua.edu.sumdu.j2se.hritsay.tasks;

import java.util.Objects;

public class Task implements Cloneable {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean isActive;
    /** Constructor for non-repeatable task.
     * @param title sets the title of a task.
     * @param time sets the time of task execution.
     */
    public Task(final String title, final int time) throws IllegalArgumentException {
            this.title = title;
            this.time = time;
            isActive = false;
            if (time < 0) {
                throw new IllegalArgumentException("Time must be > 0");
            }

    }
    /** Constructor for repeatable task.
     *
     * @param title sets the title of a task.
     * @param start sets starting time of task execution.
     * @param end sets time for ending of task execution.
     * @param interval sets interval between task repeats.
     */
    public Task(final String title, final int start, final int end, final int interval) throws IllegalArgumentException {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            isActive = false;
            if ((start < 0) || (end < 0) || (interval <= 0)) {
                throw new IllegalArgumentException("Time must be > 0");
            }
    }
    public Task() {
        super();
    }
    /**
     * Getter for task title.
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Setter for task title.
     * @param title sets new task title
     */
    public void setTitle(final String title) {
        this.title = title;
    }
    /**
     * Getter for current status of task.
     * @return isActive field.
     */
    public boolean isActive() {
        return isActive;
    }
    /**
     * Setter for isActive field.
     * @param active sets new activity status.
     */
    public void setActive(final boolean active) {
        this.isActive = active;
    }
    /**
     *  Getter for task time.
     * @return time if task is non-repeatable.
     * return start if task is repeatable.
     */
    public int getTime() {
        if (interval == 0) {
            return time;
        } else {
            return start;
        }
    }
    /**
     *  Setter for task time.
     *  Converts repeatable task into a non-repeatable one.
     * @param time sets new task time.
     */
    public void setTime(final int time) {
        if (isRepeated()) {
            this.interval = 0;
            this.start = 0;
            this.end = 0;
        }
        this.time = time;
    }
    /**
     *  Setter for task time.
     *  Converts a non-repeatable task into a repeatable one.
     * @param start sets new task start time
     * @param end sets new task end time
     * @param interval sets new task repeat interval
     */
    public void setTime(final int start, final int end, final int interval) {
        if (!isRepeated()) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }
    /**
     * Getter for start time of a repeatable task.
     * @return time of non-repeatable task execution.
     * return start time of repeatable task execution.
     */
    public int getStartTime() {
        if (interval != 0) {
            return this.start;
        } else {
            return this.time;
        }
    }
    /**
     * Getter for end time of a repeatable task.
     * @return time of non-repeatable task execution.
     *  return end time of repeatable task execution.
     */
    public int getEndTime()   {
        if (interval != 0) {
            return this.end;
        } else {
            return this.time;
        }
    }
    /**
     * Getter for interval of a repeatable task.
     * @return 0, because non-repeatable tasks have
     *            interval set to 0 by default.
     *  return interval of repeatable task execution.
     */
    public int getRepeatInterval() {
        if (this.interval == 0) {
            return 0;
        } else {
            return this.interval;
        }
    }
    /**
     * Method to check whether task is repeatable or not.
     * @return true if task is repeatable.
     * return false if task is non-repeatable.
     */
    public boolean isRepeated() {
        return ((this.interval > 0) && (this.start != this.end));
    }
    /**
     *  Method to get next execution time relative to given current time.
     * @return -1 if the task is not active
     * return time If the task does not repeat
     * return -1 If current is longer than the end time
     * return start If current < start
     * return -1 if current < 0
     */
    public final int nextTimeAfter(final int current) {
        if (!isActive()) {
            return -1;
        } else if (!isRepeated()) {
            if (current >= time) {
                return -1;
            } else {
                return time;
            }
        } else if (current < start) {
            return start;
        } else {
            if (current >= end || current < 0) {
                return -1;
            } else if ((current + interval) >= end) {
                return -1;
            } else {
                int date = this.start;
                while (date < this.end) {
                    if (current < date) {
                        break;
                    }
                    date += interval;
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
        return 31*Objects.hash(title, time, start, end, interval, isActive);
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task)super.clone();
    }

    @Override
    public String toString() {
        return " Task: " +
                "title = '" + title + '\'' +
                ", time = " + time +
                ", start = " + start +
                ", end = " + end +
                ", interval = " + interval +
                ", isActive = " + isActive +
                ".\n";
    }
}

