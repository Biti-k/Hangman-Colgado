/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colgado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Baiti
 */
public class VentanaJuego extends JFrame implements ActionListener{
    ArrayList<ImageIcon> imagenes;
    JRadioButton easyMode, normalMode, hardMode;
    ButtonGroup grupoBotones;
    JLabel monigoteLabel, palabraLabel;
    JButton iniciarPartida;
    JTextField fieldLetras;
    JPanel letrasIntroducidasPanel;
    String incognito;
    String palabraEscogida;
    String palabrasFaciles[] = {"patata", "hola", "adios", "ave", "hilo", "halo", "raro", "malo", "pavo", "saco", "rato", "pato", "pata", "coco", "coca", "juego", "jugo"};
    String palabrasMedio[] = {"estupendo", "maravilloso", "relampago", "urinario", "esternon", "maquina", "arepa", "burrito", "manzana", "kilogramo", "holograma"};
    String palabrasDificiles[] = {"esternocleidomastoideo", "otorrinolaringólogo", "supercalifragilisticoexpialidoso", "desoxirribonucleico", "paralelepípedo", "Hipopotomonstrosesquipedaliofobia", "pneumonoultramicroscopicsilicovolcanoconiosis", "electroencefalografista"};
    ArrayList<String> letrasRegistradas;
    int indexImagen;
    public VentanaJuego(){
        letrasRegistradas = new ArrayList<>();
        indexImagen = -1;
        imagenes = new ArrayList<>();
        grupoBotones = new ButtonGroup();
        inicializarImagenes();
        setSize(new Dimension(950,700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        letrasIntroducidasPanel = new JPanel();
        letrasIntroducidasPanel.setPreferredSize(new Dimension(150, 100));
        letrasIntroducidasPanel.setBackground(new Color(218, 247, 166));
        letrasIntroducidasPanel.setLayout(new GridLayout(0, 1));


        add(letrasIntroducidasPanel, BorderLayout.WEST);
        
        JPanel panelJuego = new JPanel();
        panelJuego.setLayout(new BorderLayout());
        JPanel panelJuegoArriba = new JPanel();
        panelJuegoArriba.setLayout(new FlowLayout(FlowLayout.LEADING));
        JLabel labelDificultad = new JLabel("Escoge la dificultad:");
        labelDificultad.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        labelDificultad.setForeground(Color.BLACK);
        easyMode = new JRadioButton("Modo fácil");
        normalMode = new JRadioButton("Modo normal");
        hardMode = new JRadioButton("Modo difícil");
        easyMode.setBackground(null);
        easyMode.setBorder(null);
        normalMode.setBackground(null);
        normalMode.setBorder(null);
        hardMode.setBackground(null);
        hardMode.setBorder(null);
        grupoBotones.add(easyMode);
        grupoBotones.add(normalMode);
        grupoBotones.add(hardMode);
        panelJuegoArriba.add(labelDificultad);
        panelJuegoArriba.add(easyMode);
        panelJuegoArriba.add(normalMode);
        panelJuegoArriba.add(hardMode);
        panelJuegoArriba.setBackground(new Color(255, 87, 51));
        panelJuegoArriba.setPreferredSize(new Dimension(60, 60));
        panelJuego.add(panelJuegoArriba, BorderLayout.NORTH);
        
        JPanel panelJuegoDraw = new JPanel();
        monigoteLabel = new JLabel();
        monigoteLabel.setIcon(imagenes.get(9));
        panelJuegoDraw.add(monigoteLabel);
        panelJuegoDraw.setBackground(new Color(182, 121, 121));
        panelJuego.add(panelJuegoDraw, BorderLayout.CENTER);
        
        JPanel panelJuegoAbajo = new JPanel();
        panelJuegoAbajo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelJuegoAbajo.setBackground(new Color(255, 87, 51));
        panelJuegoAbajo.setPreferredSize(new Dimension(100, 150));
        palabraLabel = new JLabel("");
        palabraLabel.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        palabraLabel.setForeground(Color.BLACK);
        fieldLetras = new JTextField();
        fieldLetras.setPreferredSize(new Dimension(200, 30));
        fieldLetras.setFont(new Font("MONOSPACED", Font.BOLD, 25));
        fieldLetras.addActionListener(this);
        iniciarPartida = new JButton("Iniciar partida/Reiniciar");
        iniciarPartida.setPreferredSize(new Dimension(200, 30));
        iniciarPartida.addActionListener(this);
        panelJuegoAbajo.add(palabraLabel);
        panelJuegoAbajo.add(new Sep(0, 1000));
        panelJuegoAbajo.add(fieldLetras);
        panelJuegoAbajo.add(new Sep(5, 1000));
        panelJuegoAbajo.add(iniciarPartida);
        panelJuego.add(panelJuegoAbajo, BorderLayout.SOUTH);
        add(panelJuego);
        setResizable(false);
        setVisible(true);
    }
    
    public void inicializarImagenes(){
        for(int i = 1; i <= 10; i++ ){
            imagenes.add(new ImageIcon("./recursos/" + i + ".png"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == iniciarPartida){
            palabraRandom();
        }
        
        if(e.getSource() == fieldLetras){
            String letrasIntroducidas = fieldLetras.getText();
            letrasIntroducidas = letrasIntroducidas.toLowerCase();
            char letraIntroducida = letrasIntroducidas.charAt(0);
            if(letrasIntroducidas.length() <= 1){
                if(palabraEscogida.matches(".*" + letrasIntroducidas + "+.*|" + letrasIntroducidas + "+.*|.*" + letrasIntroducidas + "+")){
                    String pAux = palabraLabel.getText();
                    for(int i = 0; i < palabraEscogida.length(); i++){
                        if(palabraEscogida.charAt(i) == letraIntroducida){
                            pAux = pAux.substring(0, i) + letraIntroducida + pAux.substring(i + 1, pAux.length());
                        }
                    }
                    palabraLabel.setText(pAux);
                    if(palabraLabel.getText().equals(palabraEscogida)){
                        JOptionPane.showMessageDialog(null, "Has ganado!");
                        palabraRandom();
                    }
                    if(letrasRegistradas.indexOf(String.valueOf(letraIntroducida)) == -1){
                         letrasRegistradas.add(String.valueOf(letraIntroducida));
                           JButton b = new JButton(letrasIntroducidas);
                           b.setBackground(Color.white);
                           b.setBorder(BorderFactory.createLineBorder(Color.black));
                           b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
                           b.setForeground(Color.GREEN);
                           letrasIntroducidasPanel.add(b);
                    }
                }else{
                    if(letrasRegistradas.indexOf(String.valueOf(letraIntroducida)) == -1){
                        JButton b = new JButton(letrasIntroducidas);
                        b.setBackground(Color.white);
                        b.setBorder(BorderFactory.createLineBorder(Color.black));
                        b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
                        b.setForeground(Color.RED);
                        letrasIntroducidasPanel.add(b);
                        letrasRegistradas.add(String.valueOf(letraIntroducida));
                    }
                    indexImagen++;
                    if(indexImagen == 9){
                        monigoteLabel.setIcon(imagenes.get(indexImagen));
                        JOptionPane.showMessageDialog(null, "Has perdido, la palabra era: " + palabraEscogida);
                        indexImagen = 0;
                        monigoteLabel.setIcon(imagenes.get(indexImagen));
                        palabraRandom();
                    }else if(indexImagen < 9){
                        monigoteLabel.setIcon(imagenes.get(indexImagen));
                    }else{
                        JOptionPane.showMessageDialog(null, "Escoge una dificultad y reinicia la partida :)");
                    }
                }
                fieldLetras.setText("");
            }else{
                JOptionPane.showMessageDialog(null, "Función no soportada aún, por favor escriba letra a letra.");
                fieldLetras.setText("");
            }
            letrasIntroducidasPanel.validate();
            letrasIntroducidasPanel.repaint();
        }
    }
    
    public void palabraRandom(){
        indexImagen = -1;
        letrasIntroducidasPanel.removeAll();
        letrasIntroducidasPanel.validate();
        letrasIntroducidasPanel.repaint();
        monigoteLabel.setIcon(null);
        if(easyMode.isSelected()){
            Random r = new Random();
            int opcionRandom = r.nextInt(palabrasFaciles.length);
            palabraEscogida = palabrasFaciles[opcionRandom];
            incognito = "";
            for(int i = 0; i < palabraEscogida.length(); i++){
                incognito += "-";
            }
            palabraLabel.setText(incognito);
        }else if(normalMode.isSelected()){
            Random r = new Random();
            int opcionRandom = r.nextInt(palabrasMedio.length);
            palabraEscogida = palabrasMedio[opcionRandom];
            incognito = "";
            for(int i = 0; i < palabraEscogida.length(); i++){
                incognito += "-";
            }
            palabraLabel.setText(incognito);
        }else if(hardMode.isSelected()){
            Random r = new Random();
            int opcionRandom = r.nextInt(palabrasDificiles.length);
            palabraEscogida = palabrasDificiles[opcionRandom];
            incognito = "";
            for(int i = 0; i < palabraEscogida.length(); i++){
                incognito += "-";
            }
            palabraLabel.setText(incognito);
        }
    }
}
