/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	ResourceMgr.java
 * 模块说明：
 * 修改历史：
 * 2020/9/1 - taozhi - 创建。
 */
package com.snail.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author taozhi
 */
public class ResourceMgr {
  public static BufferedImage tankL, tankU, tankR, tankD;

  static {
    try {
      tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
      tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
      tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
      tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}