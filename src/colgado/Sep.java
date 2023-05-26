/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colgado;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Baiti
 */
public class Sep extends JLabel{
    int height, width;
    public Sep(int height, int width){
        setPreferredSize(new Dimension(width, height));
    }
}
