import java.util.*;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> items;
    private Room currentRoom;

    /**
     * Construutor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        items = new ArrayList<Item>();
    }
    public void goToRoom(Room room)
    {
        currentRoom = room;
    }
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    public void dropItem(Item item)
    {
        items.remove(item);
        currentRoom.addItem(item);
        System.out.println("You dropped " + item.getName());
    }
    public void pickUpItem(Item item)
    {
        if(items.size() <= 4 && item.getPickUpable())
        {
            System.out.println("You picked up " + item.getName());
            currentRoom.takeItem(item);
            items.add(item);
        }else
        {
            if(!item.getPickUpable())
            {
                System.out.println("You can't pick this item up!");
            }
            if(!(items.size() <= 4))
            {
                System.out.println("You have too many items!");
            }
        }
    }
    public ArrayList<Item> getInventory()
    {
        return items;
    }
    public void showInventory()
    {
        if (items.size() > 0)
        {
            System.out.println("You're carrying");
            for (int i = 0; i < items.size(); i++)
            {
                System.out.println(">" + items.get(i).getName());
            }
        }else
        {
            System.out.println("You're not carrying anything silly!");
        }
    }
    public void dropItem(int index)
    {
        items.remove(index);   
    }
}
