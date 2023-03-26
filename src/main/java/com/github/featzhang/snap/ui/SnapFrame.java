package com.github.featzhang.snap.ui;

import com.github.featzhang.snap.enums.NapState;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.featzhang.snap.utils.FlakeLabel.APP_NAME;
import static com.github.featzhang.snap.utils.FlakeLabel.INIT_STATE;
import static com.github.featzhang.snap.utils.FlakeLabel.RESTING;
import static com.github.featzhang.snap.utils.FlakeLabel.START_REST;
import static com.github.featzhang.snap.utils.FlakeLabel.START_WORK;
import static com.github.featzhang.snap.utils.FlakeLabel.STOP;
import static com.github.featzhang.snap.utils.FlakeLabel.THINGS_DEFAULT;
import static com.github.featzhang.snap.utils.FlakeLabel.TIMER_DEFAULT;
import static com.github.featzhang.snap.utils.FlakeLabel.WAITING;
import static com.github.featzhang.snap.utils.FlakeLabel.WORKING;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * all time in milliseconds.
 *
 * @author featzhang
 */
public class SnapFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel timerLabel;
    private JLabel thingLabel;

    private JButton startButton;
    private JButton stopButton;
    private JButton restButton;
    private JButton interruptButton;

    private long startTime;
    private long endTime;
    private long leastTime;
    private Timer timer;
    private NapState status = NapState.Initial;
    private JProgressBar progressBar;
    private JTable logTable;

    public SnapFrame() throws HeadlessException {
        setTitle(APP_NAME.value());
        initComponents();
        loadAction();
        initState();
    }

    private long getWorkTimeInterval() {
        return 25 * 60 * 1000;
    }

    private void initState() {
//		create timer
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (status == NapState.RESTING || status == NapState.WORKING) {
                    leastTime += 1000;
                    long time = endTime - leastTime;
                    if (time < 0) {
                        onEndOfTime();
                    } else {
                        timerLabel.setText(formatDuration(time));
                        int process = (int) ((double) (leastTime - startTime) * 100 / getWorkTimeInterval());
                        progressBar.setValue(process);
                    }
                }
            }
        }, 1000, 1000);
//
        toState(NapState.Initial);
    }

    private void onEndOfTime() {

    }

    private void loadAction() {
        thingLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onThingLabelClicked();
            }
        });

        startButton.addActionListener(e -> onStartButtonAction());
        stopButton.addActionListener(e -> onStopButtonAction());
        interruptButton.addActionListener(e -> onInterruptButtonAction());
        restButton.addActionListener(e -> onWeakButtonAction());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                status = NapState.Initial;
                timer.cancel();
            }
        });
    }

    protected void onInterruptButtonAction() {

    }

    protected void onWeakButtonAction() {

    }

    protected void onStopButtonAction() {
//		
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
//
        endTime = System.currentTimeMillis();

    }

    protected void onStartButtonAction() {
//
        if (status == NapState.Initial) {
            toState(NapState.WORKING);
        } else if (status == NapState.WORKING) {
            saveNewLog();
            toState(NapState.WORKING);
        }
//
        startTime = System.currentTimeMillis();
        endTime = startTime + getWorkTimeInterval();
        leastTime = startTime;
    }

    private void saveNewLog() {

    }

    protected void onThingLabelClicked() {

    }

    private void initComponents() {
//
        startButton = new JButton(START_WORK.value());

        stopButton = new JButton(STOP.value());
        interruptButton = new JButton("interrupt");
        restButton = new JButton("weak");
        progressBar = new JProgressBar();
        progressBar.setBackground(new Color(136, 129, 45));
        progressBar.setValue(50);
        progressBar.setIndeterminate(true);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        buttonPanel.add(interruptButton);
        buttonPanel.add(restButton);
        buttonPanel.add(stopButton);
//
        Container parent = getContentPane();
//        panel.add(timerLabel, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//        panel.add(panel_1, BorderLayout.WEST);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0};
        gbl_panel_1.rowHeights = new int[]{96, 0, 0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE, 1.0};

        panel_1.setLayout(gbl_panel_1);
        // parent.add(panel);
        timerLabel = new JLabel(TIMER_DEFAULT.value());
        timerLabel.setForeground(new Color(255, 25, 20));
        timerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 48));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_timerLabel = new GridBagConstraints();
        gbc_timerLabel.gridwidth = 3;
        gbc_timerLabel.insets = new Insets(0, 0, 5, 0);
        gbc_timerLabel.gridx = 0;
        gbc_timerLabel.gridy = 0;
        panel_1.add(timerLabel, gbc_timerLabel);

        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(210, 210, 210));
        GridBagConstraints gbc_progressBar = new GridBagConstraints();
        gbc_progressBar.fill = GridBagConstraints.BOTH;
        gbc_progressBar.gridwidth = 3;
        gbc_progressBar.insets = new Insets(0, 0, 5, 0);
        gbc_progressBar.gridx = 0;
        gbc_progressBar.gridy = 1;
        panel_1.add(progressBar, gbc_progressBar);
        thingLabel = new JLabel(THINGS_DEFAULT.value());

        thingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thingLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        GridBagConstraints gbc_thingLabel = new GridBagConstraints();
        gbc_thingLabel.gridwidth = 3;
        gbc_thingLabel.insets = new Insets(0, 0, 5, 0);
        gbc_thingLabel.gridx = 0;
        gbc_thingLabel.gridy = 2;
        panel_1.add(thingLabel, gbc_thingLabel);
        GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
        gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
        gbc_buttonPanel.gridwidth = 3;
        gbc_buttonPanel.gridx = 0;
        gbc_buttonPanel.gridy = 3;
        panel_1.add(buttonPanel, gbc_buttonPanel);

        setContentPane(panel_1);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "History",
                TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 3;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 4;
        panel_1.add(panel, gbc_panel);
        panel.setLayout(new BorderLayout(0, 0));

        logTable = new JTable();

        panel.add(logTable, BorderLayout.CENTER);
    }

    private String formatDuration(long duration) {
        long du = duration / 1000;
        long sec = du % 60;
        long min = du / 60 % 60;
        return String.format("%02d:%02d", min, sec);
    }

    private void toState(NapState state) {
        this.status = state;
        switch (state) {
            case Initial:
//
                startButton.setText(START_WORK.value());
                startButton.setEnabled(true);
                interruptButton.setEnabled(false);
                restButton.setEnabled(true);
                stopButton.setEnabled(false);
//
                thingLabel.setText(INIT_STATE.value());
                break;
            case WORKING:
                startButton.setText(START_REST.value());
                startButton.setEnabled(false);
                interruptButton.setEnabled(true);
                restButton.setEnabled(true);
                stopButton.setEnabled(true);
//
                thingLabel.setText(WORKING.value());
                break;
            case WAIT:
                startButton.setEnabled(false);
                interruptButton.setEnabled(false);
                restButton.setEnabled(false);
                stopButton.setEnabled(false);
//
                thingLabel.setText(WAITING.value());
                break;
            case RESTING:
                startButton.setText(START_WORK.value());
                startButton.setEnabled(true);
                interruptButton.setEnabled(true);
                restButton.setEnabled(false);
                stopButton.setEnabled(false);
//
                thingLabel.setText(RESTING.value());
                break;
        }
    }

}
