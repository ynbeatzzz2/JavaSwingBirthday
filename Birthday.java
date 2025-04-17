import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Birthday extends JFrame {
    private JLabel countdownLabel;
    private Timer countdownTimer;
    private Timer colorTimer;
    private int secondsLeft = 5;
    private JTextArea cake;
    private Color[] techColors = {
            Color.CYAN, Color.MAGENTA, Color.GREEN, Color.ORANGE, Color.PINK, Color.YELLOW
    };
    private int colorIndex = 0;
	
    public Birthday() {
        setTitle("🎉 Birthday Countdown 🎉");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
		
        countdownLabel = new JLabel("Countdown: " + secondsLeft, SwingConstants.CENTER);
        countdownLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        countdownLabel.setForeground(Color.MAGENTA);
        add(countdownLabel, BorderLayout.CENTER);
		
        startCountdown();
    }
	
    private void startCountdown() {
        countdownTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondsLeft--;
                countdownLabel.setText("Countdown: " + secondsLeft);

                if (secondsLeft <= 0) {
                    countdownTimer.stop();
                    showGlowingCake();
                }
            }
        });
        countdownTimer.start();
    }
	
    private void showGlowingCake() {
        getContentPane().removeAll();
        getContentPane().setBackground(Color.BLACK);
        repaint();
        revalidate();
		
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
		
        JLabel message = new JLabel("🎂 Happy Birthday, Phillip! 🎂");
        message.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        message.setForeground(Color.WHITE);
        centerPanel.add(message, gbc);
		
        cake = new JTextArea(
            "\n     ,   ,   ,\n" +
            "     |\\\\_|\\\\_|\\\\_\n" +
            "     |=|=|=|=|=|=|\n" +
            "     | Happy     |\n" +
            "     | Birthday! |\n" +
            "     |    🎂🎉    |\n" +
            "     '-----------'\n"
        );
        cake.setFont(new Font("Monospaced", Font.BOLD, 18));
        cake.setEditable(false);
        cake.setOpaque(false);
        cake.setForeground(techColors[colorIndex]);
        cake.setHighlighter(null);
        cake.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        centerPanel.add(cake, gbc);
        add(centerPanel, BorderLayout.CENTER);
		
        startGlowingEffect();
        revalidate();
        repaint();
    }
	
    private void startGlowingEffect() {
        colorTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorIndex = (colorIndex + 1) % techColors.length;
                cake.setForeground(techColors[colorIndex]);
            }
        });
        colorTimer.start();
    }
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Birthday app = new Birthday();
            app.setVisible(true);
        });
    }
}