/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author JRS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculadora extends JFrame implements ActionListener, KeyListener {

    private JTextField txtResultado;
    private JLabel lblCien;
    private double memoria;
    private char operacionPendiente;
    private boolean nuevaOperacion;

    public Calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        txtResultado = new JTextField("0", 20);
        txtResultado.setHorizontalAlignment(JTextField.CENTER);
        txtResultado.setEditable(false);
        add(txtResultado, BorderLayout.NORTH);
        
        lblCien = new JLabel("Me merezco un 100 profe");
        lblCien.setHorizontalAlignment(JTextField.CENTER);
        add(lblCien, BorderLayout.SOUTH);
        
        JPanel panelBotones = new JPanel(new GridLayout(5, 4));

        String[] botonText = {
            "1", "2", "3", "-", "M-",
            "4", "5", "6", "+", "CE",
            "7", "8", "9", "C", "=",
            "0", ".", "/", "*", "MOD",
            "MC", "MR", "MS", "M+", ":)"
        };

        for (String text : botonText) {
            JButton boton = new JButton(text);
            boton.setFont(new java.awt.Font("Arial", 3, 18));
            boton.addActionListener(this);
            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);

        memoria = 0;
        operacionPendiente = ' ';
        nuevaOperacion = true;
        txtResultado.addKeyListener(this);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String respuesta = e.getActionCommand();

        switch (respuesta) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                if (nuevaOperacion) {
                    txtResultado.setText(respuesta);
                    nuevaOperacion = false;
                } else {
                    txtResultado.setText(txtResultado.getText() + respuesta);
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                calcularOperacion();
                operacionPendiente = respuesta.charAt(0);
                memoria = Double.parseDouble(txtResultado.getText());
                nuevaOperacion = true;
                break;
            case "=":
                calcularOperacion();
                operacionPendiente = ' ';
                nuevaOperacion = true;
                break;
            case "C":
                txtResultado.setText("0");
                nuevaOperacion = true;
                operacionPendiente = ' ';
                break;
            case "CE":
                txtResultado.setText("0");
                nuevaOperacion = true;
                break;
            case "MC":
                memoria = 0;
                break;
            case "MR":
                txtResultado.setText(Double.toString(memoria));
                break;
            case "MS":
                memoria = Double.parseDouble(txtResultado.getText());
                break;
            case "M+":
                memoria += Double.parseDouble(txtResultado.getText());
                break;
            case "M-":
                memoria -= Double.parseDouble(txtResultado.getText());
                break;
            case "MOD":
                calcularOperacion();
                txtResultado.setText(Double.toString(memoria));
                operacionPendiente = ' ';
                nuevaOperacion = true;
                break;
        }
    }

    private void calcularOperacion() {
        double pantallaActual = Double.parseDouble(txtResultado.getText());

        switch (operacionPendiente) {
            case '+':
                memoria += pantallaActual;
                break;
            case '-':
                memoria -= pantallaActual;
                break;
            case '*':
                memoria *= pantallaActual;
                break;
            case '/':
                if (pantallaActual != 0) {
                    memoria /= pantallaActual;
                } else {
                    txtResultado.setText("Error");
                }
                break;
        }
        txtResultado.setText(Double.toString(memoria));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar) || keyChar == '.') {
            actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, String.valueOf(keyChar)));
        } else if (keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/') {
            actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, String.valueOf(keyChar)));
        } else if (keyChar == '=' || keyChar == '\n') {
            actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "="));
        } else if (keyChar == '\b') { // Backspace
            actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "CE"));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) != 0) {
            if (keyCode == KeyEvent.VK_L) {
                actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "MC"));
            } else if (keyCode == KeyEvent.VK_R) {
                actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "MR"));
            } else if (keyCode == KeyEvent.VK_M) {
                actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "MS"));
            } else if (keyCode == KeyEvent.VK_P) {
                actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "M+"));
            } else if (keyCode == KeyEvent.VK_Q) {
                actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "M-"));
            }
        } else if (keyCode == KeyEvent.VK_DELETE) {
            actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "CE"));
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "C"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
