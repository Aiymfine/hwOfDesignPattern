import java.util.*;
class Player{
    private Room currentRoom;
    private List<Item> inventory=new ArrayList<>();
    public Player(Room startingRoom){
        this.currentRoom=startingRoom;
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Room room){
        this.currentRoom=room;
    }
    public List<Item> getInventory(){
        return inventory;
    }
    public void addItem(Item item){
        inventory.add(item);
    }
}