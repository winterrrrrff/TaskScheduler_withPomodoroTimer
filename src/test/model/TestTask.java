package model;

import model.exceptions.EmptyStringException;
import model.exceptions.InvalidPriorityLevelException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.TagParser;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    private Task testTask;
    private Priority priority;
    private DueDate dueDate;
    private TagParser tagParser;
    private Tag testTag;

    @BeforeEach
    public void constructor() {
      //  tagParser = new TagParser();
        testTask = new Task("Test");
        priority = new Priority(1);
        dueDate = new DueDate();
        testTag = new Tag("Test Tag");
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
    void testGetDescription() {
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
        task1.setPriority(new Priority(1));
        assertFalse(task1.equals(testTask));
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


}