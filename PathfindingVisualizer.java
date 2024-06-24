import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class PathfindingVisualizer extends JFrame implements ActionListener{
    public static void main(String[] args) {
        new PathfindingVisualizer();
    }
    JPanel panel;
    JButton startButton,resetButton,generateButton,clearButton;
    JComboBox<String> algorithmComboBox,toolComboBox;
    JLabel algL,toolL,gridSlideL,speedSlideL;
    JSlider gridSizeSlider,speedSlider;

    public PathfindingVisualizer(){
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
    
        String[] tools = {"Start", "Finish", "Wall", "Eraser"};
        toolL = new JLabel("Tools");
        toolL.setBounds(30,275,120,25);
        toolComboBox = new JComboBox<>(tools);
        toolComboBox.setBounds(30,300,120,30);
        
        gridSlideL =new JLabel("Grid:");
        gridSlideL.setBounds(10,350,40,25);
        gridSizeSlider = new JSlider(10, 50, 20);
        gridSizeSlider.setBounds(55,350,100,25);
        gridSizeSlider.setBackground(Color.LIGHT_GRAY);
        speedSlideL= new JLabel("Speed:");
        speedSlideL.setBounds(10,400,40,25);
        speedSlider = new JSlider(0, 100, 50);
        speedSlider.setBounds(55,400,100,25);
        speedSlider.setBackground(Color.LIGHT_GRAY);
        //densitySlider = new JSlider(0, 100, 30);
    
        panel.add(startButton);
        panel.add(resetButton);
        //panel.add(generateButton);   venda ig
        panel.add(clearButton);
        panel.add(algL);
        panel.add(algorithmComboBox);
        panel.add(toolL);
        panel.add(toolComboBox);
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
