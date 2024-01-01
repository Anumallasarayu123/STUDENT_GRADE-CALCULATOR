import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame implements ActionListener {
    private JLabel[] subjectLabels;
    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public GradeCalculatorGUI() {
        setTitle("Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));
        setResizable(false);

        int numSubjects = 5; // Change this value as needed

        subjectLabels = new JLabel[numSubjects];
        subjectFields = new JTextField[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + ": ");
            subjectFields[i] = new JTextField(5);
            add(subjectLabels[i]);
            add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Increase font size for visibility
        add(gradeLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int numSubjects = subjectFields.length;
            int[] marks = new int[numSubjects];

            for (int i = 0; i < numSubjects; i++) {
                try {
                    marks[i] = Integer.parseInt(subjectFields[i].getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.");
                    return;
                }
            }

            int totalMarks = 0;
            for (int mark : marks) {
                totalMarks += mark;
            }

            double averagePercentage = (double) totalMarks / numSubjects;

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f%%", averagePercentage));

            char grade;
            if (averagePercentage >= 90) {
                grade = 'A';
            } else if (averagePercentage >= 80) {
                grade = 'B';
            } else if (averagePercentage >= 70) {
                grade = 'C';
            } else if (averagePercentage >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            gradeLabel.setText("Grade: " + grade);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeCalculatorGUI());
    }
}
