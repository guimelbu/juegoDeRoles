package Forms;

import javax.swing.*;

public class Skeleton {
    static ImageIcon iconSkeleton;

    protected static ImageIcon skeletonUp() {
        iconSkeleton = new ImageIcon("src/Images/skeleton/skeleton_up.gif");

        return iconSkeleton;
    }
    protected static ImageIcon skeletonDown() {
        iconSkeleton = new ImageIcon("src/Images/skeleton/skeleton_down.gif");

        return iconSkeleton;
    }
    protected static ImageIcon skeletonRight() {
        iconSkeleton = new ImageIcon("src/Images/skeleton/skeleton_right.gif");

        return iconSkeleton;
    }
    protected static ImageIcon skeletonLeft() {
        iconSkeleton = new ImageIcon("src/Images/skeleton/skeleton_left.gif");

        return iconSkeleton;
    }

}
