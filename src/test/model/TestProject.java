package model;

import model.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.TagParser;
import parsers.exceptions.ParsingException;

import java.util.Calendar;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestProject {

    private Project project;
    private Task testTask;
    private Tag testTag;
    private Task task;
    private DueDate dueDate;
    private TagParser tagParser;
    private Priority priority;
    private Priority priorityDefault;
    private Priority priorityQuadrant1;
    private Priority priorityQuadrant2;
    private Priority priorityQuadrant3;
    private Priority priorityQuadrant4;
    private String IANDU = "IMPORTANT & URGENT";
    private String IM = "IMPORTANT";
    private String UR = "URGENT";
    private String DE = "DEFAULT";

    @BeforeEach
    public void constructor() {
        project = new Project("cpsc210 Project");
        testTask = new Task("Project Phase1");
        testTag = new Tag("Test Tag");
        priority = new Priority(1);
        priorityDefault = new Priority();
        priorityQuadrant1 = new Priority(1);
        priorityQuadrant2 = new Priority(2);
        priorityQuadrant3 = new Priority(3);
        priorityQuadrant4 = new Priority(4);
        task = new Task("Project Phase1");
        tagParser = new TagParser();
        dueDate = new DueDate();
    }

    @Test
    void testAdd() {
        project.add(testTask);
        assertTrue(project.contains(testTask));
        assertFalse(project.isCompleted());
    }

    @Test
    void testAdd2() {
        project.add(testTask);
        project.add(testTask);
        assertEquals(1, project.getNumberOfTasks());
    }

    @Test
    void testAdd3() {
        Todo task1 = new Task("1");
        task1.setPriority(new Priority(1));
        Todo task2 = new Task("2");
        task2.setPriority(new Priority(3));
        Todo task3 = new Task("3");
        Todo project1 = new Project("a");
        Todo project2 = new Project("b");
        project2.setPriority(new Priority(2));
        Todo project3 = new Project("c");
        project3.setPriority(new Priority(4));
        project.add(task2);
        project.add(project3);
        project.add(task3);
        project.add(project1);
        project.add(project2);
        project.add(task1);
        assertEquals(6,project.getNumberOfTasks());
    }

    @Test
    void testRemove() {
        Task task2 = new Task("test2");
        project.add(testTask);
        project.add(task2);
        assertTrue(project.contains(task2));
        project.remove(task2);
        assertFalse(project.contains(task2));
    }

    @Test
    void testRemove2() {
        Task task2 = new Task("test2");
        Task task3 = new Task("test3");
        project.add(testTask);
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
    void testGetProgress() {
        assertEquals(0, project.getProgress());
        project.add(testTask);
        assertEquals(0, project.getProgress());
        testTask.setStatus(Status.DONE);
    }

    @Test
    void testGetProgress2() {
        testTask.setStatus(Status.DONE);
        project.add(testTask);
        assertEquals(0, project.getProgress());
    }

    @Test
    void testGetProgress3() {
        testTask.setStatus(Status.UP_NEXT);
        project.add(testTask);
        assertEquals(0, project.getProgress());
    }


    @Test
    void testGetNumOfTasks() {
        assertEquals(0, project.getNumberOfTasks());
        project.add(testTask);
        assertEquals(1, project.getNumberOfTasks());
    }

    @Test
    void testIsCompleted() {
        assertFalse(project.isCompleted());
        project.add(testTask);
        assertFalse(project.isCompleted());
    }

    @Test
    void testIsCompleted2() {
        testTask.setStatus(Status.DONE);
        project.add(testTask);
        assertFalse(project.isCompleted());
    }

    @Test
    void testConstruct11231231() {
        try {
            Project tryProject = new Project("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testConstruct2213() {
        String tryString = null;
        try {
            Project tryProject = new Project(tryString);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testConstruct3123213() {
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
            project.add(testTask);
        } catch (NullArgumentException e) {
            fail("The testTask provided is not null!");
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
            project.remove(testTask);
        } catch (NullArgumentException e) {
            fail("The testTask provided is not null!");
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
            project.contains(testTask);
        } catch (NullArgumentException e) {
            fail("The testTask provided is not null!");
        }
    }

    @Test
    void testGetNumOfTasks2() {
        Task task22 = new Task("abc");
        Project project2 = new Project("2");
        project2.add(task22);
        project.add(new Task("1"));
        project.add(new Task("2"));
        project.add(new Task("3"));
        project.add(new Task("4"));
        project.add(project2);
        System.out.println(project.getNumberOfTasks());

    }

    @Test
    void getProgessTest() {
        try {
            Task task123 = new Task("T");
            task123.setProgress(2);
            Project project123 = new Project("a");
            Project project = new Project("Test");
            project.add(task123);
            project.add(project123);
            System.out.println(project.getProgress());
            assertEquals(1, project.getProgress());
        } catch (InvalidProgressException e) {
            //
        }
    }


    @Test
    void testtttt() {
        Project project123 = new Project("1");
        Project project567 = new Project("2");
        Task task123 = new Task("a");
        Task task456 = new Task("b");
        Task task1789 = new Task("d");
        Task task1011 = new Task("e");
        project123.add(task123);
        project123.add(task456);
        project123.add(task1789);
        project567.add(task1011);
        project567.add(project123);
        System.out.println(project567.getNumberOfTasks());
    }


    @Test
    void testOnline1() {
        Task task1 = new Task("1");
        Task task2 = new Task("2");
        Task task3 = new Task("3");
        Project projectYo = new Project("Test");
        projectYo.add(task1);
        projectYo.add(task2);
        projectYo.add(task3);
        assertEquals(0,projectYo.getProgress());
        assertEquals(3,projectYo.getNumberOfTasks());
    }

    @Test
    void testOnline2() {
        try {
            Task task1 = new Task("1");
            task1.setProgress(100);
            Task task2 = new Task("2");
            Task task3 = new Task("3");
            Project projectYo = new Project("Test");
            projectYo.add(task1);
            projectYo.add(task2);
            projectYo.add(task3);
            assertEquals(33, projectYo.getProgress());
        } catch (InvalidProgressException e) {
            //
        }
    }

    @Test
    void testOnline3() {
        try {
            Task task1 = new Task("1");
            task1.setProgress(100);
            Task task2 = new Task("2");
            task2.setProgress(50);
            Task task3 = new Task("3");
            task3.setProgress(25);
            Project projectYo = new Project("Test");
            projectYo.add(task1);
            projectYo.add(task2);
            projectYo.add(task3);
            assertEquals(58, projectYo.getProgress());
        } catch (InvalidProgressException e) {
            //
        }
    }

    @Test
    void testOnline4() {
        try {
            Task task1 = new Task("1");
            task1.setProgress(100);
            Task task2 = new Task("2");
            task2.setProgress(50);
            Task task3 = new Task("3");
            task3.setProgress(25);
            Project projectYo = new Project("Test");
            projectYo.add(task1);
            projectYo.add(task2);
            projectYo.add(task3);
            Project project2 = new Project("2222");
            Task task4 = new Task("4");
            task4.setProgress(0);
            project2.add(projectYo);
            project2.add(task4);
            System.out.println(project2.getNumberOfTasks());
            assertEquals(29, project2.getProgress());
        } catch (InvalidProgressException e) {
            //
        }
    }

    @Test
    void testOnline5() {
        try {
            Task task1 = new Task("1");
            task1.setProgress(100);
            Task task2 = new Task("2");
            task2.setProgress(50);
            Task task3 = new Task("3");
            task3.setProgress(25);
            Project projectYo = new Project("Test");
            projectYo.add(task1);
            projectYo.add(task2);
            projectYo.add(task3);
            Project project2 = new Project("2222");
            Task task4 = new Task("4");
            task4.setProgress(5);
            project2.add(new Task("hi"));
            project2.add(task4);
            project2.add(new Task("hello"));
            project2.add(projectYo);
            System.out.println(project2.getNumberOfTasks());
            assertEquals(15, project2.getProgress());
        } catch (InvalidProgressException e) {
            //
        }
    }


    @Test
    void testTime1() {
        Project project1 = new Project("1");
        Task task1 = new Task("a");
        Task task2 = new Task("b");
        Task task3 = new Task("c");
        project1.add(task1);
        project1.add(task2);
        project1.add(task3);
        assertEquals(0,project1.getEstimatedTimeToComplete());
    }

    @Test
    void testTime2() {
        try {
            Project project1 = new Project("1");
            Task task1 = new Task("a");
            task1.setEstimatedTimeToComplete(8);
            Task task2 = new Task("b");
            Task task3 = new Task("c");
            project1.add(task1);
            project1.add(task2);
            project1.add(task3);
            assertEquals(8, project1.getEstimatedTimeToComplete());
        } catch (NegativeInputException e) {
            //
        }
    }

    @Test
    void testTime3() {
        try {
            Project project1 = new Project("1");
            Task task1 = new Task("a");
            task1.setEstimatedTimeToComplete(8);
            Task task2 = new Task("b");
            task2.setEstimatedTimeToComplete(2);
            Task task3 = new Task("c");
            project1.add(task1);
            project1.add(task2);
            project1.add(task3);
            assertEquals(10, project1.getEstimatedTimeToComplete());
        } catch (NegativeInputException e) {
            //
        }
    }
    @Test
    void testTime4() {
        try {
            Project project1 = new Project("1");
            Task task1 = new Task("a");
            task1.setEstimatedTimeToComplete(8);
            Task task2 = new Task("b");
            task2.setEstimatedTimeToComplete(2);
            Task task3 = new Task("c");
            task3.setEstimatedTimeToComplete(10);
            project1.add(task1);
            project1.add(task2);
            project1.add(task3);
            assertEquals(20, project1.getEstimatedTimeToComplete());
        } catch (NegativeInputException e) {
            //
        }
    }

    @Test
    void testTime5() {
        try {
            Project project1 = new Project("1");
            Task task1 = new Task("a");
            task1.setEstimatedTimeToComplete(8);
            Task task2 = new Task("b");
            task2.setEstimatedTimeToComplete(2);
            Task task3 = new Task("c");
            task3.setEstimatedTimeToComplete(10);
            Task task4 = new Task("e");
            task4.setEstimatedTimeToComplete(4);
            Project project2 = new Project("2");
            project1.add(task1);
            project1.add(task2);
            project1.add(task3);
            project2.add(task4);
            project2.add(project1);
            assertEquals(24, project2.getEstimatedTimeToComplete());
        } catch (NegativeInputException e) {
            //
        }
    }


    @Test
    void hash() {
        testTask.hashCode();
    }

    @Test
    void hash2() {
        project.hashCode();
    }

    @Test
    void whileTest() {
        int x = 0;
        while (x < 3) {
            System.out.println(x);
            x++;
        }
        System.out.println(project.getPriority());
    }

    @Test
    void testGetTask() {
        try {
            project.getTasks();
        } catch (UnsupportedOperationException e) {
            //
        }
    }

    @Test
    void testSameDescription() {
        Task task1 = new Task("Project Phase1");
        assertEquals(task1,testTask);
    }

    @Test
    void differentDescriptionSameOthers() {
        Task task1 = new Task("test testTask");
        assertFalse(task1.equals(testTask));
    }

    @Test
    void testSameDescriptionDifferentPriority() {
        Task task1 = new Task("Project Phase1");
        task1.setPriority(new Priority(1));
        assertFalse(task1.equals(testTask));
        testTask.setPriority(new Priority(1));
        assertEquals(task1,testTask);
    }

    @Test
    void testDifferentDueDate() {
        DueDate dueDate = new DueDate(Calendar.getInstance().getTime());
        Task task1 = new Task("Project Phase1");
        task1.setDueDate(dueDate);
        assertFalse(task1.equals(testTask));
        testTask.setDueDate(dueDate);
        assertEquals(task1,testTask);
    }

    @Test
    void testDifferentStatus() {
        Task task1 = new Task("Project Phase1");
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
        assertTrue(testTag.containsTask(new Task("Project Phase1")));
        assertTrue(testTask.containsTag(testTag));
    }

    @Test
    void testAddTag2() {
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
    void testRemoveTag2() {
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
    void constructorDefaultTest() {
        assertFalse(priorityDefault.isImportant());
        assertFalse(priorityDefault.isUrgent());
    }

    @Test
    void constructorQuadrant1Test() {
        assertTrue(priorityQuadrant1.isImportant());
        assertTrue(priorityQuadrant1.isUrgent());
    }

    @Test
    void constructorQuadrant2Test() {
        assertTrue(priorityQuadrant2.isImportant());
        assertFalse(priorityQuadrant2.isUrgent());
    }

    @Test
    void constructorQuadrant3Test() {
        assertFalse(priorityQuadrant3.isImportant());
        assertTrue(priorityQuadrant3.isUrgent());
    }

    @Test
    void constructorQuadrant4Test() {
        assertFalse(priorityQuadrant4.isImportant());
        assertFalse(priorityQuadrant4.isUrgent());
    }

    @Test
    void setImportantDefaultTest() {
        priorityDefault.setImportant(true);
        assertTrue(priorityDefault.isImportant());
        priorityDefault.setImportant(false);
        assertFalse(priorityDefault.isImportant());
    }

    @Test
    void setUrgentDefaultTest() {
        priorityDefault.setUrgent(false);
        assertFalse(priorityDefault.isUrgent());
        priorityDefault.setUrgent(true);
        assertTrue(priorityDefault.isUrgent());
    }

    @Test
    void setImportantQuadrantTest() {
        priorityQuadrant1.setImportant(true);
        assertTrue(priorityQuadrant1.isImportant());
        priorityQuadrant1.setImportant(false);
        assertFalse(priorityQuadrant1.isImportant());
    }

    @Test
    void setUrgentQuadrantTest() {
        priorityQuadrant3.setUrgent(false);
        assertFalse(priorityQuadrant3.isUrgent());
        priorityQuadrant3.setUrgent(true);
        assertTrue(priorityQuadrant3.isUrgent());
    }

    @Test
    void toStringTest() {
        assertEquals(DE, priorityDefault.toString());
        assertEquals(IANDU, priorityQuadrant1.toString());
        assertEquals(IM, priorityQuadrant2.toString());
        assertEquals(UR, priorityQuadrant3.toString());
    }

    @Test
    void testConstruct1345() {
        try {
            Priority prior = new Priority(-1);
        } catch (InvalidPriorityLevelException e) {

        }
    }

    @Test
    void testConstruct2213123() {
        try {
            Priority prior = new Priority(5);
        } catch (InvalidPriorityLevelException e) {
        }
    }

    @Test
    void testConstruct3123() {
        try {
            Priority prior = new Priority(3);
        } catch (InvalidPriorityLevelException e) {
            fail("Throw InvalidPriorityLevelException when it should not.");
        }
    }


    @Test
    void testAdd12312() {
        project.add(task);
        assertTrue(project.contains(task));
        assertFalse(project.isCompleted());
    }

    @Test
    void testAdd2123() {
        project.add(task);
        project.add(task);
        assertEquals(1,project.getNumberOfTasks());
    }

    @Test
    void testRemove21312() {
        Task task2 = new Task("test2");
        project.add(task);
        project.add(task2);
        assertTrue(project.contains(task2));
        project.remove(task2);
        assertFalse(project.contains(task2));
    }
    @Test
    void testRemove2123123() {
        Task task2 = new Task("test2");
        Task task3 = new Task("test3");
        project.add(task);
        project.add(task2);
        assertTrue(project.contains(task2));
        project.remove(task3);
        assertTrue(project.contains(task2));
    }



    @Test
    void testGetDescription123() {
        assertEquals("cpsc210 Project", project.getDescription());
    }



    @Test
    void testGetProgress3123() {
        task.setStatus(Status.UP_NEXT);
        project.add(task);
        assertEquals(0,project.getProgress());
    }


    @Test
    void testGetNumOfTasks2131() {
        assertEquals(0,project.getNumberOfTasks());
        project.add(task);
        assertEquals(1,project.getNumberOfTasks());
    }

    @Test
    void testIsCompleted123() {
        assertFalse(project.isCompleted());
        project.add(task);
        assertFalse(project.isCompleted());
    }
    @Test
    void testIsCompleted2123() {
        task.setStatus(Status.DONE);
        task.setProgress(100);
        project.add(task);
        assertTrue(project.isCompleted());
    }

    @Test
    void testConstruct1123() {
        try {
            Project tryProject = new Project("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testConstruct2123() {
        String tryString = null;
        try {
            Project tryProject = new Project(tryString);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testConstruct3123123() {
        try {
            Project tryProject = new Project("a");
        } catch (EmptyStringException e) {
            fail("The description is provided. Shouldn't throw EmptyStringException.");
        }
    }

    @Test
    void testAddException1123() {
        Task tryTask = null;
        try {
            project.add(tryTask);
        } catch (NullArgumentException e) {
        }
    }
    @Test
    void testAddException212312() {
        try {
            project.add(task);
        } catch (NullArgumentException e) {
            fail("The task provided is not null!");
        }
    }

    @Test
    void testRemoveException1123() {
        Task tryTask = null;
        try {
            project.remove(tryTask);
        } catch (NullArgumentException e) {
        }
    }

    @Test
    void testRemoveException2123() {
        try {
            project.remove(task);
        } catch (NullArgumentException e) {
            fail("The task provided is not null!");
        }
    }

    @Test
    void testContainsException1123() {
        Task tryTask = null;
        try {
            project.contains(tryTask);
        } catch (NullArgumentException e) {
        }
    }

    @Test
    void testContainsException21231() {
        try {
            project.contains(task);
        } catch (NullArgumentException e) {
            fail("The task provided is not null!");
        }
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
    void getNameTest() {
        assertEquals("Test Tag", testTag.getName());
    }

    @Test
    void toStringTest23456() {
        assertEquals("#Test Tag", testTag.toString());
    }

    @Test
    void emptyStringExceptionTest() {
        try {
            testTag = new Tag("");
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void emptyStringExceptionTest21234() {
        String testString = null;
        try {
            testTag = new Tag(testString);
        } catch (EmptyStringException e) {
        }
    }

    @Test
    void testAddTag() {
        testTask.addTag("cpsc210");
        assertTrue(testTask.containsTag("cpsc210"));
    }

    @Test
    void testAddTag22131() {
        testTask.addTag("cpsc210");
        testTask.addTag("cpsc210");
        testTask.addTag("cpsc210");
        assertEquals(1,testTask.getTags().size());
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
    void testRemoveTag221312123() {
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
        assertEquals(1,testTask.getTags().size());
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
        assertEquals(Status.TODO,testTask.getStatus());
    }

    @Test
    void testSetStatus() {
        testTask.setStatus(Status.DONE);
        assertEquals(Status.DONE,testTask.getStatus());
        testTask.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS,testTask.getStatus());
    }

    @Test
    void testGetDescription1231() {
        assertEquals("Project Phase1", testTask.getDescription());
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
        assertEquals(null,testTask.getDueDate());
    }

    @Test
    void testSetDueDate() {
        testTask.setDueDate(dueDate);
        assertEquals(this.dueDate,testTask.getDueDate());
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
    void testConstruct3() {
        try {
            Task tryTask = new Task("a");
        } catch (EmptyStringException e) {
            fail("The description is not empty. Shouldn't throw EmptyStringException");
        }
    }

    @Test
    void testAddTagException1() {
        try {
            testTask.addTag("");
        } catch (EmptyStringException e) {

        }
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
            fail("Shouldn't be catching exception.");
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
    void testC5(){
        try{
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
    void testTodayNullArgument2() {
        try {
            tagParser.parse("This description has just tag delimiter. ## ;ToDay;;tomorrow;  ;today;123; ;jjj; zzzz",testTask);
        } catch (ParsingException e) {
            fail();
        } catch (NullArgumentException e) {
            fail();
        }
        System.out.println(testTask.toString());
        assertTrue(testTask.containsTag("zzzz"));
        assertTrue(testTask.containsTag("jjj"));
        assertTrue(testTask.containsTag("123"));
    }

    @Test
    void testtest() {
        Project project = new Project("a");
        assertTrue(project.equals(project));
    }

    @Test
    void testIterator() {
        Task task11 = new Task("aaa");
        task11.setPriority(new Priority(1));
        project.add(task11);
        project.add(new Task("a"));
        project.add(new Project("234"));
        project.add(new Project("1231231"));
        Iterator<Todo> iterator = project.iterator();
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void abc() {
        Task task11 = new Task("aaa");
        task11.setPriority(new Priority(1));
        Project project1 = new Project("1qsx");
        project1.setPriority(new Priority(2));
        project.add(task11);
        project.add(project1);
        project.add(new Task("a"));
        project.add(new Project("234"));
        project.add(new Project("1231231"));
        project.add(new Project("A"));
        Project project2 = new Project("1x");
        project2.setPriority(new Priority(3));
        project.add(project2);
        for (Todo t : project) {
            System.out.println(t.getDescription());
        }
    }

    @Test
    void realTest() {
        Task task1 = new Task("1");
        task1.setPriority(new Priority(1));
        Task task2 = new Task("2");
        task2.setPriority(new Priority(3));
        Task task3 = new Task("3");
        Project project1 = new Project("a");
        Project project2 = new Project("b");
        project2.setPriority(new Priority(2));
        Project project3 = new Project("c");
        project3.setPriority(new Priority(4));
        Task task4 = new Task("4 ## important");
        project.add(task3);
        project.add(project1);
        project.add(project2);
        project.add(task1);
        project.add(task2);
        project.add(project3);
        project.add(task4);
        for (Todo t : project)
            System.out.println(t.getDescription() + ": "+t.getPriority().toString());

    }

    @Test
    void realTest2() {
        Task task1 = new Task("1");
        task1.setPriority(new Priority(1));
        Task task2 = new Task("2");
        task2.setPriority(new Priority(3));
        Task task3 = new Task("3");
        Project project1 = new Project("a");
        Project project2 = new Project("b");
        project2.setPriority(new Priority(2));
        Project project3 = new Project("c");
        project3.setPriority(new Priority(4));
        project.add(task2);
        project.add(project3);
        project.add(task3);
        project.add(project1);
        project.add(project2);
        project.add(task1);
        for (Todo t : project)
            System.out.println(t.getDescription());
    }

    @Test
    void realTest3() {
        Todo task1 = new Task("1");
        task1.setPriority(new Priority(1));
        Todo task2 = new Task("2");
        task2.setPriority(new Priority(3));
        Todo task3 = new Task("3");
        Todo project1 = new Project("a");
        Todo project2 = new Project("b");
        project2.setPriority(new Priority(2));
        Todo project3 = new Project("c");
        project3.setPriority(new Priority(4));
        project.add(task2);
        project.add(project3);
        project.add(task3);
        project.add(project1);
        project.add(project2);
        project.add(task1);
        Iterator<Todo> iterator = project.iterator();
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
    }

    @Test
    void exceptionTest() {
        Project project = new Project("a");
        project.add(new Task("1"));
        try {
            Iterator iterator = project.iterator();
            iterator.next();
            iterator.next();
        } catch (NoSuchElementException e) {
            //
        }
    }


}