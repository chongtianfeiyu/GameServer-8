import com.itgowo.http.HnHttpServer;

import java.nio.charset.Charset;

public class Main {
    private static HnHttpServer mHttpServer = new HnHttpServer();

    public static void main(String[] args) {
        initServer();
    }

    private static void initServer() {
        Thread mGameThread = new Thread(() -> {
            try {
                Thread.currentThread().setName("GameMainThread");
                mHttpServer.start(1666);
            } catch (Exception mEm) {
                mEm.printStackTrace();
            }
        });
        mGameThread.start();
    }
}
