package co.peludo90.material.navdrawer.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.peludo90.material.navdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentNavigationDrawer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentNavigationDrawer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNavigationDrawer extends Fragment {

   // TODO: Rename parameter arguments, choose names that match
   // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   private static final String ARG_PARAM1 = "param1";
   private static final String ARG_PARAM2 = "param2";
   private static final String PREF = "drawer_prefs";
   private static final String KEY_USER_CREATE_DRAWER = "k_u_c_d";
   private static final String KEY_SAVED_FROM_INSTANCE= "k_s_f_i";
   // TODO: Rename and change types of parameters
   private String mParam1;
   private String mParam2;

   private OnFragmentInteractionListener mListener;

   private ActionBarDrawerToggle actionBarDrawerToggle;
   private DrawerLayout drawerLayout;
   private Toolbar toolbar;
   private boolean userCreateDrawer;
   private boolean savedFromSavedInstance;


   /**
    * Use this factory method to create a new instance of
    * this fragment using the provided parameters.
    *
    * @param param1 Parameter 1.
    * @param param2 Parameter 2.
    *
    * @return A new instance of fragment FragmentNavigationDrawer.
    */
   // TODO: Rename and change types and number of parameters
   public static FragmentNavigationDrawer newInstance (String param1, String param2) {

      FragmentNavigationDrawer fragment = new FragmentNavigationDrawer();
      Bundle args = new Bundle();
      args.putString(ARG_PARAM1, param1);
      args.putString(ARG_PARAM2, param2);
      fragment.setArguments(args);
      return fragment;
   }

   public FragmentNavigationDrawer () {
      // Required empty public constructor
   }

   @Override
   public void onCreate (Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      if (getArguments() != null) {
         mParam1 = getArguments().getString(ARG_PARAM1);
         mParam2 = getArguments().getString(ARG_PARAM2);
      }

      if(savedInstanceState!=null){
         savedFromSavedInstance = true;
      }

     userCreateDrawer = Boolean.getBoolean(readPreferences(getContext(),  KEY_USER_CREATE_DRAWER, "false"));

   }

   /*
      @Override
      public void onCreate (Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);
         if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
         }
      }   // TODO: Rename method, update argument and hook method into UI event
   */
   public void onButtonPressed (Uri uri) {

      if (mListener != null) {
         mListener.onFragmentInteraction(uri);
      }
   }

   @Override
   public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_fragment_navigation_drawer, container, false);
   }

   @Override
   public void onAttach (Context context) {

      super.onAttach(context);
      if (context instanceof OnFragmentInteractionListener) {
         mListener = (OnFragmentInteractionListener) context;
      } else {
         throw new RuntimeException(context.toString()
                 + " must implement OnFragmentInteractionListener");
      }
   }

   @Override
   public void onDetach () {

      super.onDetach();
      mListener = null;
   }

   /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p/>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
   public interface OnFragmentInteractionListener {

      // TODO: Update argument type and name
      void onFragmentInteraction (Uri uri);
   }

   public void setUp (int fragment, DrawerLayout drawerLayout, Toolbar toolbar) {
      View  view= getActivity().findViewById(fragment);

      actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open_dwawer, R.string.close_drawer) {
         @Override
         public void onDrawerClosed (View drawerView) {
            super.onDrawerClosed(drawerView);
            getActivity().invalidateOptionsMenu();
         }

         @Override
         public void onDrawerOpened (View drawerView) {
            super.onDrawerOpened(drawerView);
            if(!userCreateDrawer){
               userCreateDrawer = true;
               savePreferences(getActivity(),KEY_USER_CREATE_DRAWER, "true");
            }
            getActivity().invalidateOptionsMenu();
         }
      };

      if(!userCreateDrawer && !savedFromSavedInstance){
         drawerLayout.openDrawer(view);
      }
      drawerLayout.setDrawerListener(actionBarDrawerToggle);
      drawerLayout.post(new Runnable() {
         @Override
         public void run () {
            actionBarDrawerToggle.syncState();
         }
      });
   }

   public  static void savePreferences (Context context, String prefName, String value){

      SharedPreferences sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(prefName, value);
      editor.apply();
   }


   public static String readPreferences (Context context, String prefName, String defaulValue){
      SharedPreferences sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
      return  sharedPreferences.getString(prefName, defaulValue);
   }
}