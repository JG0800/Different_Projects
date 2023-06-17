import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main {

        public static int richtigeZahl = 67;
        public static int count = 0;
        public static JLabel versuche = new JLabel("Versuche: " + count);
        public static JLabel text = new JLabel("gib eine Zahl zwischen 0 und 100 ein.");


        public static void main (String[]args){
            openGUI();
            System.out.println("Es wird eine Ganzzahl gesucht, probiere es doch mal:");
            naechsteRunde();
        }
        ;

        public static void openGUI () {

            JFrame frame = new JFrame("Rate die Zahl.");
            frame.setSize(600, 300);
            frame.setLocation(100, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setDefaultLookAndFeelDecorated(true);

            text.setBounds(90, 50, 600, 30);

            versuche.setBounds(500, 10, 100, 30);

            JTextField textField = new JTextField();
            textField.setBounds(90, 100, 400, 30);

            JButton neuStarten = new JButton("Neu starten");
            neuStarten.setBounds(90,190,400,30);
            neuStarten.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 0;
                    text.setText("gib eine Zahl zwischen 0 und 100 ein.");
                    versuche.setText("Versuche: "+count);
                    textField.setText("");
                }
            });

            JButton button = new JButton("Raten!");
            button.setBounds(90, 150, 400, 30);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count++;
                    String textFromTextField = textField.getText();
                    if (textFromTextField.isEmpty()) {
                        text.setText("Bitte geben Sie eine Zahl ein.");
                        return;
                    }
                    try {
                        int zahlDesGUI = Integer.parseInt(textFromTextField);
                        zahlenRaten(zahlDesGUI);
                    } catch (NumberFormatException ex) {
                        text.setText("Bitte geben Sie eine gültige Zahl ein.");
                        return;
                    }
                }

            });
            frame.add(neuStarten);
            frame.add(versuche);
            frame.add(button);
            frame.add(text);
            frame.add(textField);
            frame.setLayout(null); //dadurch können Koordinaten manuell eingetragen werden
            frame.setVisible(true);
        }

        ;

        public static void naechsteRunde () {
            Scanner zahlenScannen = new Scanner(System.in);
            int eingeleseneZahl = zahlenScannen.nextInt();
            zahlenRaten(eingeleseneZahl); //Variablen übergeben
        }

        ;

        public static void zahlenRaten ( int zahl){

            if (zahl == richtigeZahl) {
                System.out.println("Richtig, du hast " + count + " mal geraten.");
                text.setText("Richtig geraten, mit " + count + " Versuchen.");

            } else {


                if (zahl > richtigeZahl) {
                    System.out.println("Die Zahl ist groeßer als die gesuchte Zahl, versuche es erneut:");
                    System.out.println("Anzahl der Versuche: " + count);
                    text.setText("Die gesuchte Zahl ist kleiner, Versuche es erneut:");
                    versuche.setText("Versuche: " + count);


                    //naechsteRunde();
                } else if (zahl < richtigeZahl) {
                    System.out.println("Die Zahl ist kleiner als die gesuchte Zahl, versuche es erneut");
                    System.out.println("Anzahl der Versuche: " + count);
                    text.setText("Die gesuchte Zahl ist groeßer, Versuche es erneut:");
                    versuche.setText("Versuche: " + count);
                    //naechsteRunde();
                } else {
                    System.out.println("Eingabe ungültig.");
                    text.setText("Die Eingabe muss eine Ganzzahl sein.");
                    versuche.setText("Versuche: " + count);
                }
            }


        }
}

