package com.example.hotelproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;

    public RoomAdapter(List<Room> roomList) {
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.textViewRoomNumber.setText(room.getRoomNumber());
        holder.textViewRoomRate.setText(room.getRoomRate());
        holder.textViewRoomPrice.setText(room.getRoomPrice());

        if (room.isReserved()) {
            holder.buttonReserve.setText("Reserved");
            holder.buttonReserve.setEnabled(false);
        } else {
            holder.buttonReserve.setText("ooooooo");
            holder.buttonReserve.setEnabled(true);
        }

        // Handle reserve button click
        holder.buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!room.isReserved()) {
                    // Perform room reservation process
                    // ...
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewRoomNumber;
        private TextView textViewRoomRate;
        private TextView textViewRoomPrice;
        private Button buttonReserve;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRoomNumber = itemView.findViewById(R.id.textViewRoomNumber);
            textViewRoomRate = itemView.findViewById(R.id.textViewRoomRate);
            textViewRoomPrice = itemView.findViewById(R.id.textViewRoomPrice);
            buttonReserve = itemView.findViewById(R.id.buttonReserve);
        }
    }
}

