package persistence;


import model.DueDate;
import model.Priority;
import model.Tag;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.List;

// Converts model elements to JSON objects
public class Jsonifier {

    // EFFECTS: returns JSON representation of tag
    public static JSONObject tagToJson(Tag tag) {
        JSONObject object = new JSONObject();
        object.put("name",tag.getName());
        return object;
    }

    // EFFECTS: returns JSON representation of priority
    public static JSONObject priorityToJson(Priority priority) {
        JSONObject object = new JSONObject();
        object.put("important",priority.isImportant());
        object.put("urgent",priority.isUrgent());
        return object;
    }

    // EFFECTS: returns JSON representation of dueDate
    public static JSONObject dueDateToJson(DueDate dueDate) {
        JSONObject object = new JSONObject();
        if (dueDate == null) {
            return null;
        } else {
            Calendar dueDateCal = Calendar.getInstance();
            dueDateCal.setTime(dueDate.getDate());
            object.put("year", dueDateCal.get(Calendar.YEAR));
            object.put("month", dueDateCal.get(Calendar.MONTH));
            object.put("day", dueDateCal.get(Calendar.DAY_OF_MONTH));
            object.put("hour", dueDateCal.get(Calendar.HOUR_OF_DAY));
            object.put("minute", dueDateCal.get(Calendar.MINUTE));
            return object;
        }
    }

    // EFFECTS: returns JSON representation of task
    public static JSONObject taskToJson(Task task) {
        JSONObject object = new JSONObject();
        JSONArray jsonTags = new JSONArray();
        if (!task.getTags().isEmpty()) {
            for (Tag t : task.getTags()) {
                jsonTags.put(tagToJson(t));
            }
        }
        object.put("description", task.getDescription());
        object.put("tags", jsonTags);
        if (task.getDueDate() == null) {
            object.put("due-date",JSONObject.NULL);
        } else {
            object.put("due-date", dueDateToJson(task.getDueDate()));
        }
        object.put("priority", priorityToJson(task.getPriority()));
        object.put("status", task.getStatus().toString().replace(" ", "_"));
        return object;
    }

    // EFFECTS: returns JSON array representing list of tasks
    public static JSONArray taskListToJson(List<Task> tasks) {
        JSONArray jsonTask = new JSONArray();
        for (Task t : tasks) {
            jsonTask.put(taskToJson(t));
        }
        return jsonTask;
    }
}