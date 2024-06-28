import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class PathfindingVisualizer extends JFrame implements ActionListener,ItemListener{
    public static void main(String[] args) {
        new PathfindingVisualizer();
    }

    private int cells=20;

    private final int mapSize=600;
    private int cSize=mapSize/cells;
    private int tool; //stores which tool selected(from checkboxes)

    JPanel panel;
    JButton startButton,resetButton,generateButton,clearButton;
    JComboBox<String> algorithmComboBox,toolComboBox;
    JLabel algL,toolL,gridSlideL,speedSlideL;
    JSlider gridSizeSlider,speedSlider;
    CheckboxGroup Checkgrp;
    Checkbox StartCB,FinishCB,WallCB,EraserCB;
    Map canvas;
    Node map[][];

    public PathfindingVisualizer(){
        
        this.clearMap();

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
        StartCB = new Checkbox("Start",Checkgrp,true);
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


        StartCB.addItemListener(this);
        FinishCB.addItemListener(this);
        WallCB.addItemListener(this);
        EraserCB.addItemListener(this);

        startButton.addActionListener(this);
        resetButton.addActionListener(this);
        clearButton.addActionListener(this);

    
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
    int startx,starty,finishx,finishy;

    public void clearMap(){
        startx=-1;
        starty=-1;
        finishx=-1;
        finishy=-1;
        map=new Node[cells][cells];//cells-length of the map panel
        for(int i=0;i<cells;i++){
            for(int j=0;j<cells;j++){
                map[i][j]=new Node(3, i, j);
            }
        }
    }
    public void resetMap() {	//RESET MAP
		for(int x = 0; x < cells; x++) {
			for(int y = 0; y < cells; y++) {
				Node current = map[x][y];
				if(current.cellType == 4 || current.cellType == 5)	//checked,finalpath
					map[x][y] = new Node(3,x,y);	//back emptynode
			}
		}
		if(startx > -1 && starty > -1) {	//reset strt,finsh
			map[startx][starty] = new Node(0,startx,starty);
			map[startx][starty].hops=0;
		}
		if(finishx > -1 && finishy > -1)
			map[finishx][finishy] = new Node(1,finishx,finishy);
		reset();	
	}
    public void reset() {	//reset
		solving = false;
		length = 0;
		checks = 0;
	}
    private boolean solving;
    private int length,checks;
    public void Update(){
        cSize = mapSize/cells;
		canvas.repaint();
    }

    
    public void actionPerformed(ActionEvent e) {
        //actions


    }
    
    public void itemStateChanged(ItemEvent e) {
        if(StartCB.getState()==true) tool=0;
        else if(FinishCB.getState()==true) tool=1;
        else if(WallCB.getState()==true) tool=2;
        else if(EraserCB.getState()==true) tool=3;
    }
    class Map extends JPanel implements MouseListener,MouseMotionListener{
        public Map(){
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            for(int x=0;x<cells;x++){
                for(int y=0;y<cells;y++){
                    switch (map[x][y].cellType) {
                        case 0:
                            g.setColor(Color.BLUE);
                            break;
                        case 1:
                            g.setColor(Color.RED);
                            break;
                        case 2:
                            g.setColor(Color.BLACK);
                            break;
                        case 3:
                            g.setColor(Color.WHITE);
                            break;
                        case 4:
                            g.setColor(Color.CYAN);
                            break;
                        case 5:
                            g.setColor(Color.YELLOW);
                            break;
                        
                    }
                    g.fillRect(x*cSize, y*cSize, cSize, cSize);
                    g.setColor(Color.BLACK);
                    g.drawRect(x*cSize, y*cSize, cSize, cSize);

                }
            }
    
        }
    
    
        public void mousePressed(MouseEvent e) {
            resetMap();
            try{
                int x= e.getX()/cSize;
                int y= e.getY()/cSize;
                Node current = map[x][y];
                switch (tool) {
                    case 0:
                        if(current.cellType!=2){
                            if(startx>-1 && starty>-1){
                                map[startx][starty].cellType=3;
                                map[startx][starty].hops=-1;

                            }
                            current.hops=0;
                            startx = x;
                            starty = y;
                            current.cellType=0;
                        }
                        break;

                    case 1:
                        if (current.cellType!=2) {
                            if(finishx>-1 && finishy>-1){
                                map[finishx][finishy].cellType=3;
                                
                            }
                            finishx=x;
                            finishy=y;
                            current.cellType=1;
                        }

                        break;
                
                    default:
                        if (current.cellType!=0 && current.cellType!=1) {
                            current.cellType=tool;
                            
                        }
                        break;
                }
                Update();

            }catch(Exception exc){}

        }
    
        public void mouseDragged(MouseEvent e) {
            
        }
    
    
        public void mouseMoved(MouseEvent e) {
           
        }
    
    
        public void mouseClicked(MouseEvent e) {
            
        }
    
    
        
    
        public void mouseReleased(MouseEvent e) {
           
        }
    
    
        public void mouseEntered(MouseEvent e) {
            
        }
    
    
        public void mouseExited(MouseEvent e) {
            
        }
    }
    class Node{
        private int cellType=0,hops,x,y,lastX,lastY;
		
        public Node(int cellType, int x, int y){
            this.cellType = cellType; // 0-start 1-finish 2-wall 3-empty 4-checked 5-finalpath
            this.x=x;
            this.y=y;
            hops=-1;
        }
    }
    
    

}
