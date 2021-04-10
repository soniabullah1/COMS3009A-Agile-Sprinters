package com.example.agilesprintersapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class chatListAdapter extends ArrayAdapter<Chats_list> {
    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView contactName;
        TextView msg;
        TextView time;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public chatListAdapter(Context context, int resource, ArrayList<Chats_list> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getContactName();
        String birthday = getItem(position).getMesgRecieved();
        String sex = getItem(position).getTimeStamp();

        //Create the person object with the information
        Chats_list person = new Chats_list(name,birthday,sex);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.contactName = (TextView) convertView.findViewById(R.id.contactNametxt);
            holder.msg = (TextView) convertView.findViewById(R.id.msgtxt);
            holder.time = (TextView) convertView.findViewById(R.id.timeStamptxt);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        holder.contactName.setText(person.getContactName());
        holder.msg.setText(person.getMesgRecieved());
        holder.time.setText(person.getTimeStamp());


        return convertView;
    }
}
