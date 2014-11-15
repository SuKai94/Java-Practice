package com.susu.androiddemo;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class TaskFragment extends ListFragment {
	
	private String[] taskArray = {"XTT", "SuSu"};
	private ListView mListView;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.list_fragment, container, false);
		mListView = (ListView) mView.findViewById(android.R.id.list);
		return mView;
    }
    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        b = savedInstanceState;
        String[] list = {"Class 1","Class 2","class 3","Class 4","Class 5"};  
        adapter = new SimpleAdapter(getActivity(), getData(list), R.layout.item_list, new String[]{"title"}, new int[]{R.id.title});
        setListAdapter(adapter);
    }
    
}
