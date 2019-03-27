package pomodoro;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;

public class PomodoroApp extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;

    public static void main(String[] args) {
        new PomodoroApp();
    }

    public PomodoroApp() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame frame = new JFrame("Pomodoro");
                frame.setSize(WIDTH,HEIGHT);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sidebar = new JTabbedPane();
                sidebar.setTabPlacement(JTabbedPane.LEFT);
                sidebar.addTab("Pomodoro", new TestPane());
                sidebar.addTab("ShortBreak", new ShortBreak());
                sidebar.addTab("LongBreak", new LongBreak());
                frame.add(sidebar);
                frame.setVisible(true);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        });
    }

    public class TestPane extends JPanel {

        private LocalDateTime startTime;
        private JLabel label;
        private Timer timer;

        private Duration duration  = Duration.ofMinutes(25);

        public TestPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(100, 100, 100, 100);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            label = new JLabel("00h : 25m : 00s");
            add(label, gbc);
            JButton btn = new JButton("Start");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timer.isRunning()) {
                        timer.stop();
                        startTime = null;
                        btn.setText("Start");
                    } else {
                        startTime = LocalDateTime.now();
                        timer.start();
                        btn.setText("Stop");
                    }
                }
            });
            add(btn, gbc);

            timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LocalDateTime now = LocalDateTime.now();
                    Duration runningTime = Duration.between(startTime, now);
                    Duration timeLeft = duration.minus(runningTime);
                    if (timeLeft.isZero() || timeLeft.isNegative()) {
                        timeLeft = Duration.ZERO;
                        btn.doClick(); // Cheat
                    }

                    label.setText(format(timeLeft));
                }
            });
        }

        protected String format(Duration duration) {
            long hours = duration.toHours();
            long mins = duration.minusHours(hours).toMinutes();
            long seconds = duration.minusMinutes(mins).toMillis() / 1000;
            return String.format("%02dh : %02dm : %02ds", hours, mins, seconds);
        }
    }
}

