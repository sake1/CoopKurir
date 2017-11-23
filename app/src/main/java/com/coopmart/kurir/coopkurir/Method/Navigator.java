package com.coopmart.kurir.coopkurir.Method;


/**
 * Created by sake on 08/06/17.
 */

public class Navigator {
    public static final int ERROR_CODE = -1;

    public static final int FROM_LOGIN_ACTIVITY = 0;
    public static final int REQUEST_PROFILE_EDIT = 1;
    public static final int FROM_SETTING_ACTIVITY = 2;
    public static final int FROM_HOME_ACTIVITY = 3;
    public static final int COMPLETED_PICKUP = 4;
    public static final int UNCOMPLETED_PICKUP = 5;

    public static final int FROM_GOOGLE_MAPS_PICK_LOCATION = 6;
    public static final int FROM_GALLERY = 7;
    public static final int CODE_REQUEST_EXTERNAL_STORAGE = 8;
    public static final int CODE_REQUEST_LOCATION_ACCESS = 9;


//    public static void changeHomeActivityDisplayedFragment(Fragment intendedFragment, FragmentActivity fragment) {
//        /**
//         * TODO
//         * Add a prevention of any other kind of activity get passed to second param
//         */
//        FragmentManager fragmentManager = fragment.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
//        fragmentTransaction.replace(R.id.h_fragment_frame, intendedFragment);
//        fragmentTransaction.commit();
//    }
}
