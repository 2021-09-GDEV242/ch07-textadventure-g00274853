import java.util.ArrayList;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int maxWeight;
    private int health;

     /**
     * Constructor for objects of class Player
     */
    public Player(int weight)
    {
        // initialise instance variables
        maxWeight = weight;
        health = 10;
        inventory = new ArrayList<Item>();
    }

    /**
     * Set the room the player is currently in.
     * @param room  room player is in
     */
    public void setRoom(Room room)
    {
        currentRoom = room;
    }
    
     public boolean addItem(Item item)
    {
      if (getCurrentSize() + item.getWeight() > maxWeight)
      {
        System.out.println("\nThis item is too big.");
        return false;
      }
      inventory.add(item);
      return true;
    }
    
    private int getCurrentSize()
    {
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += item.getWeight(); 
        }
        return totalWeight;
    }
    
    /**
     * Add health  to a player
     * @param spoops  amount of health that player gains
     */
    public void addHealth(int spoops)
    {
      health += spoops;
      if (health > 10) {
        health = 10;
      }
    }

    /**
     * Remove health from a player. Return true if health gets to 0.
     * @param spoops  amount of health player looses
     */
    public boolean damage(int spoops)
    {
      health -= spoops;
      if (health <= 0) {
          return true;
      }
      return false;
    }
    
    /**
     * Get the current health of the player.
     * @return health  integer of spoops of health
     */
    public int getHealth()
    {
      return health;
    }
    
    /**
     * Get the room the player is currently in.
     */
    public Room getRoom()
    {
        return currentRoom;
    }
    
    public Item getItemFromInventory(String itemName)
    {
        for (Item item : inventory) {
            if (itemName.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }
    
     public boolean pickUpItem(Item currentItem)
    {
        if (currentItem.canTake()) 
        {
          if (getCurrentWeight() + currentItem.getWeight() <= maxWeight) 
          {
              inventory.add(currentItem);
              currentRoom.removeItem(currentItem);
              return true;
          } else 
          {
              System.out.println("This item doesn't fit in your inventory.\n");
          }
        } else 
        {
          System.out.println("You don't need this item.\n");
        }
        return false;
    }

     /**
     * Remove an item (after eating or using).
     * @param currentItem
     */
    public void removeItem(Item currentItem)
    {
      inventory.remove(currentItem);
    }
    
    /**
     * Get the current weight of the player's inventory.
     * @return int  current weight of player's inventory
     */
    private int getCurrentWeight()
    {
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight += item.getWeight(); 
        }
        return totalWeight;
    }
    
    public String getPlayerInventory()
    {
      String stringInventory = "";
      for (Item item : inventory) {
          stringInventory += item.getName() + "(" + item.getWeight() + ") ";
      }
      return stringInventory;
    }
}
