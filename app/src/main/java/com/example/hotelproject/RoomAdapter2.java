package com.example.hotelproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter2 extends RecyclerView.Adapter<RoomAdapter2.ViewHolder> {

    private List<Room> roomList;

    public RoomAdapter2(List<Room> roomList) {
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.roomNumberTextView.setText(room.getRoomNumber());
        holder.roomRateTextView.setText(room.getRoomRate());
        holder.roomPriceTextView.setText(room.getRoomPrice());
        holder.roomCapacityTextView.setText(room.getRoomCapacity());
        if (room.isReserved()) {
            holder.isReservedTextView.setText("Reserved");
        } else {
            holder.isReservedTextView.setText("Reserve");
        }
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView roomNumberTextView;
        public TextView roomRateTextView;
        public TextView roomPriceTextView;
        public TextView roomCapacityTextView;
        public TextView isReservedTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            roomNumberTextView = itemView.findViewById(R.id.room_number_text_view);
            roomRateTextView = itemView.findViewById(R.id.room_rate_text_view);
            roomPriceTextView = itemView.findViewById(R.id.room_price_text_view);
            roomCapacityTextView = itemView.findViewById(R.id.room_capacity_text_view);
            isReservedTextView = itemView.findViewById(R.id.is_reserved_text_view);
        }
    }
}

