package co.peludo90.material.navdrawer;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import co.peludo90.material.navdrawer.fragments.FragmentNavigationDrawer;

public class MainActivity extends AppCompatActivity implements FragmentNavigationDrawer.OnFragmentInteractionListener{

   private Toolbar toolbar;
   private FragmentNavigationDrawer fragmentNavigationDrawer;
   private DrawerLayout drawerLayout;

   @Override
   protected void onCreate (Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      toolbar = (Toolbar) findViewById(R.id.main_app_bar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      drawerLayout = (DrawerLayout) findViewById(R.id.act_main_drawer);
      fragmentNavigationDrawer = (FragmentNavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.act_main_frag);
      fragmentNavigationDrawer.setUp(R.id.act_main_frag,drawerLayout,toolbar);

   }

   @Override
   public boolean onCreateOptionsMenu (Menu menu) {

      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.main_menu, menu);
      return true;
   }

   @Override
   public void onFragmentInteraction (Uri uri) {

   }

   @Override
   public boolean onOptionsItemSelected (MenuItem item) {

      switch (item.getItemId()) {
         case R.id.ic_common_next:
            startActivity(new Intent(this, NextActivity.class));
            break;
         case R.id.ic_common_settings:
            Toast.makeText(this, "Fuck", Toast.LENGTH_LONG).show();
            break;
         default:
            return false;
      }

      return true;
   }
}
