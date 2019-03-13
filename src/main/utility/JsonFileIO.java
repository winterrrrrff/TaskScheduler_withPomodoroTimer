package utility;

import model.Task;
import parsers.TaskParser;
import persistence.Jsonifier;

import java.io.*;
import java.util.List;
import java.util.Scanner;

// File input/output operations
public class JsonFileIO {
    public static final File jsonDataFile = new File("./resources/json/tasks.json");
    
    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Task> read() {
        TaskParser taskParser = new TaskParser();
        try {
            FileReader fileReader = new FileReader(jsonDataFile);
            Scanner scanner = new Scanner(fileReader);
            String text = scanner.useDelimiter("\\A").next();
            return taskParser.parse(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // EFFECTS: saves the tasks to jsonDataFile
    public static void write(List<Task> tasks) {
        String newJsonData = Jsonifier.taskListToJson(tasks).toString();
        try {
            FileWriter file = new FileWriter(jsonDataFile);
            file.write(newJsonData);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
