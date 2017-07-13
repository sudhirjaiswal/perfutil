package com.rjil.perf;
import com.rjil.perf.RemoteSSHLogin;
public class LinuxClient {

	public static void main(String[] args) {
		String host = "ec2-52-76-66-56.ap-southeast-1.compute.amazonaws.com";
        String user = "ec2-user";
        String privateKeyPath = "D:\\Performance\\Keys\\TJ_ST_KP_Testing.pem";
        String command1 = "echo \"Chalo bhai connect ho gaya!qwertyuio\" >> /home/ec2-user/test.out";
        RemoteSSHLogin rsl=new RemoteSSHLogin();
       String pan=rsl.remotesshfire(host, user, privateKeyPath, command1);
       System.out.println(pan);

	}
}
