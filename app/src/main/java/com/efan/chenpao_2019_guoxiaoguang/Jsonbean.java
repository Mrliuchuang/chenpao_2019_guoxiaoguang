package com.efan.chenpao_2019_guoxiaoguang;

import java.util.List;

/**
 * Created by Administrator on 2019/1/21.
 */

public class Jsonbean {

    /**
     * result : {"face_token":"8afc606d0b23318de9d66067a519a0a4","user_list":[{"score":93.209609985352,"group_id":"student","user_id":"liuchuang","user_info":""}]}
     * log_id : 305486879906264281
     * error_msg : SUCCESS
     * cached : 0
     * error_code : 0
     * timestamp : 1547990626
     */
    private ResultEntity result;
    private long log_id;
    private String error_msg;
    private int cached;
    private int error_code;
    private int timestamp;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public void setCached(int cached) {
        this.cached = cached;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public ResultEntity getResult() {
        return result;
    }

    public long getLog_id() {
        return log_id;
    }

    public String getError_msg() {
        return error_msg;
    }

    public int getCached() {
        return cached;
    }

    public int getError_code() {
        return error_code;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public class ResultEntity {
        /**
         * face_token : 8afc606d0b23318de9d66067a519a0a4
         * user_list : [{"score":93.209609985352,"group_id":"student","user_id":"liuchuang","user_info":""}]
         */
        private String face_token;
        private List<User_listEntity> user_list;

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        public void setUser_list(List<User_listEntity> user_list) {
            this.user_list = user_list;
        }

        public String getFace_token() {
            return face_token;
        }

        public List<User_listEntity> getUser_list() {
            return user_list;
        }

        public class User_listEntity {
            /**
             * score : 93.209609985352
             * group_id : student
             * user_id : liuchuang
             * user_info :
             */
            private Double score;
            private String group_id;
            private String user_id;
            private String user_info;

            public void setScore(Double  score) {
                this.score = score;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setUser_info(String user_info) {
                this.user_info = user_info;
            }

            public Double getScore() {
                return score;
            }

            public String getGroup_id() {
                return group_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getUser_info() {
                return user_info;
            }
        }
    }
}
