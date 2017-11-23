package com.coopmart.kurir.coopkurir.Fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coopmart.kurir.coopkurir.Activity.HomeActivity;
import com.coopmart.kurir.coopkurir.Activity.PathFinderActivity;
import com.coopmart.kurir.coopkurir.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickupDetailFragment extends Fragment {

    public static final String TAG = "Detil Pengambilan Produk";

    @BindView(R.id.fpr_output_timestamp) TextView timestamp;
    @BindView(R.id.fpr_output_session) TextView session;
    @BindView(R.id.fpr_output_farmer_name) TextView farmerName;
    @BindView(R.id.fpr_output_address) TextView address;
    @BindView(R.id.fpr_output_product_name) TextView productName;
    @BindView(R.id.fpr_output_product_qty) TextView productQty;

    private int detailCode;

    @OnClick(R.id.fpr_trigger_submit)
    public void submit() {
        final Dialog report = new Dialog(getActivity());
        report.setContentView(R.layout.dialog_product_pickup_report);
        report.show();

        report.findViewById(R.id.dppr_trigger_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(PickupDetailFragment.this.getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Mohon Tunggu");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                Toast.makeText(PickupDetailFragment.this.getActivity(), "Transaksi Berhasil!", Toast.LENGTH_LONG).show();
                                ((HomeActivity) getActivity()).popBackStack().changeHomeActivityDisplayedFragment(new PickupFragment(), false);
                                report.dismiss();
                                progressDialog.dismiss();
                            }
                        }, 3000);

            }
        });
    }

    @OnClick(R.id.fpr_trigger_location_finder)
    public void locationFinder() {
        Intent intent = new Intent(getActivity(), PathFinderActivity.class);
        startActivity(intent);
    }

    public PickupDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pickup_detail_uncompleted, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(TAG);
        ButterKnife.bind(this, view);

        setDisplayedData();

        return view;
    }

    private void setDisplayedData() {
        timestamp.setText("Jumat 16 Juli 2017");
        session.setText("Sesi 1");
        farmerName.setText("Budi Udi");
        address.setText("Jalan Surakarti No.20 A5/20, Tangerang, Banten");
        productName.setText("Cabai Merah");
        productQty.setText("500 gr");
    }
}
