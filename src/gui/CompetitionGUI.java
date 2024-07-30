package gui;

import game.competition.Competition;
import game.competition.SkiCompetition;
import game.arena.IArena;
import game.arena.WinterArena;
import game.enums.WeatherCondition;
import game.enums.SnowSurface;
import model.CompetitorImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompetitionGUI extends JFrame {
    private Competition competition;
    private JPanel arenaPanel;
    private JTextArea infoTextArea;

    public CompetitionGUI(Competition competition) {
        this.competition = competition;
        setTitle("Competition Simulator");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());

        // Arena Panel
        arenaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintArena(g);
            }
        };
        arenaPanel.setPreferredSize(new Dimension(1000, 700));
        add(arenaPanel, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1));

        JButton buildArenaButton = new JButton("Build Arena");
        buildArenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Build arena logic
                // Example: Create a new WinterArena with default values
                IArena arena = new WinterArena(800, SnowSurface.POWDER, WeatherCondition.SUNNY);
                competition = new SkiCompetition(arena, 10);
            }
        });
        controlPanel.add(buildArenaButton);

        JButton startCompetitionButton = new JButton("Start Competition");
        startCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                competition.startCompetition();
                // Start a timer to repaint the arena every 30 milliseconds
                Timer timer = new Timer(30, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        arenaPanel.repaint();
                    }
                });
                timer.start();
            }
        });
        controlPanel.add(startCompetitionButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Info Panel
        infoTextArea = new JTextArea();
        add(new JScrollPane(infoTextArea), BorderLayout.EAST);
    }

    private void paintArena(Graphics g) {
        for (CompetitorImpl competitor : competition.getFinishedCompetitors()) {
            int yPosition = (int) competitor.getCurrentPosition();
            g.fillOval(10, yPosition, 20, 20); // Adjust the x and y position based on the competitor's position
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IArena arena = new WinterArena(700, SnowSurface.PACKED, WeatherCondition.SUNNY);
                Competition competition = new SkiCompetition(arena, 10);
                new CompetitionGUI(competition).setVisible(true);
            }
        });
    }
}
