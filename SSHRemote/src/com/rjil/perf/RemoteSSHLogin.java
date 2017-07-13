package com.rjil.perf;
import java.io.InputStream;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RemoteSSHLogin {
public String output="";
	public String remotesshfire(String host,String user,String privateKeyPath,String command1){
	        try {
	        JSch jsch = new JSch();
	        jsch.addIdentity(privateKeyPath);
	        java.util.Properties config = new java.util.Properties();
	        config.put("StrictHostKeyChecking", "no");
	        Session session = jsch.getSession(user, host, 22);
	        session.setConfig(config);
	        session.connect();
	        output=host;
	       // System.out.println("Connected");
	        output=output.concat(">> Connected");
	        Channel channel = session.openChannel("exec");
	        ((ChannelExec) channel).setCommand(command1);
	        channel.setInputStream(null);
	        ((ChannelExec) channel).setErrStream(System.err);
	        InputStream in = channel.getInputStream();
	        channel.connect();
	        byte[] tmp = new byte[1024];
	        while (true) {
	            while (in.available() > 0) {
	                int i = in.read(tmp, 0, 1024);
	                if (i < 0)
	                    break;
	                  //  System.out.print(new String(tmp, 0, i));
	                    output=output.concat(" >> ");
	                    output=output.concat(new String(tmp, 0, i));
	            }
	            if (channel.isClosed()) {
	               // System.out.println("exit-status: " + channel.getExitStatus());
	                output=output.concat(" >> ");
	                output=output.concat("exit-status: " + channel.getExitStatus());
	                break;
	            }
	            try {
	                 Thread.sleep(1000);
	            } catch (Exception ee) {}
	        }
	        channel.disconnect();
	        session.disconnect();
	      //  System.out.println("Disconnected");
	        output=output.concat(" >> ");
	        output=output.concat("Disconnected");
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return output;
	    }

	public static void main(String[] args) {
		String host = "ec2-52-76-66-56.ap-southeast-1.compute.amazonaws.com";
        String user = "ec2-user";
        String privateKeyPath = "D:\\Performance\\Keys\\TJ_ST_KP_Testing.pem";
        String command1 = "";
        RemoteSSHLogin rsl=new RemoteSSHLogin();
       String pan=rsl.remotesshfire(host, user, privateKeyPath, command1);
       System.out.println(pan);
	}
}