package containers;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The class of the actual month's days, inside the middleContainer
 */
public class DaysClass extends JPanel {

    private final String styleFilePath = "src/source/container_style_sheet";
    private String ID;
    private String styleID;


    public DaysClass(ContainerClass parent_to_add, String ID, String styleID){
        this.ID = ID;
        this.styleID = styleID;
        this.setPreferredSize(new Dimension(parent_to_add.getWidth() / 8, parent_to_add.getHeight() / 4));
        setLayout(new GridLayout(2, 2));
        initContainerColor();
        parent_to_add.add(this);


    }
    public void initContainerColor(){
        try {
            File inFile = new File(styleFilePath);
            Scanner sc = new Scanner(inFile);

            while (sc.hasNextLine()){

                String line = sc.nextLine();

                if(line.contains(";")){ // because the source file contains comments

                    String[] splitLine = line.split(";");
                    String ID = splitLine[0];
                    String color = splitLine[1];

                    if(ID.equals(this.styleID)){
                        this.setBackground(Color.decode(color));
                        break;
                    }
                }


            }
            sc.close();


        }catch (IOException e){
            System.out.println(e);
        }
    }
    public String getID(){
        return ID;
    }
}
