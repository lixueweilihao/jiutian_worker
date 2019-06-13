package hadoop.hadoop.com.rpc.login;

public class LoginServiceImplement implements LoginServiceInterface{
    @Override
    public String login(String username, String passwd) {
        // TODO Auto-generated method stub
        System.out.println("haha");
        return username+"Login Successful!";
    }
}
