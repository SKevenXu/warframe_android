package com.example.warframedemo1.ui.ItemSearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.warframedemo1.HttpUtils;
import com.example.warframedemo1.R;
import com.example.warframedemo1.databinding.FragmentItemsearchBinding;
import com.example.warframedemo1.databinding.FragmentRivensearchBinding;
import com.example.warframedemo1.databinding.FragmentVoidtraderBinding;
import com.example.warframedemo1.showProgressBar;
import com.example.warframedemo1.ui.RivenSearch.RivenSearchViewModel;
import com.example.warframedemo1.warframedata.datafromlog.USB_demo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ItemSearchFragment extends Fragment{
    public ArrayAdapter<String> adapter=null;
    public List list=null;
    public ArrayList url_num=null;







    class thread extends Thread{
        HttpUtils http=new HttpUtils();
        Gson gson=new Gson();
        SearchView searchView=getView().findViewById(R.id.itemsearch_searchs);
        ListView listView=getView().findViewById(R.id.itemsearch_listview);

        thread() throws IOException {
        }
        //SearchView searchView=getView().findViewById(R.id.itemsearch_search);


        public payload getitemname()  {
            payload pay=null;
            try {
                    String temp=http.getData_zh("https://api.warframe.market/v1/items");
                    // System.out.println(temp);
                    test test=gson.fromJson(temp,test.class);
                     pay=gson.fromJson(test.payload, payload.class);
                }catch (Exception e){

                }

                return pay;
        }


        @Override
        public void run(){

                payload pay = getitemname();
                //System.out.println(pay.items.get(0).item_name);

            getActivity().runOnUiThread(()->{



                searchView.onActionViewExpanded();
                searchView.setIconified(false);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        String pattern=".*"+query+".*";
                        Pattern p=Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

                        list=new ArrayList();
                        url_num=new ArrayList();
                        for(int i=0;i<pay.items.size();i++){
                            Matcher m=p.matcher(pay.items.get(i).item_name);

                            if(m.find()==true){
                                list.add(m.group());
                                url_num.add(pay.items.get(i).url_name);
                                System.out.println(m.group());

                            }
                        }
                        adapter =new ArrayAdapter<String>(getActivity(),R.layout.listviewlayout,list);
                        listView.setAdapter(adapter);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return true;
                    }
                });
                searchView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("onclick"+v);
                        list.clear();
                        adapter =new ArrayAdapter<String>(getActivity(),R.layout.listviewlayout,list);
                        listView.setAdapter(adapter);
                    }
                });
                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        System.out.println("on close");
                        list.clear();
                        url_num.clear();
                        adapter =new ArrayAdapter<String>(getActivity(),R.layout.listviewlayout,list);
                        listView.setAdapter(adapter);
                        return false;
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent= new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        String weapon_name=url_num.get(position).toString();
                        Uri content = Uri.parse("https://warframe.market/zh-hans/items/"+weapon_name);
                        intent.setData(content);
                        startActivity(intent);
                        System.out.println("on click item\t\t"+position+"\t\t"+id);
                    }
                });
            });
            showProgressBar show=new showProgressBar();
            show.hideProgressDialog();



        }
    }
    private FragmentItemsearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ItemSearchViewModel ItemsearchViewModel =
                new ViewModelProvider(this).get(ItemSearchViewModel.class);

        binding = FragmentItemsearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textItemSearch;
        ItemsearchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressBar show=new showProgressBar();
        show.showProgressDialog(getActivity(),"loading...");
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(()->{
            try {
                System.out.println("thread start!");
                thread th= null;

                th = new thread();
                th.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}
class test{
    JsonObject payload;

    public JsonObject getPayload() {
        return payload;
    }

    public void setPayload(JsonObject payload) {
        this.payload = payload;
    }
}
class payload{
    List<items> items;
    public List<items> getItems() {
        return items;
    }

    public void setItems(List<items> items) {
        this.items = items;
    }
}
class items{
    String url_name;
    String item_name;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getUrl_name() {
        return url_name;
    }

    public void setUrl_name(String url_name) {
        this.url_name = url_name;
    }
}




