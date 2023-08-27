package containers;

import java.awt.*;

public class TopRightContainer extends ContainerClass{
    public TopRightContainer(Container parent_to_add, String ID, int width, int height){
        super(parent_to_add, ID, width, height);
    }

    @Override
    protected void setContainerLayout() {
        super.setContainerLayout();
        GridLayout gl = new GridLayout(3,4);
        setLayout(gl);
    }
}
