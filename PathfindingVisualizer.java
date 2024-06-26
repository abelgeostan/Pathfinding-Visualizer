import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class PathfindingVisualizer extends JFrame implements ActionListener{
    public static void main(String[] args) {
        new PathfindingVisualizer();
    }

    private int cells=20;

    private final int mapSize=600;

    JPanel panel;
    JButton startButton,resetButton,generateButton,clearButton;
    JComboBox<String> algorithmComboBox,toolComboBox;
    JLabel algL,toolL,gridSlideL,speedSlideL;
    JSlider gridSizeSlider,speedSlider;
    CheckboxGroup Checkgrp;
    Checkbox StartCB,FinishCB,WallCB,EraserCB;
    Map canvas;

    public PathfindingVisualizer(){

        this.initial();
        

    }
    public void initial(){
        setSize(850,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Path Finder");
        setLayout(null);

        panel=new JPanel();
        //panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Controls"));
        panel.setBounds(0,10,200,600);
        panel.setLayout(null);
        add(panel);
        panel.setBackground(Color.LIGHT_GRAY);
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        startButton = new JButton("Start Search");
        startButton.setBounds(30,50,120,40);
        resetButton = new JButton("Reset");
        resetButton.setBounds(30,100,120,40);
        //generateButton = new JButton("Generate Map");
        clearButton = new JButton("Clear Map");
        clearButton.setBounds(30,150,120,40);
    
        String[] algorithms = {"Dijkstra", "A*"};
        algL=new JLabel("Algorithms");
        algL.setBounds(30,200,120,25);
        algorithmComboBox = new JComboBox<>(algorithms);
        algorithmComboBox.setBounds(30,225,120,30);


        Checkgrp = new CheckboxGroup();
        StartCB = new Checkbox("Start",Checkgrp,false);
        FinishCB = new Checkbox("Finish",Checkgrp,false);
        WallCB = new Checkbox("Wall",Checkgrp,false);
        EraserCB = new Checkbox("Eraser",Checkgrp,false);
        toolL = new JLabel("Tools");                                      
        toolL.setBounds(30,275,120,25);
        StartCB.setBounds(30,300,50,25);
        FinishCB.setBounds(100,300,50,25);
        WallCB.setBounds(30,325,50,25);
        EraserCB.setBounds(100,325,50,25);
        

    
        // String[] tools = {"Start", "Finish", "Wall", "Eraser"};
        
        // toolComboBox = new JComboBox<>(tools);                       VENDA ig
        // toolComboBox.setBounds(30,300,120,30);
        
        gridSlideL =new JLabel("Grid:");
        gridSlideL.setBounds(10,365,40,25);
        gridSizeSlider = new JSlider(10, 50, 20);
        gridSizeSlider.setBounds(55,365,100,25);
        gridSizeSlider.setBackground(Color.LIGHT_GRAY);
        speedSlideL= new JLabel("Speed:");
        speedSlideL.setBounds(10,400,40,25);
        speedSlider = new JSlider(0, 100, 50);
        speedSlider.setBounds(55,400,100,25);
        speedSlider.setBackground(Color.LIGHT_GRAY);
        //densitySlider = new JSlider(0, 100, 30);
        canvas= new Map();
        canvas.setBounds(230, 10, mapSize+1, mapSize+1);
		getContentPane().add(canvas);




    
        panel.add(startButton);
        panel.add(resetButton);
        //panel.add(generateButton);   venda ig
        panel.add(clearButton);
        panel.add(algL);
        panel.add(algorithmComboBox);
        panel.add(toolL);
        // panel.add(toolComboBox);
        panel.add(StartCB);
        panel.add(FinishCB);
        panel.add(WallCB);
        panel.add(EraserCB);
        panel.add(gridSlideL);
        panel.add(gridSizeSlider);
        panel.add(speedSlideL);
        panel.add(speedSlider);


        //panel.add(densitySlider);   venda ig



        setVisible(true);

    }

    
    public void actionPerformed(ActionEvent e) {
        //actions
    }
}

class Map extends JPanel implements MouseListener,MouseMotionListener{
    public Map(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }



    public void mouseDragged(MouseEvent e) {
        
    }


    public void mouseMoved(MouseEvent e) {
       
    }


    public void mouseClicked(MouseEvent e) {
        
    }


    public void mousePressed(MouseEvent e) {
        
    }


    public void mouseReleased(MouseEvent e) {
       
    }


    public void mouseEntered(MouseEvent e) {
        
    }


    public void mouseExited(MouseEvent e) {
        
    }
}
