package netty;

/**
 * @author js.ding
 * @Title: Test001
 * @ProjectName daodemo
 * @Description: TODO
 * @date 2019/5/1015:05
 */
public class Test001 {
    public static void main(String[] args) throws Exception {
        int port = 8083;
        new DiscardServer(port).run();
        System.out.println("server run");
    }
}
