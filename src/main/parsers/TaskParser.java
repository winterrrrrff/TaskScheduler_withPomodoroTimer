package parsers;

import model.DueDate;
import model.Priority;
import model.Status;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Represents Task parser
public class TaskParser {

    // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
    // string and parses it as a task; each parsed task is added to the list of tasks.
    // Any task that cannot be parsed due to malformed JSON data is not added to the
    // list of tasks.
    // Note: input is a string representation of a JSONArray
    public List<Task> parse(String input) {
        List<Task> tasks = new ArrayList<>();
        JSONArray jsonTasks = new JSONArray(input);
        for (Object object : jsonTasks) {
            JSONObject task = (JSONObject) object;
            if (checkKey(task)) {
                if (checkValue(task)) {
                    Task theOneTask = parseHelper(task);
                    //if (!(theOneTask == null)) {
                    tasks.add(theOneTask);
                    // }
                }
            }
        }
        return tasks;
    }

    public boolean checkKey(JSONObject task) {
        Boolean hasValidKeys = task.has("description") && task.has("priority") && task.has("tags")
                && task.has("status") && task.has("due-date");
        return hasValidKeys;
    }

    public boolean checkValue(JSONObject task) {
        Boolean isDes = task.get("description") instanceof String;
        Boolean isStat = task.get("status") instanceof String;
        Boolean isPri = checkPriorityInfo(task);
        Boolean isDd = checkDueDateInfo(task);
        Boolean isTag = checkTagsInfo(task);
        return  isDes && isTag && isPri && isStat && isDd;
    }

    public boolean checkPriorityInfo(JSONObject task) {
        if (task.get("priority") instanceof JSONObject) {
            if (task.getJSONObject("priority").has("important")
                    && task.getJSONObject("priority").has("urgent")) {
                if (task.getJSONObject("priority").get("important") instanceof Boolean
                        && task.getJSONObject("priority").get("urgent") instanceof Boolean) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDueDateInfo(JSONObject task) {
        if (!task.isNull("due-date") && task.get("due-date") instanceof JSONObject) {
            if (task.getJSONObject("due-date").has("year")
                    && task.getJSONObject("due-date").has("month")
                    && task.getJSONObject("due-date").has("day")
                    && task.getJSONObject("due-date").has("hour")
                    && task.getJSONObject("due-date").has("minute")) {
                if (task.getJSONObject("due-date").get("year") instanceof Integer
                        && task.getJSONObject("due-date").get("month") instanceof Integer
                        && task.getJSONObject("due-date").get("day") instanceof Integer
                        && task.getJSONObject("due-date").get("hour") instanceof Integer
                        && task.getJSONObject("due-date").get("minute") instanceof Integer) {
                    return true;
                }
            }
        } else if (task.isNull("due-date")) {
            return true;
        }
        return false;
    }

    public boolean checkTagsInfo(JSONObject task) {
        if (task.get("tags") instanceof JSONArray) {
            if (task.getJSONArray("tags").isEmpty()) {
                return true;
            } else {
                for (int i = 0; i < task.getJSONArray("tags").length(); i++) {
                    if (task.getJSONArray("tags").getJSONObject(i).has("name")) {
                        if (task.getJSONArray("tags").getJSONObject(i).get("name") instanceof String) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Task parseHelper(JSONObject task) {
        String description = task.getString("description");
        JSONObject priority = task.getJSONObject("priority");
        JSONArray tags = task.getJSONArray("tags");
        String status = task.getString("status");
        if (!task.isNull("due-date") && task.get("due-date") instanceof JSONObject) {
            JSONObject dueDate = task.getJSONObject("due-date");
            Task oneTask = parseOneTask(description,status,dueDate,tags,priority);
            return oneTask;
        } else if (task.isNull("due-date")) {
            Task taskWithNullDueDate = parseOneTaskWithNullDueDate(description,status,tags,priority);
            return taskWithNullDueDate;
        }
        return null;
    }

    private Task parseOneTask(String des, String sta, JSONObject dd, JSONArray tag, JSONObject prior) {
        Task theTask = new Task(des);
        theTask.setStatus(parseStatus(sta));
        theTask.setPriority(parsePriority(prior));
        theTask.setDueDate(parseDueDate(dd));
        parseTag(theTask,tag);
        return theTask;
    }

    private Task parseOneTaskWithNullDueDate(String des, String sta, JSONArray tag, JSONObject prior) {
        Task theTask = new Task(des);
        theTask.setStatus(parseStatus(sta));
        theTask.setPriority(parsePriority(prior));
        parseTag(theTask,tag);
        return theTask;
    }

    private Status parseStatus(String s) {
        if (s.equalsIgnoreCase("TODO")) {
            return Status.TODO;
        } else if (s.equalsIgnoreCase("DONE")) {
            return Status.DONE;
        } else if (s.equalsIgnoreCase("UP_NEXT")) {
            return Status.UP_NEXT;
        } else {
            return Status.IN_PROGRESS;
        }
    }

    private Priority parsePriority(JSONObject pr) {
        Priority thePriority = new Priority();
        thePriority.setImportant(pr.getBoolean("important"));
        thePriority.setUrgent(pr.getBoolean("urgent"));
        return thePriority;
    }

    private DueDate parseDueDate(JSONObject d) {
        Calendar theTime = Calendar.getInstance();
        theTime.set(Calendar.YEAR, d.getInt("year"));
        theTime.set(Calendar.MONTH, d.getInt("month"));
        theTime.set(Calendar.DAY_OF_MONTH, d.getInt("day"));
        theTime.set(Calendar.HOUR_OF_DAY, d.getInt("hour"));
        theTime.set(Calendar.MINUTE, d.getInt("minute"));
        DueDate theDueDate = new DueDate(theTime.getTime());
        return theDueDate;
    }


    private void parseTag(Task t, JSONArray tags) {
        for (int i = 0; i < tags.length(); i++) {
            JSONObject oneTag = tags.getJSONObject(i);
            String theTag = oneTag.getString("name");
            t.addTag(theTag);
        }
    }
}
