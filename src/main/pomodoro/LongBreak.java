package pomodoro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;

public class LongBreak extends JPanel {

    private LocalDateTime startTime;
    private JLabel label;
    private Timer timer;

    private Duration duration = Duration.ofMinutes(15);

    public LongBreak() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(100, 100, 100, 100);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        label = new JLabel("00h : 15m : 00s");
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






