import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Yixuan Sun
 * @version 2020.10.25
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;
    private boolean open;  
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, boolean isOpen) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<Item>();
        open = isOpen;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Add an item to the room's inventory.
     * @param roomItem  Item object in the room
     */
    public void addItem(Item roomItem)
    {
        items.add(roomItem);
    }

    /**
     * Remove an item from the room's inventory.
     * @param roomItem Item object in the room
     */
    public void removeItem(Item roomItem)
    {
        items.remove(roomItem);
    }
    
    /**
     * See if the room is open/unlocked.
     * @return boolean
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Unlock the room.
     */
    public void unlock() {
        open = true;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public Item getItemFromRoom(String itemName)
    {
        for (Item item : items) {
            if (itemName.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }
    
        
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        String longDescription = "\nYou are " + description + "\n";
           if (items.size() > 0)
           {
                if (items.size() > 1) 
                {
                    longDescription += "\nYou see some items on the ground:";
                } 
                else 
                {
                    longDescription += "\nYou see something on the ground:";
                }
                for (Item item : items) 
                {
                    longDescription += "\n" + item.getItemDetails();
                }
            longDescription += "\n";
        }
         return longDescription + "\n" + getExitString();
    }

}

