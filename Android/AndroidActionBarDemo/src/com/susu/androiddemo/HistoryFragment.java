package com.susu.androiddemo;

import android.app.ActionBar.LayoutParams;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HistoryFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
        return inflateAndSetupView(inflater, container, savedInstanceState, R.layout.history_fragment);      
    }  
      
    private View inflateAndSetupView(LayoutInflater inflater, ViewGroup container,   
            Bundle savedInstanceState, int layoutResourceId) {  
        View layout = inflater.inflate(layoutResourceId, container, false);  
          
        return layout;  
    }

}
