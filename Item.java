
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String longDescription;
    private boolean pickUpable;
    
    public Item (String name, String longDescription, boolean isPickUpable)
    {
        this.name = name;
        this.longDescription = longDescription;
        this.pickUpable = isPickUpable;
    }
    public String getLongDescription()
    {
        return longDescription;
    }
    public String getName()
    {
        return name;
    }
    public boolean getPickUpable()
    {
        return pickUpable;
    }
}
