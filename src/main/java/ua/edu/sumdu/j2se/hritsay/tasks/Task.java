package ua.edu.sumdu.j2se.hritsay.tasks;

import java.util.Objects;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean isActive;

    public Task(final String title, final int time) {
        this.title = title;
        this.time = time;
        isActive = false;
    }

    public Task(final String title, final int start, final int end, final int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        isActive = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(final boolean active) {
        this.isActive = active;
    }

    public int getTime() {
        if (interval == 0) {
            return time;
        } else {
            return start;
        }
    }

    public void setTime(final int time) {
        if (isRepeated()) {
            this.interval = 0;
            this.start = 0;
            this.end = 0;
        }
        this.time = time;
    }

    public void setTime(final int start, final int end, final int interval) {
        if (!isRepeated()) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    public int getStartTime() {
        if (interval != 0) {
            return this.start;
        } else {
            return this.time;
        }
    }

    public int getEndTime()   {
        if (interval != 0) {
            return this.end;
        } else {
            return this.time;
        }
    }

    public int getRepeatInterval() {
        if (this.interval == 0) {
            return 0;
        } else {
            return this.interval;
        }
    }

    public boolean isRepeated() {
        return ((this.interval > 0) && (this.start != this.end));
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
            return time == task.time && start == task.start
                    && end == task.end
                    && interval == task.interval
                    && isActive == task.isActive
                    && Objects.equals(title, task.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, time, start, end, interval, isActive);
        }
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
}

