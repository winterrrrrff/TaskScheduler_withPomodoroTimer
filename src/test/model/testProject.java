package model;

import model.exceptions.InvalidProgressException;
import model.exceptions.NegativeInputException;
import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TestProject {

    private Project project;
    private Task task;

    @BeforeEach
    public void constructor() {
        project = new Project("cpsc210 Project");
        task = new Task("Project Phase1");
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
    void testGetProgress() {
        assertEquals(0, project.getProgress());
        project.add(task);
        assertEquals(0, project.getProgress());
        task.setStatus(Status.DONE);
    }

    @Test
    void testGetProgress2() {
        task.setStatus(Status.DONE);
        project.add(task);
        assertEquals(0, project.getProgress());
    }

    @Test
    void testGetProgress3() {
        task.setStatus(Status.UP_NEXT);
        project.add(task);
        assertEquals(0, project.getProgress());
    }


    @Test
    void testGetNumOfTasks() {
        assertEquals(0, project.getNumberOfTasks());
        project.add(task);
        assertEquals(1, project.getNumberOfTasks());
    }

    @Test
    void testIsCompleted() {
        assertFalse(project.isCompleted());
        project.add(task);
        assertFalse(project.isCompleted());
    }

    @Test
    void testIsCompleted2() {
        task.setStatus(Status.DONE);
        project.add(task);
        assertFalse(project.isCompleted());
    }

    @Test
    void testConstruct1() {
        try {
            Project tryProject = new Project("");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    void testConstruct2() {
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
        project.add(task3);
        project.add(project1);
        project.add(project2);
        project.add(task1);
        project.add(task2);
        project.add(project3);
        for (Todo t : project)
            System.out.println(t.getDescription());

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
       // project.add(project2);
        //project.add(task1);
        Iterator<Todo> iterator = project.iterator();
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        System.out.println(iterator.next().getDescription());
       // assertFalse(iterator.hasNext());
    }

    @Test
    void hash() {
        task.hashCode();
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

}


