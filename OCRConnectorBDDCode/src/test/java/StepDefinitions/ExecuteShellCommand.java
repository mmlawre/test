package StepDefinitions;

import Utils.PropertyReader;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.cucumber.java.en.When;

import java.io.InputStream;

public class ExecuteShellCommand {
    @When("User runs the D command")
    public void runCommandD() {
        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(PropertyReader.getProperty("hostUser"), PropertyReader.getProperty("host"), 22);
            session.setPassword(PropertyReader.getProperty("hostPassword"));
            session.setConfig(config);
            session.connect();
            System.out.println("========================================");
            System.out.println("Connected to Server");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand("source ~/.bash_profile" + ";" + PropertyReader.getProperty("relPathToConnectorShell")+" -source concur -customer test1 -D");
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
            }
            channel.disconnect();
            session.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
