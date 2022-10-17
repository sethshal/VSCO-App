package shalini.mvvm.vsco.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import dagger.hilt.android.AndroidEntryPoint;
import shalini.mvvm.vsco.R;

@AndroidEntryPoint
public class VSCOActivity extends AppCompatActivity {

    private NavController navController;

    /**
     * All the initialization for the activity happens here. Note: If the device is rotated
     * this is called again. Need to be careful avoid network calls here. This sets up the bindings,
     * toolbar and the observers.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vsco_activity);
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
