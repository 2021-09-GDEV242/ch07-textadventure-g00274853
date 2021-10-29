
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;
    private int weight;
    private String name;
    private boolean canFit;

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemName, String itemDescription, int itemWeight, boolean fit)
    {
        // initialise instance variables
        description = itemDescription;
        weight = itemWeight;
        name = itemName;
        canFit = fit;
    }
    
    /**
     * Return whether player can take the item.
     * @return boolean
     */
    public boolean canTake()
    {
        return canFit;
    }
    
    /**
     * Return the name of the current item.
     * @return name of the item.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Return the description of the current item.
     * @return description of the item
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return the weight of the current item.
     * @return weight of the item
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Return a string with all the details of the item.
     * @return details about the item
     */
    public String getItemDetails()
    {
        String combine = "A " + description + " that Weight " + weight + " spoops." + 
        "  (Item name: " + name + ")";
        return combine;
    }
    
    
}
