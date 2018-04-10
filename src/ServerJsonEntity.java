import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by hnvfh on 2017/4/5.
 */
public class ServerJsonEntity {
    public static final int Success = 1;
    public static final int Fail = 2;
    public static final int other = 3;
    public static final String Code_Success = "success";
    public static final String Code_Fail = "fail";
    private int code=1;
    private String msg=Code_Success;
    private Object data=null;



    public int getCode() {
        return code;
    }

    public ServerJsonEntity setCode(int mCode) {
        code = mCode;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ServerJsonEntity setMsg(String mMsg) {
        msg = mMsg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ServerJsonEntity setData(Object mData) {
        data = mData;
        return this;
    }
    public String toJson() throws UnsupportedEncodingException {

        return  JSON.toJSONString(this)  ;
    }
    public byte[] toBytes() throws UnsupportedEncodingException {
       return toJson().getBytes("UTF-8");
    }
}
