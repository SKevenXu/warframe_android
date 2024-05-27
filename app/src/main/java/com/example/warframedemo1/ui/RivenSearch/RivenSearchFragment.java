package com.example.warframedemo1.ui.RivenSearch;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.warframedemo1.HttpUtils;
import com.example.warframedemo1.R;
import com.example.warframedemo1.showProgressBar;
import com.example.warframedemo1.warframemarket.items_name;
import com.example.warframedemo1.databinding.FragmentRivensearchBinding;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class RivenSearchFragment extends Fragment {
    AlertDialog alertDialog;
    public ArrayAdapter<String> adapter=null;
    public List list=null;
    public ArrayList url_num=null;
    class thread extends Thread{
//        items_name it=new items_name();

        SearchView searchView=getView().findViewById(R.id.rivensearch_search);
        ListView listView=getView().findViewById(R.id.rivensearch_listview);
        TextView textView=getView().findViewById(R.id.text_RivenSearch);

        thread() throws Exception {
        }

        public payload getweaponname() throws Exception {
            HttpUtils http=new HttpUtils();
            String temp= http.getFromAssets("weapon_url_name.json",getActivity());
            //String temp=http.getData_zh("https://api.warframe.market/v1/riven/items");//error: int java.util.List.size()
            Gson gson=new Gson();

            payload demo=gson.fromJson(temp,payload.class);
            //System.out.println(temp);
            return demo;
        }


                payload pay = getweaponname();


//                for(int i=0;i<pay.items.size();i++){
//                    System.out.println(pay.items.get(i).item_name);
//                }

        @Override
        public void run() {


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
//                        String pattern=".*"+newText+".*";
//                        Pattern p=Pattern.compile(pattern);
//
//                        for(int i=0;i<pay.items.size();i++){
//                            Matcher m=p.matcher(pay.items.get(i).item_name);
//                            if(m.find()==true){
//                                adapter.add(m.group());
//                                System.out.println(m.group());
//                                listView.setAdapter(adapter);
//                            }
//                        }
                            return false;
                    }
                });
//                searchView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        System.out.println("onclick"+v);
//                        list.clear();
//                        adapter =new ArrayAdapter<String>(getActivity(),R.layout.listviewlayout,list);
//                        listView.setAdapter(adapter);
//                    }
//                });
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
                        Uri content = Uri.parse("https://warframe.market/zh-hans/auctions/search?type=riven&weapon_url_name="+weapon_name+"&polarity=any&sort_by=price_asc");
                        intent.setData(content);
                        startActivity(intent);
                        System.out.println("on click item\t\t"+position+"\t\t"+id);
                    }
                });
            });
            showProgressBar show=new showProgressBar();
            show.hideimageDialog(alertDialog);
        }
    }
    private FragmentRivensearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RivenSearchViewModel rivenSearchViewModel =
                new ViewModelProvider(this).get(RivenSearchViewModel.class);

        binding = FragmentRivensearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRivenSearch;
        rivenSearchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
        alertDialog=show.showimageDialog(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(()->{
            thread th= null;
            try {
                th = new thread();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            th.start();
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    class payload {
        List<items> items;

        public List<items> getItems() {
            return items;
        }

        public void setItems(List<items> items) {
            this.items = items;
        }
    }

    class items {
        String item_name;
        String url_name;

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
}
