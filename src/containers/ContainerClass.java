package containers;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ContainerClass extends JPanel {
    public String ID;
    private Color containerColor;
    public ContainerClass(Container parent_to_add, String ID, int width, int height){

        this.ID = ID;
        setSize(new Dimension(width,height));
        setPreferredSize(new Dimension(width,height));

        initContainerColor();
        setContainerLayout();

        setVisible(true);
        parent_to_add.add(this);
    }
    private void initContainerColor(){
        try{
            File infile = new File("src/source/container_style_sheet");
            Scanner sc = new Scanner(infile);

            while (sc.hasNextLine()){

                String line = sc.nextLine();

                if(line.contains(";")){ // because there are comments in the file source

                    String[] splitLine = line.split(";");
                    String ID = splitLine[0];

                    if (ID.equals(this.ID)){

                        if (splitLine.length == 2){
                            containerColor = Color.decode(splitLine[1]);
                            break;
                        }else{
                            containerColor = Color.WHITE;
                        }
                    }

                }
            }

            sc.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }
    protected void setContainerLayout(){

    }
     //Also very important, to call the paintComponent methods, not the paint methods.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(containerColor);
        g2d.fillRect(0,0,this.getWidth(), this.getHeight());
    }

}
