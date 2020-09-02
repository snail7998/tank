/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	Explode.java
 * 模块说明：
 * 修改历史：
 * 2020/9/2 - taozhi - 创建。
 */
package com.snail.tank;

import java.awt.*;

/**
 * @author taozhi
 */
public class Explode {

  public static int WIDTH = ResourceMgr.explodes[0].getWidth();
  public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

  private int x, y;

  // 当前画到爆炸的第几步，每paint一次 step 加1
  private int step = 0;

  private boolean living = true;
  TankFrame tf;

  public Explode(int x, int y, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.tf = tf;

    // new Audio("audio/explode.wav")
  }

  public void paint(Graphics g) {
    g.drawImage(ResourceMgr.explodes[step++], x , y, null);
    if (step >= ResourceMgr.explodes.length) {
      step = 0;
      living = false;
    }
  }

  public boolean isLiving() {
    return living;
  }

  public void setLiving(boolean living) {
    this.living = living;
  }
}
