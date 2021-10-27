
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemSize;

    /**
     * Constructor for objects of class Item
     */
    public Item()
    {
        // initialise instance variables
        itemDescription = "";
        itemSize = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Item(String description, int size)
    {
        // put your code here
        itemDescription = description;
        itemSize = size;
    }
    
    public String getItemDescription()
    {
        String itemString = "Item Description:";
        itemString += this.itemDescription +"\n";
        itemString += "Item Size:" + this.itemSize;
        return itemString;
    }
    
    
}
