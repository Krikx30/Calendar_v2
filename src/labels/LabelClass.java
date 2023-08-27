package labels;
import mainPackage.Application;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class LabelClass extends JLabel {

    protected String dataSource = "src/source/label_style_sheet";
    protected Application app;
    protected String ID;
    protected String styleID;
    protected String comment;

    protected static final String UNSET_VALUE = "-";
    protected static final String VALUE_NOT_KNOWN = "NA";

    //TODO: put comment variable into Icon Label! (interface)
    public LabelClass(Application app, JPanel parent_to_add, String ID, String styleID, String text, ArrayList<LabelClass> allLabel){
        this.app = app;
        this.ID = ID;
        this.styleID = styleID;
        setText(text);
        loadProperties();

        allLabel.add(this);
        parent_to_add.add(this);
    }
    private void loadProperties(){
        File inFile = new File(dataSource);

        try(Scanner sc = new Scanner(inFile)){

            while (sc.hasNextLine()){
                String currentLine = sc.nextLine();
                String[] lineTokens = currentLine.split(";");

                if(lineTokens.length == 5){
                    String styleID = lineTokens[0];
                    String fontStyle = lineTokens[1];
                    String fontSize = lineTokens[2];
                    String fontColor = lineTokens[3];
                    String alignment = lineTokens[4];

                    if (this.styleID.equals(styleID)){
                        this.setFont(new Font(fontStyle, Font.PLAIN, Integer.parseInt(fontSize)));
                        this.setForeground(Color.decode(fontColor));
                        this.setHorizontalAlignment(Integer.parseInt(alignment));
                        break;
                    }

                }else{
                    // load the default properties, if there is no style for this label
                    this.setFont(new Font("Helvetica", Font.PLAIN, 10));
                    this.setForeground(Color.black);
                    this.setHorizontalAlignment(0);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }

    }
    public String getID(){
        return this.ID;
    }
    public String getStyleID(){
        return this.styleID;
    }
    public void setHorAlignment(int alignmentNumber){
        this.setHorizontalAlignment(alignmentNumber);
    }
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setText(){

    }
}
