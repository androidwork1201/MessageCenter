package com.example.messagecenter.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.messagecenter.R;
import com.example.messagecenter.databinding.FragmentLoginBinding;

import java.util.Objects;


public class LoginFragment extends Fragment {


    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;
    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Tag", "OnCreateView");
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Tag", "OnViewCreate");

        binding.btn.setOnClickListener(v -> {
            if (binding.editAccount.getText().length() < 1 || binding.editPassword.getText().length() < 1) {
                Toast.makeText(getActivity(), "帳號密碼請勿留白!", Toast.LENGTH_SHORT).show();
            } else {
                Bundle args = new Bundle();
                args.putString("pushToken", viewModel.getDriveToken().getValue());
                args.putString("account", binding.editAccount.getText().toString());
                args.putString("password", binding.editPassword.getText().toString());
                Navigation.findNavController(requireView()).navigate(
                        R.id.action_loginFragment_to_contentInfoListFragment,
                        args);
            }

        });

    }

    @Override
    public void onStart() {
        super.onStart();


        Log.d("Tag", "OnStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tag", "OnResume");
    }

    /**避免翻轉時已輸入內容丟失*/
    @Override
    public void onPause() {
        super.onPause();
        viewModel.getAccount().setValue(binding.editAccount.getText().toString());
        viewModel.getPassword().setValue(binding.editPassword.getText().toString());
        Log.d("Tag", "OnPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Tag", "OnStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; //避免ML
        Log.d("Tag", "OnDestroyView");
    }

}