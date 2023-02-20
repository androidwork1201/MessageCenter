package com.example.messagecenter.list;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messagecenter.adapter.ContentListAdapter;
import com.example.messagecenter.databinding.FragmentContentInfoListBinding;
import com.example.messagecenter.network.MessageCenterApiInterface;
import com.example.messagecenter.network.contentpage.ContentListClient;
import com.example.messagecenter.network.contentpage.ContentListData;
import com.example.messagecenter.network.loginpage.LoginClient;
import com.example.messagecenter.network.loginpage.LoginData;
import com.example.messagecenter.value.ValuePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContentInfoListFragment extends Fragment {


    private FragmentContentInfoListBinding binding;
    private ContentListAdapter adapter;
    private ContentInfoListViewModel viewModel;

    /**sessionToken List*/
    private ArrayList<String> sessionToken = new ArrayList<>();

    /**子帳號相關資料*/
    private ArrayList<String> userId = new ArrayList<>();
    private ArrayList<String> unread = new ArrayList<>();
    private ArrayList<String> userName = new ArrayList<>();
    private ArrayList<String> userUid = new ArrayList<>();

    public ContentInfoListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TagB", "OnCreateView");

        binding = FragmentContentInfoListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ContentInfoListViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setContentListViewModel(viewModel);
        return binding.getRoot();
    }

    /**初始化及接收帳號密碼資訊*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TagB", "OnViewCreated");

        assert getArguments() != null;
        String pushToken = getArguments().getString("pushToken");
        String accountInfo = getArguments().getString("account");
        String passwordInfo = getArguments().getString("password");
        analyzeLoginInfo(pushToken, accountInfo, passwordInfo);


    }

    @Override
    public void onStart() {
        super.onStart();

        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycler.setHasFixedSize(true);
        Log.d("TagB", "OnStart");

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("TagB", "OnResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TagB", "OnPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TagB", "OnStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d("TagB", "OnDestroyView");
    }

    /**解析使用者登入資訊*/
    private void analyzeLoginInfo(
            String pushToken,
            String account,
            String password
    ) {
        MessageCenterApiInterface messageCenterApiInterface = LoginClient.getLoginClientData().create(MessageCenterApiInterface.class);
        Call<LoginData> call = messageCenterApiInterface.getLoginData(pushToken, account, password);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                LoginData.DataDTO info = response.body().getData();
                viewModel.getSessionToken().setValue(info.getSessionToken());
                sessionToken.add(info.getSessionToken());
                System.out.println(viewModel.getSessionToken().getValue());

                showContentInfoList(viewModel.getSessionToken().getValue());

            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    /**顯示子帳號資訊*/
    private void showContentInfoList(String token) {
        MessageCenterApiInterface messageCenterApiInterface =
                ContentListClient.getContentListClientData().create(MessageCenterApiInterface.class);
        Call<ContentListData> call = messageCenterApiInterface.getContentListData(token);
        call.enqueue(new Callback<ContentListData>() {
            @Override
            public void onResponse(Call<ContentListData> call, Response<ContentListData> response) {
                List<ContentListData.DataDTO> info = response.body().getData();
                for (int i = 0; i < info.size(); i++) {
                    userId.add(info.get(i).getUserId());
                    unread.add(info.get(i).getUnread());
                    userName.add(info.get(i).getName());
                    userUid.add(info.get(i).getUid());
                }
                adapter = new ContentListAdapter(getContext(), userId, unread, userName, userUid);
                binding.recycler.setAdapter(adapter);
                adapter.submitList(info);
            }

            @Override
            public void onFailure(Call<ContentListData> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}