/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	Bullet.java
 * 模块说明：
 * 修改历史：
 * 2020/6/10 - taozhi - 创建。
 */
package com.snail.tank;

import java.awt.*;

/**
 * @author taozhi
 */
public class Bullet {

  // 速度
  private static final int SPEED = 10;
  // 子弹外形
  private static final int WIDTH = 20, HEIGHT = 20;

  // 位置
  private int x, y;
  // 方向
  private Dir dir;
  // 子弹是否在屏幕内，不在的话，从list中去除，保证内存不泄露
  private boolean live = true;

  private TankFrame tf;

  public Bullet(int x, int y, Dir dir, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.tf = tf;
  }

  public void paint(Graphics g) {
    if (!live) {
      tf.bullets.remove(this);
    }

    Color c = g.getColor();
    g.setColor(Color.RED);
    g.fillOval(x, y, WIDTH, HEIGHT);
    g.setColor(c);
    move();
  }

  private void move() {
    switch (this.dir) {
      case LEFT:
        x -= SPEED;
        break;
      case RIGHT:
        x += SPEED;
        break;
      case UP:
        y -= SPEED;
        break;
      case DOWN:
        y += SPEED;
        break;
      default:
        break;
    }
    // 如果超出屏幕范围，子弹 live为 false
    if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
      live = false;
    }
  }
}
