package com.example.messagecenter.network.contentpage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentListData {

    @SerializedName("status")
    private Boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<DataDTO> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("user_id")
        private String userId;
        @SerializedName("unread")
        private String unread;
        @SerializedName("name")
        private String name;
        @SerializedName("uid")
        private String uid;
        @SerializedName("fast_token")
        private String fastToken;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUnread() {
            return unread;
        }

        public void setUnread(String unread) {
            this.unread = unread;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getFastToken() {
            return fastToken;
        }

        public void setFastToken(String fastToken) {
            this.fastToken = fastToken;
        }
    }
}
