package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order of tasks is preserved
public class Project extends Todo implements Iterable<Todo> {

    private String description;
    private List<Todo> tasks;

    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    //  throws EmptyStringException if description is null or empty
    public Project(String description) {
        super(description);
        if (description == null || description.length() == 0) {
            throw new EmptyStringException("Cannot construct a project with no description");
        }
        this.description = description;
        tasks = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    //   throws NullArgumentException when task is null
    public void add(Todo task) {
        if (task == null) {
            throw new NullArgumentException();
        }
        if (!contains(task)) {
            tasks.add(task);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes task from this project
    //   throws NullArgumentException when task is null
    public void remove(Todo task) {
        if (task == null) {
            throw new NullArgumentException();
        }
        if (contains(task)) {
            tasks.remove(task);
        }
    }

    // EFFECTS: returns the description of this project
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    @Deprecated
    public List<Task> getTasks() {
        throw new UnsupportedOperationException();
    }

    //     EFFECTS: returns an integer between 0 and 100 which represents
//     the percentage of completion (rounded down to the nearest integer).
//     the value returned is the average of the percentage of completion of
//     all the tasks and sub-projects in this project.

    public int getProgress() {
        if (getNumberOfTasks() == 0) {
            return 0;
        }
        int totalProgress = 0;
        for (Todo t : tasks) {
            totalProgress += t.getProgress();
        }
        return totalProgress / getNumberOfTasks();
    }


    @Override
    public int getEstimatedTimeToComplete() {
        int numOfHour = 0;
        for (Todo t : tasks) {
            numOfHour += t.getEstimatedTimeToComplete();
        }
        return numOfHour;
    }

    // EFFECTS: returns the number of tasks (and sub-projects) in this project
    public int getNumberOfTasks() {
        return tasks.size();
    }


    // EFFECTS: returns true if every task (and sub-project) in this project is completed, and false otherwise
//     If this project has no tasks (or sub-projects), return false.
    public boolean isCompleted() {
        return getNumberOfTasks() != 0 && getProgress() == 100;
    }

    // EFFECTS: returns true if this project contains the task
    //   throws NullArgumentException when task is null
    public boolean contains(Todo task) {
        if (task == null) {
            throw new NullArgumentException("Illegal argument: task is null");
        }
        return tasks.contains(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }


    @Override
    public Iterator<Todo> iterator() {
        return new TodoIterator();
    }

    private class TodoIterator implements Iterator<Todo> {

        private int index = 0;
        private int priorityNum = 1;
        private Priority currentPriority = new Priority(1);

        @Override
        public boolean hasNext() {
            return index < tasks.size();
        }

        @Override
        public Todo next() {
            if (!hasNext() && priorityNum > 4) {
                throw new NoSuchElementException();
            }
            Todo next = getNextTodo();
            index += 1;
            return next;
        }

        private Todo getNextTodo() {
            while (index < tasks.size()) {
                if (tasks.get(index).getPriority().equals(currentPriority)) {
                    return tasks.get(index);
                } else {
                    index += 1;
                }
            }
            index = 0;
            priorityNum += 1;
            currentPriority = new Priority(priorityNum);
            return getNextTodo();
        }
    }



}

