package com.fc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootTest
public class MailTest {
    @Autowired
    private JavaMailSender sender;

    @Test
    void MimeTest() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom("2631994547@qq.com");

        helper.setTo(new String[] {

        });

        helper.setCc(new String[] {

        });

        helper.setBcc(new String[] {

        });

        helper.setSubject("");

        helper.setText("");

        sender.send(mimeMessage);
    }

    @Test
    void  testSimple() {
        //简单的邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom("2631994547@qq.com");
        //接收者
        message.setTo("2373661156@qq.com",
                      "2633054873@qq.com",
                      "3322432808@qq.com");
        //抄送人
        message.setCc("");
        //秘密抄送，只有发送人和密抄者能看到
        message.setBcc("");
        //邮件主题
        message.setSubject("重金求子");
        //邮件内容
        message.setText("听我说谢谢你，因为有你，温暖了四季");

        sender.send(message);
    }

}
