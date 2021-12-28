package com.adarsh.crimereportandroidphp.ui.citizen.ipc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.adapters.IpcAdapter;
import com.adarsh.crimereportandroidphp.adapters.SearchIpcAdapter;
import com.adarsh.crimereportandroidphp.retrofit.models.SearchIpcModel;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewIpcModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IpcFragment extends Fragment {



    private RecyclerView ipcRecycler;
    private EditText searchView;
    private ImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ipc, container, false);
        initView(root);
        Api api= ApiClient.Citizen().create(Api.class);
        api.VIEW_IPC_MODEL_CALL().enqueue(new Callback<ViewIpcModel>() {
            @Override
            public void onResponse(Call<ViewIpcModel> call, Response<ViewIpcModel> response) {
                ViewIpcModel viewIpcModel=response.body();
                if(viewIpcModel.getStatus().equalsIgnoreCase("success"))
                {
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                    ipcRecycler.setLayoutManager(linearLayoutManager);
                    IpcAdapter adapter=new IpcAdapter(viewIpcModel,getActivity());
                    ipcRecycler.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(getActivity(), "No IPC Sections Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewIpcModel> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(("").equals(searchView.getText().toString()))
                {
                    Toast.makeText(getContext(), "Enter your search query", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Api api= ApiClient.Citizen().create(Api.class);
                    api.SEARCH_IPC_MODEL_CALL(searchView.getText().toString()).enqueue(new Callback<SearchIpcModel>() {
                        @Override
                        public void onResponse(Call<SearchIpcModel> call, Response<SearchIpcModel> response) {
                            SearchIpcModel viewIpcModel=response.body();
                            if(viewIpcModel.getStatus().equalsIgnoreCase("success"))
                            {
                                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                                ipcRecycler.setLayoutManager(linearLayoutManager);
                                SearchIpcAdapter adapter=new SearchIpcAdapter(viewIpcModel,getActivity());
                                ipcRecycler.setAdapter(adapter);
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "No IPC Sections Found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchIpcModel> call, Throwable t) {
                            Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return root;
    }

    private void initView(View root) {

        ipcRecycler = root.findViewById(R.id.ipc_recycler);
        searchView = root.findViewById(R.id.search_edt);
        imageView = root.findViewById(R.id.search_img);

    }
}