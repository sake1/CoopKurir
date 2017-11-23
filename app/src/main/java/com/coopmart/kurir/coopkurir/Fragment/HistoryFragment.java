package com.coopmart.kurir.coopkurir.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.coopmart.kurir.coopkurir.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    public static final String TAG = "Riwayat Transaksi";

    @BindView(R.id.fh_no_list_label) TextView message;
    @BindView(R.id.fh_transaction_list) ListView listView;

    private ArrayList<String[]> transactionData;

    private class CustomArrayAdapter extends ArrayAdapter<String[]> {

        private ArrayList<String[]> data;
        private int lastPosition = -1;
        private class ViewHolder {
            public TextView name;
            public TextView time;
            public TextView farmer;
            public TextView qty;
            public TextView price;
            public ImageView image;
        }

        public CustomArrayAdapter(Context context, ArrayList<String[]> data) {
            super(context, R.layout.listview_transaction_history, data);
            this.data = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String[] data = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            HistoryFragment.CustomArrayAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new HistoryFragment.CustomArrayAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.listview_transaction_history, parent, false);
                viewHolder.name = (TextView) convertView.findViewById(R.id.th_output_product_name);
                viewHolder.time = (TextView) convertView.findViewById(R.id.th_output_timestamp);
                viewHolder.farmer = (TextView) convertView.findViewById(R.id.th_output_farmer_name);
                viewHolder.qty = (TextView) convertView.findViewById(R.id.th_output_product_quantity);
                viewHolder.price = (TextView) convertView.findViewById(R.id.th_output_prduct_payment);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.th_product_picture);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (HistoryFragment.CustomArrayAdapter.ViewHolder) convertView.getTag();
                result=convertView;
            }

            Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.fadein : R.anim.fadeout);
            result.startAnimation(animation);
            lastPosition = position;

            viewHolder.name.setText(data[0]);
            viewHolder.time.setText(data[1]);
            viewHolder.farmer.setText(data[2]);
            viewHolder.qty.setText(data[3]);
            viewHolder.price.setText(data[4]);

            // Return the completed view to render on screen
            return convertView;
        }
    }

    public HistoryFragment() {
        transactionData = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(TAG);
        ButterKnife.bind(this, view);

        displayListViewContent();

        return view;
    }

    private void getPickupRequestData() {
        transactionData.add(new String[] {"Bayam", "07:12 14/06/17 (Sesi 1)", "Budi Udi", "Jumlah: 500 gr", "Rp. 2000"});
        transactionData.add(new String[] {"Cabai Merah", "07:32 14/06/17 (Sesi 1)", "Udin Din", "Jumlah: 1000 gr ", "Rp. 24000"});
        transactionData.add(new String[] {"CabaI Merah", "11:44 14/06/17 (Sesi 2)", "Dinda Da", "Jumlah: 500 gr", "Rp. 12000"});
        transactionData.add(new String[] {"Jeruk Nipis", "12:12  14/06/17 (Sesi 2)", "Dani Ni", "Jumlah: 3000 gr", "Rp. 18000"});
        transactionData.add(new String[] {"Bayam", "17:59  14/06/17 (Sesi 3)", "Nila La", "Jumlah: 500 gr", "Rp. 2000"});
        transactionData.add(new String[] {"Jeruk Nipis", "16:59 14/06/17 (Sesi 3)", "Lana Na", "Jumlah: 1500 gr", "Rp. 6000"});
    }

    private void displayListViewContent() {
        transactionData.clear();
        getPickupRequestData();
        if(transactionData.size() == 0) {
            message.setVisibility(View.VISIBLE);
            return;
        }

        ArrayAdapter adapter = new HistoryFragment.CustomArrayAdapter(getActivity(), transactionData);
        listView.setAdapter(adapter);
        listView.setFooterDividersEnabled(true);
        listView.setDividerHeight(1);
    }
}
