package edu.bu.metcs.myproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;

import edu.bu.metcs.myproject.Database.MyFoodManagerDao;

public class FridgeFoodListAdapter extends RecyclerView.Adapter<FridgeFoodListAdapter.FridgeFoodListViewHolder>{

private final static String TAG = FridgeFoodListAdapter.class.getSimpleName ();
private ArrayList<FoodItem> foodItems;
private Listener listener;


//private ImageView mEditImage, mDeleteImage;

    public interface Listener {
      //  void onClick(int position);
        void onEditClick(int position, int foodspaceId) throws ParseException;
        void onDeleteClick(int position, int foodspaceId, String itemName) throws ParseException;
    }

    public FridgeFoodListAdapter(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }


    @Override
    public FridgeFoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_food_list, parent, false);
        return new FridgeFoodListViewHolder(cardView, listener, foodItems);
    }

    @Override
    public void onBindViewHolder(FridgeFoodListViewHolder viewHolder, final int position) {
        CardView cardView = viewHolder.fridgeFoodListCardView;

      //  Log.d(TAG, "onBind : "+projects[position].getSummary());
      //  Log.d(TAG, "onBindtitle : "+projects[position].getTitle());
        TextView textView = (TextView) cardView.findViewById(R.id.foodNameTextViewId);
        Log.d(TAG, "foodItem.getName : "+foodItems.get(position).getName());
        textView.setText(foodItems.get(position).getName());
    }


    @Override
    public int getItemCount()
    {
        return foodItems.size();
    }

    public static class FridgeFoodListViewHolder extends RecyclerView.ViewHolder  {

        private TextView fridgeFoodListView;
        private CardView fridgeFoodListCardView;
        private ImageView mEditImage, mDeleteImage;

        public FridgeFoodListViewHolder(View itemView, final Listener listener1, final ArrayList<FoodItem> foodItems) {
            super(itemView);
            fridgeFoodListCardView = (CardView)itemView;
            fridgeFoodListView = (TextView) itemView.findViewById(R.id.foodNameTextViewId);
            mEditImage = itemView.findViewById(R.id.edit_image);

            mEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    if (listener1 != null) {
                        int position = getAdapterPosition();
                        // int foodspace = 0;
                         //  int itemId = position;
                        if (position != RecyclerView.NO_POSITION) {
                            try {
                                listener1.onEditClick(foodItems.get(position).getId(), foodItems.get(position).getSpaceId());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        //listener1.onEditClick(position, 0, itemId);
                         }
                    }
                }
            });

            mDeleteImage = itemView.findViewById(R.id.delete_image);
            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener1 != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            try {
                                listener1.onDeleteClick(foodItems.get(position).getId(),
                                    foodItems.get(position).getSpaceId(),
                                    foodItems.get(position).getName());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
