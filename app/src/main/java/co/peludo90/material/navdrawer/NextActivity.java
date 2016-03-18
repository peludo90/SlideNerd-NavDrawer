package co.peludo90.material.navdrawer;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NextActivity extends AppCompatActivity {

   private Toolbar toolbar;

   @Override
   protected void onCreate (Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_next);
      toolbar = (Toolbar) findViewById(R.id.main_app_bar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   }

   @Override
   public boolean onCreateOptionsMenu (Menu menu) {

      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected (MenuItem item) {

      switch (item.getItemId()) {
         case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            break;

         default:
            return false;
      }

      return true;
   }
}
