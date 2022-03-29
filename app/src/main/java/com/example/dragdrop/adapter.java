package com.example.dragdrop;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    List<String> list;
    Context context;

    public adapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);


        return  new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.t.setText(list.get(position));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void remove(String temp) {
        try
        {
       int p= list.indexOf(temp);
        //Toast.makeText(context, new String(String.valueOf(p)), Toast.LENGTH_SHORT).show();
            list.clear();
       notifyItemRemoved(p);
       notifyItemRangeChanged(p,getItemCount());}catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView t;

        public viewholder(@NonNull View itemView) {
            super(itemView);
           // itemView.setOnTouchListener(new MyTouchListener());
           itemView.setOnLongClickListener(new logngpress());
            t=itemView.findViewById(R.id.itemTextView);
            t.setGravity(Gravity.CENTER_HORIZONTAL);
            t.setGravity(Gravity.CENTER_VERTICAL);
            t.setAlpha(0.8F);
        }
    }
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);

                //view.setVisibility(View.INVISIBLE);
               // Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
               // view.setOnTouchListener(null);
                return true;
            } else {
                //Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
                view.setOnTouchListener(null);
                return false;
            }
        }
    }
    private  final class logngpress implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view) {
            //Toast.makeText(context, "working", Toast.LENGTH_SHORT).show();

            view.setOnTouchListener(new MyTouchListener());



            return true;
        }
    }
}
