package com.example.hotelproject;

public class Room {
    private String roomNumber;
    private String roomRate;
    private String roomPrice;
    private int roomCapacity;
    private boolean isReserved;

    public Room(String roomNumber, String roomRate, String roomPrice, int roomCapacity, boolean isReserved) {
        this.roomNumber = roomNumber;
        this.roomRate = roomRate;
        this.roomPrice = roomPrice;
        this.roomCapacity = roomCapacity;
        this.isReserved = isReserved;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(String roomRate) {
        this.roomRate = roomRate;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
