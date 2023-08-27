package labels;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class IconLabel extends JLabel {

    private String dataSource = "src/source/label_style_sheet";
    private ArrayList<String> iconList = new ArrayList<>();
    private String ID;
    private String styleID;
    private String comment;

    public IconLabel(JPanel parent_to_add, String ID, String styleID, Dimension iconPreferredSize, ArrayList<IconLabel> allIconLabel){
        this.ID = ID;
        this.styleID = styleID;
        setPreferredSize(iconPreferredSize);

        loadProperties();
        allIconLabel.add(this);
        parent_to_add.add(this);
    }
    private void loadProperties(){
        File inFile = new File(dataSource);

        try(Scanner sc = new Scanner(inFile);){
            while (sc.hasNextLine()){

                String currentLine = sc.nextLine();
                String[] lineTokens = currentLine.split(";");
                String styleID = lineTokens[0];

                if (lineTokens.length >= 2){ //at least 1 icon path is given

                    if (this.styleID.equals(styleID)){
                        String defaultIcon = lineTokens[1];

                        // scale the icon for the appropriate size
                        int scaledImageWidth = this.getPreferredSize().width;
                        int scaledImageHeight = this.getPreferredSize().height;
                        ImageIcon originalImg = new ImageIcon(defaultIcon);
                        Image scaledImage = originalImg.getImage().getScaledInstance(scaledImageWidth, scaledImageHeight, Image.SCALE_SMOOTH);
                        ImageIcon finalImage = new ImageIcon(scaledImage);

                        this.setIcon(finalImage);
                        this.setHorizontalAlignment(0);

                        // put all the icon paths in an array, fo further use. (icon change)
                        for (int i = 1; i < lineTokens.length; i++){
                            iconList.add(lineTokens[i]);
                        }
                        break;
                    }
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public String getID(){
        return ID;
    }
    public String getStyleID(){
        return styleID;
    }
    public String getComment(){
        return comment;
    }

    /**
     *
     * @param newComment The new comment, that will be added to the comment string (append)
     */
    public void addComment(String newComment){
        comment += "\n"+newComment;
    }
}
