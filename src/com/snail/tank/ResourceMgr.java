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
import java.util.Objects;

/**
 * @author taozhi
 */
public class ResourceMgr {

  // 单例
  private ResourceMgr(){}

  private static final ResourceMgr INSTANCE = new ResourceMgr();

  public static ResourceMgr getInstance() {
    return INSTANCE;
  }

  public static BufferedImage badTankL, badTankU, badTankR, badTankD;
  public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
  public static BufferedImage bulletL, bulletU, bulletR, bulletD;
  public static BufferedImage[] explodes = new BufferedImage[16];

  static {
    try {
      badTankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png")));
      badTankL = ImageUtil.rotateImage(badTankU, -90);
      badTankR = ImageUtil.rotateImage(badTankU, 90);
      badTankD = ImageUtil.rotateImage(badTankU, 180);

      goodTankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png")));
      goodTankL = ImageUtil.rotateImage(goodTankU, -90);
      goodTankR = ImageUtil.rotateImage(goodTankU, 90);
      goodTankD = ImageUtil.rotateImage(goodTankU, 180);

      bulletU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png")));
      bulletL = ImageUtil.rotateImage(bulletU, -90);
      bulletR = ImageUtil.rotateImage(bulletU, 90);
      bulletD = ImageUtil.rotateImage(bulletU, 180);

      for (int i=0;i<16;i++) {
        explodes[i] = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif")));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}