import java.util.*;

class Room{
    private String description;
    private Map<String, Room> connections;
    private List<Item> items;
    private boolean doorLocked;

    private Room(Builder builder){
        this.description=builder.description;
        this.connections=builder.connections;
        this.items=builder.items;
        this.doorLocked=builder.doorLocked;
    }
    public String describe(){
        return description;
    }
    public Room getConnection(String direction){
        return connections.get(direction);
    }
    public List<Item> getItems(){
        return items;
    }
    public boolean isDoorLocked(){
        return doorLocked;
    }
    public void unlockDoor(){
        this.doorLocked=false;
    }
    public void removeItem(Item item){
        items.remove(Item);
    }

    public static class Builder{
    private String description;
    private Map<String, Room> connections=new HashMap<>();
    private List<Item> items= new ArrayList<>();
    private boolean doorLocked= false;
    public Builder description(String description){
        this.description=description;
        return this;
    }
    public Builder connect(String direction, Room room){
        connections.put(direction,room);
        return this;
    }
    public Builder addItem(Item item){
        items.add(item);
        return this;
    }
    public Builder doorLocked(boolean locked){
        this.doorLocked=locked;
        return this;
    }
    public Room build(){
        return new Room(this);
    }
}

}