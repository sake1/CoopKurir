package com.coopmart.kurir.coopkurir.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.coopmart.kurir.coopkurir.Activity.HomeActivity;
import com.coopmart.kurir.coopkurir.Method.Navigator;
import com.coopmart.kurir.coopkurir.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickupFragment extends Fragment {

    public static final String TAG = "Jadwal Pengambilan Produk";

    @BindView(R.id.fp_no_list_label) TextView message;
    @BindView(R.id.fp_pickup_list) ListView listView;

    private ArrayList<String[]> pickupData;

    private class CustomArrayAdapter extends ArrayAdapter<String[]> {

        private ArrayList<String[]> data;
        private int lastPosition = -1;
        private class ViewHolder {
            public TextView session;
            public TextView time;
            public TextView farmerName;
            public FrameLayout frame;
            public ImageView image;
        }

        public CustomArrayAdapter(Context context, ArrayList<String[]> data) {
            super(context, R.layout.listview_pickup_list, data);
            this.data = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String[] data = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            PickupFragment.CustomArrayAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new PickupFragment.CustomArrayAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.listview_pickup_list, parent, false);
                viewHolder.session = (TextView) convertView.findViewById(R.id.pl_output_session);
                viewHolder.time = (TextView) convertView.findViewById(R.id.pl_output_time);
                viewHolder.farmerName = (TextView) convertView.findViewById(R.id.pl_output_farmenr_name);
                viewHolder.frame = (FrameLayout) convertView.findViewById(R.id.pl_output_status_frame);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.pl_output_status_picture);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (PickupFragment.CustomArrayAdapter.ViewHolder) convertView.getTag();
                result=convertView;
            }

            Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.fadein : R.anim.fadeout);
            result.startAnimation(animation);
            lastPosition = position;

            viewHolder.session.setText(data[0]);
            viewHolder.time.setText(data[1]);
            viewHolder.farmerName.setText(data[2]);

            if(data[3].equals("COMPLETED")) {
                viewHolder.frame.setBackgroundColor(getResources().getColor(R.color.greenCompleted));
                viewHolder.image.setImageResource(R.drawable.check_white_logo);
            } else if(data[3].equals("NOT COMPLETED")) {
                viewHolder.frame.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                viewHolder.frame.setBackgroundColor(getResources().getColor(R.color.redImportant));
                viewHolder.image.setImageResource(R.drawable.exclamation_white_logo);
            }
            // Return the completed view to render on screen
            return convertView;
        }
    }
    private class CustomOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position < 3) {
                return;
            }
            ((HomeActivity) getActivity()).changeHomeActivityDisplayedFragment(new PickupDetailFragment(), true);
        }
    }

    public PickupFragment() {
        pickupData = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pickup, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(TAG);
        ButterKnife.bind(this, view);

        displayListViewContent();
        listView.setOnItemClickListener(new CustomOnItemClickListener());

        return view;
    }

    private void getPickupRequestData() {
        /**
         * TODO:
         * Create enum class to define the rquest status types
         */

        pickupData.add(new String[] {"Pickup sesi 1", "Selesai 16/06/17 06.28", "Budi Udi", "COMPLETED"});
        pickupData.add(new String[] {"Pickup sesi 1", "Selesai 16/06/17 06.41", "Udin Din", "COMPLETED"});
        pickupData.add(new String[] {"Pickup sesi 2", "Selesai 16/06/17 13.11", "Dinda Da", "COMPLETED"});
        pickupData.add(new String[] {"Pickup sesi 2", "16/06/17 (Hari ini)", "Danu Nu", "NOT COMPLETED, TODAY"});
        pickupData.add(new String[] {"Pickup sesi 2", "16/06/17 (Hari ini)", "Nuri Ri", "NOT COMPLETED, TODAY"});
        pickupData.add(new String[] {"Pickup sesi 1", "17/06/17", "Ridya Ya", "NOT COMPLETED"});
        pickupData.add(new String[] {"Pickup sesi 2", "16/06/17", "Yamin Min", "NOT COMPLETED"});
    }

    private void displayListViewContent() {
        pickupData.clear();
        getPickupRequestData();
        if(pickupData.size() == 0) {
            message.setVisibility(View.VISIBLE);
            return;
        }

        ArrayAdapter adapter = new PickupFragment.CustomArrayAdapter(getActivity(), pickupData);
        listView.setAdapter(adapter);
        listView.setFooterDividersEnabled(true);
        listView.setDividerHeight(1);
    }

}
