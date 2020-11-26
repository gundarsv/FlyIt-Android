package com.flyit.application.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.adapters.ChatMessageAdapter;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.Chatroom;
import com.flyit.application.models.ChatroomMessage;
import com.flyit.application.viewModels.ChatViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private FragmentManager mFragmentManager;
    private RelativeLayout mLoadedLayout;
    private ConstraintLayout mLoadingLayout;
    private RecyclerView mMessageRecycler;
    private ChatViewModel chatViewModel;
    private ChatMessageAdapter mMessageAdapter;
    private List<ChatroomMessage> chatroomMessages;
    private Button mSendMessageButton;
    private Button mDecisionButton;
    private EditText mMessageEditText;
    private TextView mChatroomNameTextView;

    private final String JOIN = "JOIN";
    private final String LEAVE = "LEAVE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mMessageRecycler = view.findViewById(R.id.recyclerview_message_list);
        mSendMessageButton = view.findViewById(R.id.send_message_button);
        mMessageEditText = view.findViewById(R.id.message_edit_text);
        mChatroomNameTextView = view.findViewById(R.id.chatroom_name);
        mLoadedLayout = view.findViewById(R.id.loaded_layout_joined);
        mLoadingLayout = view.findViewById(R.id.loading_layout);
        mDecisionButton = view.findViewById(R.id.chat_decision_button);

        chatroomMessages = new ArrayList<>();

        mFragmentManager = getActivity().getSupportFragmentManager();
        mMessageAdapter = new ChatMessageAdapter(getActivity(), chatroomMessages);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);

        mMessageRecycler.setLayoutManager(layoutManager);
        mMessageRecycler.setAdapter(mMessageAdapter);

        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        mMessageRecycler.setItemAnimator(animator);

        chatViewModel = new ViewModelProvider(getActivity()).get(ChatViewModel.class);
        chatViewModel.startConnection();

        chatViewModel.getIsFinished().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    chatViewModel.getChatroomById(String.valueOf(getArguments().getInt("ChatroomId", 0)));
                }
            }
        });

        chatViewModel.getChatroom().observe(getViewLifecycleOwner(), new Observer<Chatroom>() {
            @Override
            public void onChanged(Chatroom chatroom) {
                if (chatroom == null) {
                    return;
                } else if (chatroom.isHasUserJoined()) {
                    mChatroomNameTextView.setText(chatroom.getFlightNo());
                    mDecisionButton.setText(LEAVE);
                    mSendMessageButton.setEnabled(true);
                    mMessageEditText.setEnabled(true);
                    chatViewModel.startChatConnection(chatroom.getId());
                    chatroomMessages.addAll(chatroom.getChatroomMessages());
                    mMessageAdapter.notifyItemRangeInserted(0, chatroomMessages.size() - 1);
                    mMessageRecycler.scrollToPosition(chatroomMessages.size() - 1);
                } else {
                    mChatroomNameTextView.setText(chatroom.getFlightNo());
                    chatroomMessages.clear();
                    mMessageAdapter.notifyItemRangeRemoved(0, chatroomMessages.size());
                    mDecisionButton.setText(JOIN);
                    Toast.makeText(getActivity(), "Press JOIN if you want to join the chatroom", Toast.LENGTH_LONG).show();
                    mMessageEditText.setEnabled(false);
                    mSendMessageButton.setEnabled(false);
                }
            }
        });

        chatViewModel.getIncomingMessage().observe(getViewLifecycleOwner(), new Observer<ChatroomMessage>() {
            @Override
            public void onChanged(ChatroomMessage chatroomMessage) {
                if (chatroomMessage == null) {
                    return;
                } else {
                    chatroomMessages.add(chatroomMessage);
                    mMessageAdapter.notifyItemInserted(chatroomMessages.size() - 1);
                    mMessageRecycler.scrollToPosition(chatroomMessages.size() - 1);
                }
            }
        });

        mSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mMessageEditText.getText().toString().trim().isEmpty()) {
                    chatViewModel.sendMessage(getArguments().getInt("ChatroomId", 0), mMessageEditText.getText().toString());
                    mMessageEditText.setText("");
                }
            }
        });

        chatViewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });

        chatViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mLoadedLayout.setVisibility(View.INVISIBLE);
                    mLoadingLayout.setVisibility(View.VISIBLE);
                } else {
                    mLoadedLayout.setVisibility(View.VISIBLE);
                    mLoadingLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        mDecisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mDecisionButton.getText().toString()) {
                    case LEAVE:
                        chatViewModel.leaveChatroomById(String.valueOf(getArguments().getInt("ChatroomId", 0)));
                        break;
                    case JOIN:
                        chatViewModel.joinChatroomById(String.valueOf(getArguments().getInt("ChatroomId", 0)));
                        break;
                }
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                chatViewModel.endChatConnection(getArguments().getInt("ChatroomId", 0));
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), mFragmentManager, new ControlCenterMenuFragment(), "ControlCenterMenuFragment", getArguments(), R.id.fragment_container);
            }
        });

        return view;
    }
}
