package hotel;

public class RoomType {
    private  int room_type_id;

    public int getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(int room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getRoom_type_name() {
        return room_type_name;
    }

    public void setRoom_type_name(String room_type_name) {
        this.room_type_name = room_type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String room_type_name;
    private String description;
    public RoomType(int room_type_id,String room_type_name,String description){
        this.room_type_id = room_type_id;
        this.room_type_name = room_type_name;
        this.description = description;
    }
}
