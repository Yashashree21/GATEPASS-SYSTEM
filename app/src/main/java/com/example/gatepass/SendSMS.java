package com.example.gatepass;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendSMS extends Activity {

    public void sendmessage (String email,String result) {
        try {
            String stringSenderEmail = "vkathiwale@gmail.com";
            String stringReceiverEmail = email;
            String stringPasswordSenderEmail = "fvqduujninlirafy";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
            mimeMessage.setSubject("Subject: Major Project Team Computer Engineering Department");
            if (result.equals("approved")){
                mimeMessage.setText("Hello User, \nWe Heard That You Have Applied For Gate Pass\nSo Here Is A Good News For You\nWait Wait Wait \n Its Not About Which You Are Thinking Right Now\nIts About Your Gate Pass\nYeah Your Gate Pass Has Been Approved");
            }else {
                mimeMessage.setText("Hello User,\n There Are Tough and Good Times In Life \n Just We Have To Keep Patience \n and we are Regret To Inform You That \n Your Request For Gate Pass Has Been Declined");
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


//    public String getEmoji(int uni){
//        return  new String(Character.toChars(uni));
//    }
}
