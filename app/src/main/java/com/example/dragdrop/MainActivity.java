package com.example.dragdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {
    RecyclerView list;
    TextView droped, mTextView, mText;
    List<String> wordList= new ArrayList<>();
    List<String> textview= new ArrayList<>();
    adapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        list=findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        droped=findViewById(R.id.textView);
        droped.setOnDragListener(new MyDragListener());



         add(2000);

        wordList.add("word1");
        wordList.add("word2");
        wordList.add("word3");
        wordList.add("word4");
        wordList.add("word5");
        wordList.add("word1");
        wordList.add("word2");
        wordList.add("word3");
        wordList.add("word4");
        wordList.add("word5");

        String temp=droped.getText().toString();
       // Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();

        String[] arrOfStr = temp.split(" ");

        for (String a : arrOfStr)
        {
            textview.add(a);
            //Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
        }

        //wordList.remove(new String("word1"));





        //wordList = Arrays.asList(vec.v);
        a=new adapter(wordList,MainActivity.this);
        list.setAdapter(a);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
       // itemTouchHelper.attachToRecyclerView(list);

    }

    private void add(int n) {
        for(int i=0;i<n;i++)
        {
            wordList.add("word"+i);
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|
            ItemTouchHelper.START|ItemTouchHelper.END,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int formposition=viewHolder.getAdapterPosition();
            int toposition=target.getAdapterPosition();
            Collections.swap(wordList,formposition,toposition);
            recyclerView.getAdapter().notifyItemMoved(formposition,toposition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Toast.makeText(MainActivity.this, "Drag_entered", Toast.LENGTH_SHORT).show();
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Toast.makeText(MainActivity.this, "Exit drag", Toast.LENGTH_SHORT).show();
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup






                    TextView t;
                    View view = (View) event.getLocalState();
                    t=view.findViewById(R.id.itemTextView);
                    String temp=t.getText().toString();
                    String r=temp;
                    textview.add(r);

                    temp=all(textview);
                    TextView container = (TextView) v;
                    container.setText(temp);
                   //view.setVisibility(View.VISIBLE);

                    wordList.remove(new String(r));
                    list.setAdapter(null);
                    a=new adapter(wordList,MainActivity.this);
                    list.setAdapter(a);


                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    private String all(List<String> strings) {

        StringBuilder sb = new StringBuilder();
        for(String s: strings)
        {
            s+=' ';
            sb.append(s);
        }
        return sb.toString();
    }
}