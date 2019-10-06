package com.ibm.ph.amperca.iihtibm.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaptchaController {

  @GetMapping("captcha")
  public void buildCaptcha(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("image/jpg");
    int totalCharacters = 6;
    int height = 40;
    int width = 170;

    Font fontStye = new Font("TimesRoman", Font.BOLD | Font.ITALIC, 30);

    Random randomCharacters = new Random();
    Long rChNum = Math.abs(randomCharacters.nextLong());
    String imageCode = Long.toString(rChNum, 36).substring(0, totalCharacters);

    BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
    Graphics2D iMage = (Graphics2D) bImage.getGraphics();
    iMage.setFont(fontStye);
    iMage.setBackground(new Color(0, 0, 255));
    for (int i = 0; i < totalCharacters; i++) {
      iMage.setColor(new Color(randomCharacters.nextInt(255), randomCharacters.nextInt(255),
          randomCharacters.nextInt(255)));
      if (i % 2 == 0) {
        iMage.drawString(imageCode.substring(i, i + 1), 25 * i, 24);
      } else {
        iMage.drawString(imageCode.substring(i, i + 1), 25 * i, 35);
      }
    }

    OutputStream image;
    try {
      image = response.getOutputStream();
      ImageIO.write(bImage, "jpeg", image);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      iMage.dispose();
    }

    if (request != null) {
      HttpSession session = request.getSession();
      session.setAttribute("captchValueSession", imageCode);
    }
  }
}
