package model;

import model.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.TagParser;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    private Task testTask;
    private Priority priority;
    private DueDate dueDate;
    private Project project;
    private Tag testTag;
    private Task task;

    @BeforeEach
    public void constructor() {
        testTask = new Task("Test");
        priority = new Priority(1);
        dueDate = new DueDate();
        testTag = new Tag("Test Tag");
        project = new Project("cpsc210 Project");
        task = new Task("Project Phase1");
    }

    @Test
    void testAddTag() {
        testTask.addTag("cpsc210");
        assertTrue(testTask.containsTag("cpsc210"));
    }

    @Test
    void testAddTag2() {
        testTask.addTag("cpsc210");
        testTask.addTag("cpsc210");
        testTask.addTag("cpsc210");
        assertEquals(1, testTask.getTags().size());
    }

    @Test
    void testRemoveTag() {
        testTask.addTag("a");
        testTask.addTag("b");
        testTask.addTag("c");
        testTask.removeTag("b");
        assertFalse(testTask.containsTag("b"));
    }

    @Test
    void testRemoveTag2() {
        testTask.addTag("a");
        testTask.addTag("b");
        testTask.addTag("c");
        testTask.removeTag("1");
        assertTrue(testTask.containsTag("b"));
    }

    @Test
    void testRemoveTag3() {
        testTask.removeTag("b");
        assertFalse(testTask.containsTag("b"));
        assertTrue(testTask.getTags().isEmpty());
    }

    @Test
    void testRemoveTag4() {
        testTask.addTag("a");
        testTask.addTag("b");
        testTask.removeTag("a");
        assertTrue(testTask.containsTag("b"));
        assertFalse(testTask.getTags().isEmpty());
        assertEquals(1, testTask.getTags().size());
    }


    @Test
    void testGetTag() {
        testTask.addTag("b");
        testTask.addTag("a");
        assertFalse(testTask.getTags().isEmpty());
        testTask.removeTag("a");
        testTask.removeTag("a");
        testTask.removeTag("c");
        assertFalse(testTask.getTags().isEmpty());
        assertTrue(testTask.containsTag("b"));
    }

    @Test
    void testGetPriority() {
        assertFalse(testTask.getPriority().isUrgent());
        assertFalse(testTask.getPriority().isImportant());
    }

    @Test
    void testSetPriority() {
        testTask.setPriority(priority);
        assertTrue(testTask.getPriority().isUrgent());
        assertTrue(testTask.getPriority().isImportant());
    }

    @Test
    void testGetStatus() {
        assertEquals(Status.TODO, testTask.getStatus());
    }

    @Test
    void testSetStatus() {
        testTask.setStatus(Status.DONE);
        assertEquals(Status.DONE, testTask.getStatus());
        testTask.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS, testTask.getStatus());
    }

    @Test
    void testGetDescription2345() {
        assertEquals("Test", testTask.getDescription());
    }

    @Test
    void testSetDescription() {
        testTask.setDescription("Happy NewYear");
        assertEquals("Happy NewYear", testTask.getDescription());
        testTask.setDescription("You will have A+");
        assertEquals("You will have A+", testTask.getDescription());
    }

    @Test
    void testGetDueDate() {
        assertEquals(null, testTask.getDueDate());
    }

    @Test
    void testSetDueDate() {
        testTask.setDueDate(dueDate);
        assertEquals(this.dueDate, testTask.getDueDate());
    }

    @Test
    void testContainTag() {
        testTask.addTag("a");
        assertTrue(testTask.containsTag("a"));
        testTask.addTag("c");
        assertTrue(testTask.containsTag("a"));
        testTask.removeTag("a");
        assertFalse(testTask.containsTag("a"));
    }

    @Test
    void testContainTag2() {
        assertFalse(testTask.containsTag("a"));
    }

    @Test
    void testContainTag3() {
        testTask.addTag("a");
        testTask.addTag("c");
        testTask.addTag("d");
        assertFalse(testTask.containsTag("1"));
    }



    @Test
    void testConstruct1() {
        try {
            Task tryTask = new Task("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testConstruct2() {
        String tryString = null;
        try {
            Task tryTask = new Task(tryString);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testConstruct3213123() {
        try {
            Task tryTask = new Task("a");
        } catch (EmptyStringException e) {
            fail("The description is not empty. Shouldn't throw EmptyStringException");
        }
    }

    @Test
    void getNameTest() {
        assertEquals("Test Tag",testTag.getName());
    }

    @Test
    void testAddTagException1() {
        try {
            testTask.addTag("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void getDes11() {
        Todo todo = new Project("a");
        assertEquals("a",todo.getDescription());
    }

    @Test
    void testAddTagException2() {
        String tryString = null;
        try {
            testTask.addTag(tryString);
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testAddTagException3() {
        try {
            testTask.addTag("abc");
        } catch (EmptyStringException e) {
            fail("The string is provided properly. Shouldn't throw exceptions.");
        }
    }

    @Test
    void testRemoveTagException1() {
        try {
            testTask.removeTag("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testRemoveTagException2() {
        String tryString = null;
        try {
            testTask.removeTag(tryString);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testRemoveTagException3() {
        try {
            testTask.removeTag("a");
        } catch (EmptyStringException e) {
            fail("The string is provided properly. Shouldn't throw exceptions.");
        }
    }

    @Test
    void testSetPriorityException1() {
        try {
            testTask.setPriority(new Priority(5));
        } catch (InvalidPriorityLevelException e) {
        }
    }

    @Test
    void testSetPriorityException2() {
        Priority tryPriority = null;
        try {
            testTask.setPriority(tryPriority);
        } catch (NullArgumentException e) {
        }
    }

    @Test
    void testSetPriorityException3() {
        try {
            testTask.setPriority(new Priority(3));
        } catch (InvalidPriorityLevelException e) {
            fail("Shouldn't be catching exceptions.");
        }
    }

    @Test
    void testSetStatusException1() {
        Status st = null;
        try {
            testTask.setStatus(st);
        } catch (NullArgumentException e) {
        }
    }

    @Test
    void testSetStatusException2() {
        try {
            testTask.setStatus(Status.UP_NEXT);
        } catch (NullArgumentException e) {
            fail("NullArgumentException should not be caught here.");
        }
    }

    @Test
    void testSetDueDateException1() {
        DueDate tryDueDate = null;
        try {
            testTask.setDueDate(tryDueDate);
        } catch (NullArgumentException e) {
            fail();
        }
    }

    @Test
    void testSetDueDateException2() {
        try {
            testTask.setDueDate(dueDate);
        } catch (NullArgumentException e) {
            fail("The dueDate is not null!");
        }
    }

    @Test
    void testSetDescription1() {
        try {
            testTask.setDescription("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testSetDescription2() {
        String str = null;
        try {
            testTask.setDescription(str);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testSetDescription3() {
        try {
            testTask.setDescription("a");
        } catch (EmptyStringException e) {
            fail("Description is provided properly.");
        }
    }



    @Test
    void testC4() {
        try {
            Task tryTask = new Task("       Task description ## tag1; tag2; tag3");
            System.out.println(tryTask.toString());
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    void testC5() {
        try {
            Task t = new Task("Some description ## tAg1; tag1; TAG1; tag1;tag 1; tah");
            System.out.println(t.toString());
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    void testC6() {
        try {
            Task t = new Task("Some description ##tag1;today;up next;tomorrow;in progress");
            System.out.println(t.toString());
            assertTrue(t.containsTag("tomorrow"));
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    void testSetDes() {
        try {
            testTask.setDescription("This description has just tag delimiter. ## ");
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    void testSetNullDes() {
        try {
            testTask.setDescription("");
            fail();
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    void testC10() {
        try {
            Task tryTask = new Task("Some description ## tag1;today;up next;tomorrow;in progress");
            System.out.println(tryTask.toString());
            assertTrue(tryTask.containsTag("tag1"));
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    void testTodayNullArgument() {
        try {
            testTask.setDescription("This description has just tag delimiter. ## tomorrow; TodaY;abc");
        } catch (EmptyStringException e) {
            fail();
        }
        System.out.println(testTask.toString());
        assertTrue(testTask.containsTag("abc"));
    }
    @Test
    void testSameDescription() {
        Task task1 = new Task("Test");
        assertEquals(task1,testTask);
    }

    @Test
    void differentDescriptionSameOthers() {
        Task task1 = new Task("test task");
        assertFalse(task1.equals(testTask));
    }

    @Test
    void testSameDescriptionDifferentPriority() {
        Task task1 = new Task("Test");
        System.out.println(task1.getPriority());
        System.out.println(testTask.getPriority());
        task1.setPriority(new Priority(1));
        System.out.println(task1.getPriority());
        assertFalse(task1.getPriority().equals(testTask.getPriority()));
        System.out.println(task1.equals(testTask));
        testTask.setPriority(new Priority(1));
        assertEquals(task1,testTask);
    }

    @Test
    void testDifferentDueDate() {
        DueDate dueDate = new DueDate(Calendar.getInstance().getTime());
        Task task1 = new Task("Test");
        task1.setDueDate(dueDate);
        assertFalse(task1.equals(testTask));
        testTask.setDueDate(dueDate);
        assertEquals(task1,testTask);
    }

    @Test
    void testDifferentStatus() {
        Task task1 = new Task("Test");
        task1.setStatus(Status.DONE);
        assertFalse(task1.equals(testTask));
        testTask.setStatus(Status.DONE);
        assertEquals(task1,testTask);
    }

    @Test
    void testAddTag1() {
        testTask.addTag(testTag);
        testTask.addTag("Test Tag");
        assertEquals(1,testTask.getTags().size());
        assertTrue(testTag.containsTask(new Task("Test")));
        assertTrue(testTask.containsTag(testTag));
    }

    @Test
    void testAddTag2111() {
        testTask.addTag(testTag);
        testTask.addTag("Test Tag");
        testTask.addTag("Test2");
        testTag.addTask(testTask);
        assertEquals(2,testTask.getTags().size());
        assertTrue(testTag.containsTask(testTask));
        assertTrue(testTask.containsTag(new Tag("Test2")));
        assertTrue(testTask.containsTag(testTag));
    }

    @Test
    void testRemoveTag1111() {
        testTask.addTag("Tag1");
        testTask.addTag("Tag2");
        testTask.addTag("Tag3");
        testTask.addTag(testTag);
        assertEquals(4,testTask.getTags().size());
        testTask.removeTag("Tag1");
        assertEquals(3,testTask.getTags().size());
        assertTrue(testTask.containsTag("Tag2"));
        assertFalse(testTask.containsTag("Tag1"));
    }

    @Test
    void testRemoveTag112() {
        testTask.addTag("Tag1");
        testTask.addTag("Tag2");
        testTask.addTag(new Tag("Tag3"));
        testTask.addTag(testTag);
        assertEquals(4,testTask.getTags().size());
        testTask.removeTag("Tag1");
        assertEquals(3,testTask.getTags().size());
        assertTrue(testTask.containsTag("Tag2"));
        assertFalse(testTask.containsTag("Tag1"));
        testTask.removeTag("Tag3");
        assertEquals(2,testTask.getTags().size());
        assertTrue(testTag.containsTask(testTask));
        testTask.removeTag(testTag);
        assertFalse(testTag.containsTask(testTask));
        assertFalse(testTask.containsTag(testTag));
    }

    @Test
    void testSetProgress1() {
        testTask.setProgress(50);
        assertEquals(50,testTask.getProgress());
    }

    @Test
    void testSetProgress2() {
        try {
            testTask.setProgress(50);
            assertEquals(50, testTask.getProgress());
        } catch (NegativeInputException e) {
            fail();
        }
    }

    @Test
    void testSetProgress3() {
        try {
            testTask.setProgress(-50);
            assertEquals(50, testTask.getProgress());
        } catch (InvalidProgressException e) {
            //
        }
    }

    @Test
    void testSetProgress4() {
        try {
            testTask.setProgress(1000);
            assertEquals(50, testTask.getProgress());
        } catch (InvalidProgressException e) {
            //
        }
    }

    void testException(int num) throws NegativeInputException {
        if (num == 1) {
            throw new NegativeInputException();
        }
    }

    void testException5(int num) throws NegativeInputException {
        if (num == 5) {
            throw new NegativeInputException("Negative");
        }
    }

    @Test
    void emptyStringExceptionTest2() {
        String testString = null;
        try {
            testTag = new Tag(testString);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testException5() {
        try {
            testException5(5);
        } catch (NegativeInputException e) {
            //
        }
    }

    @Test
    void testException1() {
        try {
            testException(1);
            fail();
        } catch (NegativeInputException e) {
        }
    }

    @Test
    void testException2() {
        try {
            testException(2);
        } catch (NegativeInputException e) {
            fail();
        }
    }

    void testException2(int num) throws InvalidProgressException {
        if (num == 1) {
            throw new InvalidProgressException("Invalid");
        }
    }

    @Test
    void testException3() {
        try {
            testException2(1);
            fail();
        } catch (InvalidProgressException e) {
        }
    }

    @Test
    void testException4() {
        try {
            testException2(2);
        } catch (InvalidProgressException e) {
            fail();
        }
    }

    @Test
    void testEqual1() {
        Task task1 = new Task("Test");
        Task task2 = new Task("Test");
        assertTrue(task1.equals(task2));
    }

    @Test
    void testEqual2() {
        Task task1 = new Task("Test");
        Task task2 = new Task("Test2");
        assertFalse(task1.equals(task2));
    }

    @Test
    void testContainsTag() {
        Tag tag = null;
        try {
            assertFalse(testTask.containsTag(tag));
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void checkDes() {
        Todo todo = new Task("Test");
        assertEquals(todo.getDescription(),"Test");
    }

    @Test
    void testtest1() {
        testTask.equals(testTask);
    }

    @Test
    void testtest2() {
        assertFalse(testTask.equals(testTag));
    }

    @Test
    void tag1() {
        testTask.toString();
    }

    @Test
    void desDes() {
        Todo todo = new Project("Test");
        assertEquals(todo.getDescription(),"Test");
    }

    @Test
    void containTag() {
        try {
            testTask.containsTag("");
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void removeTag() {
        try {
            Tag tag = null;
            testTask.removeTag(tag);
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void setEst1() {
        testTask.setEstimatedTimeToComplete(10);
        assertEquals(10,testTask.getEstimatedTimeToComplete());
    }

    @Test
    void setEst2() {
        try {
            testTask.setEstimatedTimeToComplete(-11);
        } catch (NegativeInputException e) {
            //
        }
    }

    @Test
    void addTag123() {
        try {
            Tag tag = null;
            testTask.addTag(tag);
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void addTag12() {
        try {
            Tag tag = new Tag("a");
            testTask.addTag(tag);
        } catch (NullArgumentException e) {
            fail();
        }
    }

    @Test
    void cons() {
        try {
            Task task = new Task("");
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void tagTest1() {
        assertFalse(testTag.equals(testTask));
    }

    @Test
    void tagTest2() {
        Tag tag1 = new Tag("a");
        Tag tag2 = new Tag("a");
        assertTrue(tag1.equals(tag2));
    }

    @Test
    void tagTest3() {
        Tag tag1 = new Tag("a");
        Tag tag2 = new Tag("b");
        tag2.equals(testTag);
        assertFalse(tag1.equals(tag2));
    }

    @Test
    void test99() {
        testTag.getTasks();
    }

    @Test
    void containssss() {
        try {
            Task task = null;
            testTag.containsTask(task);
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void test100() {
        try {
            Todo todo = new Task("s");
            todo.setPriority(null);
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void containsTest1() {
        try {
            String str = null;
            testTask.containsTag(str);
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void containsTest2() {
        try {
            String str = "";
            testTask.containsTag(str);
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void testSameNameTag() {
        Tag tag1 = new Tag("Test Tag");
        assertEquals(tag1,testTag);
    }

    @Test
    void testLowerCaseName() {
        Tag tag1 = new Tag("test tag");
        assertFalse(testTag.equals(tag1));
    }

    @Test
    void testAddTask1() {
        testTag.addTask(testTask);
        assertEquals(1,testTag.getTasks().size());
        assertTrue(testTag.containsTask(new Task("Test")));
        assertTrue(testTask.containsTag(testTag));
    }

    @Test
    void testAddTask2() {
        testTag.addTask(testTask);
        testTag.addTask(testTask);
        testTag.addTask(new Task("Task1"));
        testTag.addTask(new Task("Task2"));
        assertEquals(3,testTag.getTasks().size());
        assertTrue(testTag.containsTask(new Task("Task1")));
        assertTrue(testTag.containsTask(new Task("Task2")));
        assertTrue(testTask.containsTag(testTag));
    }

    @Test
    void testRemoveTask1() {
        testTag.addTask(new Task("Task1"));
        testTag.addTask(new Task("Task2"));
        testTag.addTask(new Task("Task3"));
        testTag.addTask(testTask);
        assertEquals(4,testTag.getTasks().size());
        assertTrue(testTag.containsTask(new Task("Task2")));
        testTag.removeTask(new Task("Task2"));
        assertFalse(testTag.containsTask(new Task("Task2")));
    }

    @Test
    void testRemoveTask2() {
        testTag.addTask(new Task("Task1"));
        testTag.addTask(testTask);
        assertEquals(2,testTag.getTasks().size());
        assertTrue(testTag.containsTask(testTask));
        testTag.removeTask(testTask);
        assertFalse(testTag.containsTask(testTask));
    }


    @Test
    void testSameDescription123() {
        Task task1 = new Task("Test");
        assertEquals(task1,testTask);
    }

    @Test
    void differentDescriptionSameOthers123() {
        Task task1 = new Task("test task");
        assertFalse(task1.equals(testTask));
    }

    @Test
    void testSameDescriptionDifferentPriority12312() {
        Task task1 = new Task("Test");
        task1.setPriority(new Priority(1));
        System.out.println(task1.getPriority());
        System.out.println(testTask.getPriority());
        assertFalse(task1.equals(testTask));
        testTask.setPriority(new Priority(1));
        assertEquals(task1,testTask);
    }

    @Test
    void testDifferentDueDate123() {
        DueDate dueDate = new DueDate(Calendar.getInstance().getTime());
        Task task1 = new Task("Test");
        task1.setDueDate(dueDate);
        assertFalse(task1.equals(testTask));
        testTask.setDueDate(dueDate);
        assertEquals(task1,testTask);
    }

    @Test
    void testDifferentStatus123() {
        Task task1 = new Task("Test");
        task1.setStatus(Status.DONE);
        assertFalse(task1.equals(testTask));
        testTask.setStatus(Status.DONE);
        assertEquals(task1,testTask);
    }

    @Test
    void testAddTag1123() {
        testTask.addTag(testTag);
        testTask.addTag("Test Tag");
        assertEquals(1,testTask.getTags().size());
        assertTrue(testTag.containsTask(new Task("Test")));
        assertTrue(testTask.containsTag(testTag));
    }

    @Test
    void testAddTag2213() {
        testTask.addTag(testTag);
        testTask.addTag("Test Tag");
        testTask.addTag("Test2");
        testTag.addTask(testTask);
        assertEquals(2,testTask.getTags().size());
        assertTrue(testTag.containsTask(testTask));
        assertTrue(testTask.containsTag(new Tag("Test2")));
        assertTrue(testTask.containsTag(testTag));
    }

    @Test
    void testRemoveTag1() {
        testTask.addTag("Tag1");
        testTask.addTag("Tag2");
        testTask.addTag("Tag3");
        testTask.addTag(testTag);
        assertEquals(4,testTask.getTags().size());
        testTask.removeTag("Tag1");
        assertEquals(3,testTask.getTags().size());
        assertTrue(testTask.containsTag("Tag2"));
        assertFalse(testTask.containsTag("Tag1"));
    }

    @Test
    void testRemoveTag3213() {
        testTask.addTag("Tag1");
        testTask.addTag("Tag2");
        testTask.addTag(new Tag("Tag3"));
        testTask.addTag(testTag);
        assertEquals(4,testTask.getTags().size());
        testTask.removeTag("Tag1");
        assertEquals(3,testTask.getTags().size());
        assertTrue(testTask.containsTag("Tag2"));
        assertFalse(testTask.containsTag("Tag1"));
        testTask.removeTag("Tag3");
        assertEquals(2,testTask.getTags().size());
        assertTrue(testTag.containsTask(testTask));
        testTask.removeTag(testTag);
        assertFalse(testTag.containsTask(testTask));
        assertFalse(testTask.containsTag(testTag));
    }


    @Test
    void testAdd() {
        project.add(task);
        assertTrue(project.contains(task));
        assertFalse(project.isCompleted());
    }

    @Test
    void testAdd2() {
        project.add(task);
        project.add(task);
        assertEquals(1,project.getNumberOfTasks());
    }

    @Test
    void testRemove() {
        Task task2 = new Task("test2");
        project.add(task);
        project.add(task2);
        assertTrue(project.contains(task2));
        project.remove(task2);
        assertFalse(project.contains(task2));
    }
    @Test
    void testRemove2() {
        Task task2 = new Task("test2");
        Task task3 = new Task("test3");
        project.add(task);
        project.add(task2);
        assertTrue(project.contains(task2));
        project.remove(task3);
        assertTrue(project.contains(task2));
    }


    @Test
    void testGetDescription() {
        assertEquals("cpsc210 Project", project.getDescription());
    }


    @Test
    void testGetProgress2() {
        task.setStatus(Status.DONE);
        task.setProgress(100);
        project.add(task);
        assertEquals(100,project.getProgress());
    }

    @Test
    void testGetProgress3() {
        task.setStatus(Status.UP_NEXT);
        project.add(task);
        assertEquals(0,project.getProgress());
    }


    @Test
    void testGetNumOfTasks() {
        assertEquals(0,project.getNumberOfTasks());
        project.add(task);
        assertEquals(1,project.getNumberOfTasks());
    }

    @Test
    void testIsCompleted() {
        assertFalse(project.isCompleted());
        project.add(task);
        assertFalse(project.isCompleted());
    }


    @Test
    void testConstruct1213123() {
        try {
            Project tryProject = new Project("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testConstruct2213123() {
        String tryString = null;
        try {
            Project tryProject = new Project(tryString);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testConstruct3() {
        try {
            Project tryProject = new Project("a");
        } catch (EmptyStringException e) {
            fail("The description is provided. Shouldn't throw EmptyStringException.");
        }
    }

    @Test
    void testAddException1() {
        Task tryTask = null;
        try {
            project.add(tryTask);
        } catch (NullArgumentException e) {
        }
    }
    @Test
    void testAddException2() {
        try {
            project.add(task);
        } catch (NullArgumentException e) {
            fail("The task provided is not null!");
        }
    }

    @Test
    void testRemoveException1() {
        Task tryTask = null;
        try {
            project.remove(tryTask);
        } catch (NullArgumentException e) {
        }
    }

    @Test
    void testRemoveException2() {
        try {
            project.remove(task);
        } catch (NullArgumentException e) {
            fail("The task provided is not null!");
        }
    }

    @Test
    void testContainsException1() {
        Task tryTask = null;
        try {
            project.contains(tryTask);
        } catch (NullArgumentException e) {
        }
    }

    @Test
    void testContainsException2() {
        try {
            project.contains(task);
        } catch (NullArgumentException e) {
            fail("The task provided is not null!");
        }
    }

    @Test
    void usePrior() {
        Todo todo = new Task("a");
        todo.getPriority();
    }

    @Test
    void testTagEqual() {
        Tag tag = new Tag("a");
        tag.equals(tag);
    }


    @Test
    void setProgressTest1() {
        try {
            testTask.setProgress(1000);
        } catch (InvalidProgressException e) {
            //
        }
    }

    @Test
    void setProgressTest2() {
        try {
            testTask.setProgress(-1000);
        } catch (InvalidProgressException e) {
            //
        }
    }

    @Test
    void setProgressTest3() {
        try {
            testTask.setProgress(50);
        } catch (InvalidProgressException e) {
            fail();
        }
    }

    @Test
    void setEstime1() {
        try {
            testTask.setEstimatedTimeToComplete(1000);
        } catch (NegativeInputException e) {
            fail();
        }
    }

    @Test
    void setEstime2() {
        try {
            testTask.setEstimatedTimeToComplete(-1000);
        } catch (NegativeInputException e) {
            //
        }
    }

    @Test
    void addTagTry1() {
        String str = null;
        try {
            testTask.addTag(str);
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void addTagTry2() {
        String str = "";
        try {
            testTask.addTag(str);
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void addTagTry4() {
        String str = "abc";
        try {
            testTask.addTag(str);
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    void addTagTry3() {
        Tag tag = null;
        try {
            testTask.addTag(tag);
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void removeTagTry1() {
        String str = null;
        try {
            testTask.removeTag(str);
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void removeTagTry2() {
        String str = "";
        try {
            testTask.removeTag(str);
        } catch (EmptyStringException e) {
            //
        }
    }

    @Test
    void removeTagTry3() {
        String str = "abc";
        try {
            testTask.removeTag(str);
        } catch (EmptyStringException e) {
            fail();
        }
    }

    @Test
    void removeTagTry4() {
        Tag tag = null;
        try {
            testTask.removeTag(tag);
        } catch (NullArgumentException e) {
            //
        }
    }

    @Test
    void removeTagTry5() {
        Tag tag = new Tag("a");
        try {
            testTask.removeTag(tag);
        } catch (NullArgumentException e) {
            fail();
        }
    }


}