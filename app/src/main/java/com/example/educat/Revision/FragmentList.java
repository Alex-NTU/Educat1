package com.example.educat.Revision;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educat.R;

public class FragmentList extends Fragment {

    ListView listView;
    RecyclerView recyclerView;
    public FragmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PdfViewer pdfViewer;
        View rootView = inflater.inflate(R.layout.fragmentlist, container, false);
        listView =(ListView) rootView.findViewById(R.id.list);
        String[] values = new String[] {
                "Systems Architecture Revision",
                "Memory And Storage Revision",
                "Systems Revision",
                "Networks",
                "Security",
                "Legal And Ethical Revision",
                "Algorithms Revision",
                "Programming",
                "Data Revision",
                "Logic Revision"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listView.getItemAtPosition(position);

                if (position == 0) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 0);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();

                }
                else if (position == 1) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 1);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 2) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 2);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 3) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 3);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 4) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 4);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 5) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 5);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 6) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 6);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 7) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 7);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 8) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 8);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
                else if (position == 9) {
                    PdfViewer sampleFragment = new PdfViewer();
                    Bundle args = new Bundle();
                    args.putInt("key", 9);
                    sampleFragment.setArguments(args);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, sampleFragment)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }

            }
        });
        return rootView;
    }

}
