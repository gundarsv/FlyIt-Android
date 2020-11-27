package com.flyit.application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyit.application.R;
import com.flyit.application.models.ChatroomMessage;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final int VIEW_TYPE_INFORMATION_MESSAGE = 3;

    private FragmentActivity mContext;
    private List<ChatroomMessage> mMessageList;

    public ChatMessageAdapter(FragmentActivity context, List<ChatroomMessage> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatroomMessage message = mMessageList.get(position);

        if (PreferenceManager.getDefaultSharedPreferences(mContext).getString("Current_UserName", "").equals(message.getUserName()) && message.getMessageType() == 2) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else if (message.getMessageType() == 1) {
            return VIEW_TYPE_INFORMATION_MESSAGE;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new MessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new MessageHolder(view);
        } else if (viewType == VIEW_TYPE_INFORMATION_MESSAGE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_information, parent, false);
            return new InformationMessageHolder(view);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatroomMessage message = mMessageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((MessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_INFORMATION_MESSAGE:
                ((InformationMessageHolder) holder).bind(message);
        }
    }

    public class MessageHolder extends RecyclerView.ViewHolder {
        private TextView messageText, timeText, nameText;

        public MessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
            nameText = itemView.findViewById(R.id.text_message_name);
        }

        public void bind(ChatroomMessage message) {
            messageText.setText(message.getMessage());
            timeText.setText("");
            nameText.setText(message.getUserName());
        }
    }

    public class InformationMessageHolder extends RecyclerView.ViewHolder {
        private TextView messageText;

        public InformationMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
        }

        public void bind(ChatroomMessage message) {
            messageText.setText(message.getMessage());
        }
    }
}
